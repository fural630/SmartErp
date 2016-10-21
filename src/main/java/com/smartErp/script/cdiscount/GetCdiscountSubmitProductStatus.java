package com.smartErp.script.cdiscount;

public class GetCdiscountSubmitProductStatus {
	public static void main(String[] args) {
		GetCdiscountSubmitProductStatus getPublishProductStatus = new GetCdiscountSubmitProductStatus();
		String email = "ehome2016@kkmoon.com";
		getPublishProductStatus.runScript(email);
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
		
		Locker locker = new Locker("Get_Cdiscount_Submit_Product_Status" + email);
        locker.setDelaySecs(60 * 10); // 10min
        if (!locker.lock(true)) {
            System.out.println(" ############## Locker  ###############");
            System.out.println("Get_Cdiscount_Submit_Product_Status" + email);
            System.out.println(" ################ Locker  #############");
            return false;
        }
		
        try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			ServiceQueryHelper.and(queryMap, "publishStatus", CdiscountPublishStatus.PLATFORM_VALIDATION_PRODUCT_ING.getValue());
			ServiceQueryHelper.and(queryMap, "apiId", cdiscountApiConfig.getId());
			
			CdiscountBackstagePublishListingDao publishListingDao = new CdiscountBackstagePublishListingDao();
			List<CdiscountBackstagePublishListing> publishListings = publishListingDao.fetchList(queryMap);
			Set<Long> packageIdSet = new HashSet<Long>();
			if (CollectionUtils.isNotEmpty(publishListings)) {
				for (CdiscountBackstagePublishListing publishListing : publishListings) {
					Long packageId = publishListing.getProductPackageId();
					if (null != packageId) {
						packageIdSet.add(packageId);
					}
				}
			}
			System.out.println("packageId : ");
			Dumper.dump(packageIdSet);
			if (CollectionUtils.isNotEmpty(packageIdSet)) {
				for (Long packageId : packageIdSet) {
					MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
					MarketplaceAPIServiceStub.GetProductPackageSubmissionResult paramGetProductPackageSubmissionResult = new MarketplaceAPIServiceStub.GetProductPackageSubmissionResult();
					String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
					HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
					paramGetProductPackageSubmissionResult.setHeaderMessage(headerMessage);
					PackageFilter paramPackageFilter = new PackageFilter();
					paramPackageFilter.setPackageID(packageId);
					paramGetProductPackageSubmissionResult.setProductPackageFilter(paramPackageFilter);
					GetProductPackageSubmissionResultResponse response = marketplaceAPIServiceStub.getProductPackageSubmissionResult(paramGetProductPackageSubmissionResult);
					ProductIntegrationReportMessage message = response.getGetProductPackageSubmissionResultResult();
					Dumper.dump(message);
					MyLocale myLocale = new MyLocale();
					if (message.getOperationSuccess()) {
						String packageIntegrationStatus = message.getPackageIntegrationStatus();
						System.out.println("packageIntegrationStatus = " + packageIntegrationStatus);
						if (StringUtils.isNotEmpty(packageIntegrationStatus)) {
							if (packageIntegrationStatus.equals(CdPackageIntegrationStatus.REJECTED)) {
								String log = myLocale.getText("at.time.cdiscount.platform.validate.fail.reason.rejected");
								for (CdiscountBackstagePublishListing publishListing : publishListings) {
									publishListing.setOperationLog(SysRemark.append(publishListing.getOperationLog(), log));
									publishListing.setPublishStatus(CdiscountPublishStatus.PUBLISHED_FAIL.getValue());
									publishListingDao.saveOrUpdate(publishListing);
								}
							} else if (packageIntegrationStatus.equals(CdPackageIntegrationStatus.MANUALCHECKTOBEDONE)) {
								ArrayOfProductReportLog productLogList = message.getProductLogList();
								ProductReportLog productReportLogList[] = productLogList.getProductReportLog();
								if (null != productReportLogList && productReportLogList.length > 0) {
									for (ProductReportLog productReportLog : productReportLogList) {
										ProductReportPropertyLog productReportPropertyLog = productReportLog.getPropertyList().getProductReportPropertyLog()[0];
										if (null != productReportPropertyLog) {
											//	logMassage etc : "RM2457|4854006043754|OK||8500|Produit conforme : votre produit est bien pris en compte pour la création
											String logMassage = productReportPropertyLog.getLogMessage();
											if (StringUtils.isNotEmpty(logMassage)) {
												String logList[] = logMassage.split("\\|");
												String sku = logList[0];
												String ean = logList[1];
												String status = logList[2].trim();
												Map<String, Object> query = new HashMap<String, Object>();
												ServiceQueryHelper.and(query, "sku", sku);
												ServiceQueryHelper.and(query, "ean", ean);
												CdiscountBackstagePublishListing publishListing = publishListingDao.findOne(query);
												if (null != publishListing) {
													if ("OK".equals(status)) {
														String log = myLocale.getText("at.time.cdiscount.platform.validate.success");
														String newLog = SysRemark.append(publishListing.getOperationLog(), log);
														publishListing.setOperationLog(newLog);
														publishListing.setPublishStatus(CdiscountPublishStatus.PLATFORM_MANUAL_REVIEW_PRODUCT_ING.getValue());
														publishListingDao.saveOrUpdate(publishListing);
													} else if ("KO".equals(status)) {
														String validateLog = productReportPropertyLog.getName();
														String log = myLocale.getText("at.time.cdiscount.platform.validate.fial.reason", validateLog);
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
