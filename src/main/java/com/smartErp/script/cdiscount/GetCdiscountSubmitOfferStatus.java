package com.smartErp.script.cdiscount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ArrayOfOfferReportLog;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetOfferPackageSubmissionResult;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetOfferPackageSubmissionResultResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.OfferIntegrationReportMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.OfferReportLog;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.OfferReportPropertyLog;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.PackageFilter;
import com.smartErp.application.libraries.constentEnum.CdiscountPublishStatusEnum;
import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.dao.CdiscountPublishDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.product.dao.ProductDao;
import com.smartErp.product.model.Product;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.code.SysRemark;
import com.smartErp.util.frame.SpringContextUtil;

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
		CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getCdiscountApiConfigNoCloseByEmail(email);
		if (null == cdiscountApiConfig) {
			System.out.println("cdiscountApiConfig is empty !");
			return false;
		}
		
//		Locker locker = new Locker("Get_Cdiscount_Submit_Offer_Status" + email);
//        locker.setDelaySecs(60 * 10); // 10min
//        if (!locker.lock(true)) {
//            System.out.println(" ############## Locker  ###############");
//            System.out.println("Get_Cdiscount_Submit_Offer_Status" + email);
//            System.out.println(" ################ Locker  #############");
//            return false;
//        }
        
        try {
        	CdiscountPublishDao cdiscountPublishDao = (CdiscountPublishDao) SpringContextUtil.getBean("cdiscountPublishDao");
        	ProductDao productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        	
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("publishStatus", CdiscountPublishStatusEnum.PLATFORM_OFFERS_VALIDATE_ING.getValue());
			queryMap.put("apiId", cdiscountApiConfig.getId());
			
	        List<CdiscountPublish> cdiscountPublishList = cdiscountPublishDao.getCdiscountPublishByApiIdAndStatus(queryMap);
			
			if (CollectionUtils.isEmpty(cdiscountPublishList)) {
	        	System.out.println(" ############## Locker  ###############");
	            System.out.println("no data " + email);
	            System.out.println(" ################ Locker  #############");
	        	return false;
	        }
			
			Set<Long> packageIdSet = new HashSet<Long>();
			if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					Long packageId = cdiscountPublish.getOffersPackageId();
					if (null != packageId) {
						packageIdSet.add(packageId);
					}
				}
			}
			
			String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
	        if (StringUtils.isEmpty(token)) {
	        	System.out.println("token = " + token);
	        	return false;
	        }
			
			System.out.println("packageId : ");
			Dumper.dump(packageIdSet);
			MyLocale myLocale = new MyLocale();
			if (CollectionUtils.isNotEmpty(packageIdSet)) {
				for (Long packageId : packageIdSet) {
					MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
					GetOfferPackageSubmissionResult paramGetOfferPackageSubmissionResult = new GetOfferPackageSubmissionResult();
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
							Product product = productDao.getProductBySkuAndCreator(sku, cdiscountApiConfig.getCreator());
							Integer productId = product.getId();
							Map<String, Object> query = new HashMap<String, Object>();
							query.put("apiId", cdiscountApiConfig.getId());
							query.put("ean", ean);
							query.put("productId", productId);
							CdiscountPublish cdiscountPublish = cdiscountPublishDao.getByEanAndProductIdAndApiId(query);
							if (null != cdiscountPublish) {
								if ("OK".equals(status)) {
									String log = myLocale.getText("at.time.offers.validate.success.publish.success");
									String newLog = SysRemark.append(cdiscountPublish.getLog(), log);
									cdiscountPublish.setLog(newLog);
									cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PUBLISHED_SUCCESS.getValue());
									cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
								} else if ("KO".equals(status)) {
									String reason = logList[5].trim();
									String log = myLocale.getText("at.time.cdiscount.platform.validate.offers.fail.reason", reason);
									String newLog = SysRemark.append(cdiscountPublish.getLog(), log);
									cdiscountPublish.setLog(newLog);
									cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue());
									cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			locker.unLock();
		}
		return true;
	}
}
