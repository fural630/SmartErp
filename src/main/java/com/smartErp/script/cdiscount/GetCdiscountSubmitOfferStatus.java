package com.smartErp.script.cdiscount;

public class GetCdiscountSubmitOfferStatus {
	
	public static void main(String[] args) {
		GetCdiscountSubmitOfferStatus getCdiscountSubmitOfferStatus = new GetCdiscountSubmitOfferStatus();
		getCdiscountSubmitOfferStatus.runScript("ehome2016@kkmoon.com");
	}
	
	public boolean runScript(String argList) {
		String email = argList;
		if (StringUtils.isEmpty(email)) {
			System.out.println("email is empty!");
			return false;
		}
		CdiscountDao cdiscountDao = new CdiscountDao();
		CdiscountApiConfig cdiscountApiConfig = cdiscountDao.findNoCloseByEmail(email);
		if (null == cdiscountApiConfig) {
			System.out.println("cdiscountApiConfig is empty !");
			return false;
		}
		
		Locker locker = new Locker("Get_Cdiscount_Submit_Offer_Status" + email);
        locker.setDelaySecs(60 * 10); // 10min
        if (!locker.lock(true)) {
            System.out.println(" ############## Locker  ###############");
            System.out.println("Get_Cdiscount_Submit_Offer_Status" + email);
            System.out.println(" ################ Locker  #############");
            return false;
        }
        
        try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("publishStatus", CdiscountPublishStatus.PLATFORM_VALIDATION_OFFERS_ING.getValue());
			queryMap.put("apiId", cdiscountApiConfig.getId());
			
			CdiscountBackstagePublishListingDao publishListingDao = new CdiscountBackstagePublishListingDao();
			List<CdiscountBackstagePublishListing> publishListings = publishListingDao.fetchList(queryMap);
			Set<Long> packageIdSet = new HashSet<Long>();
			if (CollectionUtils.isNotEmpty(publishListings)) {
				for (CdiscountBackstagePublishListing publishListing : publishListings) {
					Long packageId = publishListing.getOffersPackageId();
					if (null != packageId) {
						packageIdSet.add(packageId);
					}
				}
			}
			System.out.println("packageId : ");
			Dumper.dump(packageIdSet);
			MyLocale myLocale = new MyLocale();
			if (CollectionUtils.isNotEmpty(packageIdSet)) {
				for (Long packageId : packageIdSet) {
					MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
					GetOfferPackageSubmissionResult paramGetOfferPackageSubmissionResult = new GetOfferPackageSubmissionResult();
					String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
					HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
					paramGetOfferPackageSubmissionResult.setHeaderMessage(headerMessage);
					PackageFilter paramPackageFilter = new PackageFilter();
					paramPackageFilter.setPackageID(packageId);
					paramGetOfferPackageSubmissionResult.setOfferPackageFilter(paramPackageFilter);
					GetOfferPackageSubmissionResultResponse response = marketplaceAPIServiceStub.getOfferPackageSubmissionResult(paramGetOfferPackageSubmissionResult);
					OfferIntegrationReportMessage message = response.getGetOfferPackageSubmissionResultResult();
					Dumper.dump(message);
					if (message.getOperationSuccess()) {
						ArrayOfOfferReportLog arrayOfOfferReportLog = message.getOfferLogList();
						OfferReportLog offerReportLogList[] = arrayOfOfferReportLog.getOfferReportLog();
						for (OfferReportLog offerReportLog : offerReportLogList) {
							OfferReportPropertyLog offerReportPropertyLog = offerReportLog.getPropertyList().getOfferReportPropertyLog()[0];
							String logMassage = offerReportPropertyLog.getLogMessage();
							String logList[] = logMassage.split("\\|");
							String sku = logList[0];
							String ean = logList[1];
							String status = logList[3].trim();
							String description = logList[5].trim();
							Map<String, Object> query = new HashMap<String, Object>();
							ServiceQueryHelper.and(query, "sku", sku);
							ServiceQueryHelper.and(query, "ean", ean);
							CdiscountBackstagePublishListing publishListing = publishListingDao.findOne(query);
							if (null != publishListing) {
								if ("OK".equals(status)) {
									String log = myLocale.getText("at.time.cdiscount.platform.validate.offers.success");
									String newLog = SysRemark.append(publishListing.getOperationLog(), log);
									publishListing.setOperationLog(newLog);
									publishListing.setPublishStatus(CdiscountPublishStatus.PUBLISHED_SUCCESS.getValue());
									publishListingDao.saveOrUpdate(publishListing);
								} else if ("KO".equals(status)) {
									String log = myLocale.getText("at.time.cdiscount.platform.validate.offers.fail.reason", description);
									String newLog = SysRemark.append(publishListing.getOperationLog(), log);
									publishListing.setOperationLog(newLog);
									publishListing.setPublishStatus(CdiscountPublishStatus.PUBLISHED_FAIL.getValue());
									publishListingDao.saveOrUpdate(publishListing);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locker.unLock();
		}
		return true;
	}
}
