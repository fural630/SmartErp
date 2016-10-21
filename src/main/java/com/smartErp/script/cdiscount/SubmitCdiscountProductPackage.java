package com.smartErp.script.cdiscount;

import org.apache.commons.lang.StringUtils;

public class SubmitCdiscountProductPackage {
	
	public static void main(String[] args) {
		SubmitCdiscountProductPackage publishCdiscountProduct = new SubmitCdiscountProductPackage();
		String email = "ehome2016@kkmoon.com";
		publishCdiscountProduct.runScript(email);
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
		
		Locker locker = new Locker("Submit_Cdiscount_Product_Package" + email);
        locker.setDelaySecs(60 * 20); // 20min
        if (!locker.lock(true)) {
            System.out.println(" ############## Locker  ###############");
            System.out.println("Submit_Cdiscount_Product_Package" + email);
            System.out.println(" ################ Locker  #############");
            return false;
        }
        try {
	        CdiscountBackstagePublishListingDao listingDao = new CdiscountBackstagePublishListingDao();
	        Map<String, Object> queryWaitUploadPackageMap = new HashMap<String, Object>();
	        queryWaitUploadPackageMap.put("publishStatus", CdiscountPublishStatus.WAIT_PRODUCT_UPLOAD_PACKAGE.getValue());
	        queryWaitUploadPackageMap.put("apiId", cdiscountApiConfig.getId());
	        List<CdiscountBackstagePublishListing> listings = listingDao.fetchList(queryWaitUploadPackageMap);
	        if (CollectionUtils.isEmpty(listings)) {
	        	System.out.println(" ############## Locker  ###############");
	            System.out.println("no data " + email);
	            System.out.println(" ################ Locker  #############");
	        	return false;
	        }
	        
	        List<Integer> idList = new ArrayList<Integer>();
			for (CdiscountBackstagePublishListing publishListing : listings) {
				idList.add(publishListing.getId());
			}
	        
	        CdiscountUtilDao utilDao = new CdiscountUtilDao();
	        String packagePath = utilDao.packageCdiscountProduct(listings);
	        if (StringUtils.isNotEmpty(packagePath)) {
	        	MyLocale myLocale = new MyLocale();
	        	SubmitProductPackage paramSubmitProductPackage = new SubmitProductPackage();
	        	String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
	        	HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
	        	paramSubmitProductPackage.setHeaderMessage(headerMessage);
	        	ProductPackageRequest productPackageRequest = new ProductPackageRequest();
	        	productPackageRequest.setZipFileFullPath(packagePath);
	        	paramSubmitProductPackage.setProductPackageRequest(productPackageRequest);
	        	
        		MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
        		SubmitProductPackageResponse response = marketplaceAPIServiceStub.submitProductPackage(paramSubmitProductPackage);
        		ProductIntegrationReportMessage message = response.getSubmitProductPackageResult();
        		Dumper.dump(message);
        		if (message.getOperationSuccess()) {
        			Long packageId = message.getPackageId();
        			String log = myLocale.getText("upload.product.package.success.the.package.id.is", String.valueOf(packageId), packagePath);
        			for (CdiscountBackstagePublishListing publishListing : listings) {
        				publishListing.setOperationLog(SysRemark.append(publishListing.getOperationLog(), log));
        				publishListing.setPublishStatus(CdiscountPublishStatus.PLATFORM_VALIDATION_PRODUCT_ING.getValue());
        				publishListing.setProductPackageId(packageId);
        				listingDao.saveOrUpdate(publishListing);
    				}
        		} else {
        			String errorMessage = message.getErrorMessage();
        			if (StringUtils.isNotEmpty(errorMessage)) {
        				String log = myLocale.getText("upload.product.package.fail.reason.is", errorMessage, packagePath);
        				for (CdiscountBackstagePublishListing publishListing : listings) {
        					publishListing.setPublishStatus(CdiscountPublishStatus.UPLOAD_PRODUCT_PACKAGE_FAIL.getValue());
        					publishListing.setOperationLog(SysRemark.append(publishListing.getOperationLog(), log));
        					listingDao.saveOrUpdate(publishListing);
        				}
        			}
        		}
        		return true;
	        } else {
	        	System.out.println("fail ! packagePath = " + packagePath);
	        	return false;
	        }
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			locker.unLock();
		}
		return false;
	}
}

