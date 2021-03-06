package com.smartErp.script.cdiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.OfferIntegrationReportMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.OfferPackageRequest;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SubmitOfferPackage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SubmitOfferPackageResponse;
import com.smartErp.application.libraries.constentEnum.CdiscountPublishStatusEnum;
import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.dao.CdiscountPublishDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.code.SysRemark;
import com.smartErp.util.frame.SpringContextUtil;

public class SubmitCdiscountOfferPackage {
	
	public static void main(String[] args) {
		SubmitCdiscountOfferPackage submitCdiscountOfferPackage = new SubmitCdiscountOfferPackage();
		submitCdiscountOfferPackage.runScript("ehome2016@kkmoon.com");
	}
	
	public boolean runScript(String argList) {
		String email = "ehome2016@kkmoon.com";
		if (StringUtils.isEmpty(email)) {
			System.out.println("email is empty!");
			return false;
		}
		
		CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getCdiscountApiConfigNoCloseByEmail(email);
		if (null == cdiscountApiConfig) {
			System.out.println("cdiscountApiConfig is empty !");
			return false;
		}
		
//		Locker locker = new Locker("Submit_Cdiscount_Offer_Package" + email);
//        locker.setDelaySecs(60 * 30); // 30min
//        if (!locker.lock(true)) {
//            System.out.println(" ############## Locker  ###############");
//            System.out.println("Submit_Cdiscount_Offer_Package" + email);
//            System.out.println(" ################ Locker  #############");
//            return false;
//        }
        try {
        	
        	CdiscountPublishDao cdiscountPublishDao = (CdiscountPublishDao) SpringContextUtil.getBean("cdiscountPublishDao");
	        Integer apiId = cdiscountApiConfig.getId();
	        Integer publishStatus = CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_OFFERS.getValue();
	        
	        Map<String, Object> queryMap = new HashMap<String, Object>();
	        queryMap.put("apiId", apiId);
	        queryMap.put("publishStatus", publishStatus);
	        
	        List<CdiscountPublish> cdiscountPublishList = cdiscountPublishDao.getCdiscountPublishByApiIdAndStatus(queryMap);
			
			if (CollectionUtils.isEmpty(cdiscountPublishList)) {
	        	System.out.println(" ############## Locker  ###############");
	            System.out.println("no data " + email);
	            System.out.println(" ################ Locker  #############");
	        	return false;
	        }
			
			
			String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
	        if (StringUtils.isEmpty(token)) {
	        	System.out.println("token = " + token);
	        	return false;
	        }
			
			CdiscountUploadFileUtilDao utilDao = new CdiscountUploadFileUtilDao();
	        String packagePath = utilDao.packageCdiscountOffers(cdiscountPublishList);
	        System.out.println(packagePath);
	        MyLocale myLocale = new MyLocale();
	        if (StringUtils.isNotEmpty(packagePath)) {
	        	MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
	        	SubmitOfferPackage paramSubmitCdiscountOfferPackage = new SubmitOfferPackage();
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
	        		String log = myLocale.getText("upload.offers.package.success.packageid.and.packagepath", String.valueOf(offersPackageId), packagePath);
	        		for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
	        			cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), log));
	        			cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PLATFORM_OFFERS_VALIDATE_ING.getValue());
	        			cdiscountPublish.setOffersPackageId(offersPackageId);
	        			cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
	        		}
	        	} else {
        			String errorMessage = message.getErrorMessage();
        			if (StringUtils.isNotEmpty(errorMessage)) {
        				String log = myLocale.getText("upload.offers.package.fail.reason.is", errorMessage);
        				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
        					cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue());
        					cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), log));
        					cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
        				}
        			}
        		}
	        } else {
	        	System.out.println("fail ! packagePath = " + packagePath);
	        	return false;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			locker.unLock();
		}
		return true;
	}
}

