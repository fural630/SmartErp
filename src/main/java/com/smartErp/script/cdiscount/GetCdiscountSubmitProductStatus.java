package com.smartErp.script.cdiscount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ArrayOfProductReportLog;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetProductPackageSubmissionResultResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.PackageFilter;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ProductIntegrationReportMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ProductReportLog;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ProductReportPropertyLog;
import com.smartErp.application.libraries.constentEnum.CdiscountPublishStatusEnum;
import com.smartErp.application.libraries.select.CdiscountPublishStatus;
import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.dao.CdiscountPublishDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.constant.CdPackageIntegrationStatus;
import com.smartErp.product.dao.ProductDao;
import com.smartErp.product.model.Product;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.code.SysRemark;
import com.smartErp.util.frame.SpringContextUtil;

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
		
		CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getCdiscountApiConfigNoCloseByEmail(email);
		if (null == cdiscountApiConfig) {
			System.out.println("cdiscountApiConfig is empty !");
			return false;
		}
		
//		Locker locker = new Locker("Get_Cdiscount_Submit_Product_Status" + email);
//        locker.setDelaySecs(60 * 10); // 10min
//        if (!locker.lock(true)) {
//            System.out.println(" ############## Locker  ###############");
//            System.out.println("Get_Cdiscount_Submit_Product_Status" + email);
//            System.out.println(" ################ Locker  #############");
//            return false;
//        }
//		
        try {
        	CdiscountPublishDao cdiscountPublishDao = (CdiscountPublishDao) SpringContextUtil.getBean("cdiscountPublishDao");
        	ProductDao productDao = (ProductDao) SpringContextUtil.getBean("productDao");
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("publishStatus", CdiscountPublishStatusEnum.PLATFORM_SYSTEM_VALIDATE_ING.getValue());
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
					Long packageId = cdiscountPublish.getProductPackageId();
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
								for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
									cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), log));
									cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue());
									cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
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
												Product product = productDao.getProductBySkuAndCreator(sku, cdiscountApiConfig.getCreator());
												Integer productId = product.getId();
												Map<String, Object> query = new HashMap<String, Object>();
												query.put("apiId", cdiscountApiConfig.getId());
												query.put("ean", ean);
												query.put("productId", productId);
												CdiscountPublish cdiscountPublish = cdiscountPublishDao.getByEanAndProductIdAndApiId(query);
												if (null != cdiscountPublish) {
													if ("OK".equals(status)) {
														String log = myLocale.getText("at.time.cdiscount.platform.validate.success");
														String newLog = SysRemark.append(cdiscountPublish.getLog(), log);
														cdiscountPublish.setLog(newLog);
														cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.PLATFORM_MANUAL_VALIDATE_ING.getValue());
														cdiscountPublishDao.updateCdiscountPublish(cdiscountPublish);
													} else if ("KO".equals(status)) {
														String reason = logList[5].trim();
														String log = myLocale.getText("at.time.cdiscount.platform.validate.fial.reason", reason);
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
