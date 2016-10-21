package com.smartErp.script.cdiscount;

public class SubmitCdiscountOfferPackage {
	
	public static void main(String[] args) {
		SubmitCdiscountOfferPackage submitCdiscountOfferPackage = new SubmitCdiscountOfferPackage();
		submitCdiscountOfferPackage.runScript("ehome2016@kkmoon.com");
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
		
		Locker locker = new Locker("Submit_Cdiscount_Offer_Package" + email);
        locker.setDelaySecs(60 * 30); // 30min
        if (!locker.lock(true)) {
            System.out.println(" ############## Locker  ###############");
            System.out.println("Submit_Cdiscount_Offer_Package" + email);
            System.out.println(" ################ Locker  #############");
            return false;
        }
        try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("publishStatus", CdiscountPublishStatus.WAIT_OFFERS_UPLOAD_PACKAGE.getValue());
			queryMap.put("apiId", cdiscountApiConfig.getId());
			
			CdiscountBackstagePublishListingDao publishListingDao = new CdiscountBackstagePublishListingDao();
			List<CdiscountBackstagePublishListing> publishListings = publishListingDao.fetchList(queryMap);
			
			if (CollectionUtils.isEmpty(publishListings)) {
	        	System.out.println(" ############## Locker  ###############");
	            System.out.println("no data " + email);
	            System.out.println(" ################ Locker  #############");
	        	return false;
	        }
			
			CdiscountUtilDao utilDao = new CdiscountUtilDao();
	        String packagePath = utilDao.packageCdiscountOffers(publishListings);
	        MyLocale myLocale = new MyLocale();
	        if (StringUtils.isNotEmpty(packagePath)) {
	        	try {
		        	MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
		        	SubmitOfferPackage paramSubmitCdiscountOfferPackage = new SubmitOfferPackage();
		        	String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
		        	HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
		        	paramSubmitCdiscountOfferPackage.setHeaderMessage(headerMessage);
		        	OfferPackageRequest offerPackageRequest = new OfferPackageRequest();
		        	offerPackageRequest.setZipFileFullPath(packagePath);
		        	paramSubmitCdiscountOfferPackage.setOfferPackageRequest(offerPackageRequest);
		        	SubmitOfferPackageResponse response = marketplaceAPIServiceStub.submitOfferPackage(paramSubmitCdiscountOfferPackage);
		        	OfferIntegrationReportMessage message = response.getSubmitOfferPackageResult();
		        	Dumper.dump(message);
		        	if (message.getOperationSuccess()) {
		        		Long offersPackageId = message.getPackageId();
		        		String log = myLocale.getText("at.time.upload.offers.package.success.package.id.is", String.valueOf(offersPackageId), packagePath);
		        		for (CdiscountBackstagePublishListing publishListing : publishListings) {
		        			publishListing.setOperationLog(SysRemark.append(publishListing.getOperationLog(), log));
		        			publishListing.setPublishStatus(CdiscountPublishStatus.PLATFORM_VALIDATION_OFFERS_ING.getValue());
		        			publishListing.setOffersPackageId(offersPackageId);
		        			publishListingDao.saveOrUpdate(publishListing);
		        		}
		        	}
				} catch (Exception e) {
					e.printStackTrace();
				}
	        } else {
	        	String log = myLocale.getText("at.time.upload.offers.package.fail", packagePath);
	    		for (CdiscountBackstagePublishListing publishListing : publishListings) {
	    			publishListing.setOperationLog(SysRemark.append(publishListing.getOperationLog(), log));
	    			publishListing.setPublishStatus(CdiscountPublishStatus.UPLOAD_OFFERS_PACKAGE_FAIL.getValue());
	    			publishListingDao.saveOrUpdate(publishListing);
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

