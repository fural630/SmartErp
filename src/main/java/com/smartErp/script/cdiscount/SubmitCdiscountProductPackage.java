package com.smartErp.script.cdiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ProductIntegrationReportMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ProductPackageRequest;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SubmitProductPackage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SubmitProductPackageResponse;
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

public class SubmitCdiscountProductPackage {
	
	public static void main(String[] args) {
		SubmitCdiscountProductPackage publishCdiscountProduct = new SubmitCdiscountProductPackage();
		String email = "ehome2016@kkmoon.com";
		publishCdiscountProduct.runScript(email);
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
		
//		Locker locker = new Locker("Submit_Cdiscount_Product_Package" + email);
//        locker.setDelaySecs(60 * 20); // 20min
//        if (!locker.lock(true)) {
//            System.out.println(" ############## Locker  ###############");
//            System.out.println("Submit_Cdiscount_Product_Package" + email);
//            System.out.println(" ################ Locker  #############");
//            return false;
//        }
        try {
	        CdiscountPublishDao cdiscountPublishDao = (CdiscountPublishDao) SpringContextUtil.getBean("cdiscountPublishDao");
	        
	        Integer apiId = cdiscountApiConfig.getId();
	        Integer publishStatus = CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_BASIC_INFO.getValue();
	        
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
	        String packagePath = utilDao.packageCdiscountProduct(cdiscountPublishList);
	        if (StringUtils.isNotEmpty(packagePath)) {
	        	MyLocale myLocale = new MyLocale();
	        	SubmitProductPackage paramSubmitProductPackage = new SubmitProductPackage();
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
        			String log = myLocale.getText("upload.product.package.success.packageid.and.packagepath", String.valueOf(packageId), packagePath);
        			for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
        				cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), log));
        				cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PLATFORM_SYSTEM_VALIDATE_ING.getValue());
        				cdiscountPublish.setProductPackageId(packageId);
        				cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
    				}
        		} else {
        			String errorMessage = message.getErrorMessage();
        			if (StringUtils.isNotEmpty(errorMessage)) {
        				String log = myLocale.getText("upload.product.package.fail.reason.is", errorMessage);
        				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
        					cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue());
        					cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), log));
        					cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
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
//			locker.unLock();
		}
		return false;
	}
}

