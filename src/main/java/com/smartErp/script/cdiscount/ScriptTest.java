package com.smartErp.script.cdiscount;

import com.smartErp.code.SystemInfo;

public class ScriptTest {
	
	public boolean runScript(String argList) {
		String scriptPath = SystemInfo.getScriptPath();
		System.out.println(scriptPath);
		
//		String email = "ehome2016@kkmoon.com";
//		CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
//		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getCdiscountApiConfigNoCloseByEmail(email);
//		if (null == cdiscountApiConfig) {
//			System.out.println("cdiscountApiConfig is empty !");
//			return false;
//		}
//		
//		CdiscountPublishDao cdiscountPublishDao = (CdiscountPublishDao) SpringContextUtil.getBean("cdiscountPublishDao");
//        Integer apiId = cdiscountApiConfig.getId();
//        Integer publishStatus = CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_BASIC_INFO.getValue();
//        Map<String, Object> queryMap = new HashMap<String, Object>();
//        queryMap.put("apiId", apiId);
//        queryMap.put("publishStatus", publishStatus);
//        
//        List<CdiscountPublish> cdiscountPublishList = cdiscountPublishDao.getCdiscountPublishByApiIdAndStatus(queryMap);
//		
//        CdiscountUploadFileUtilDao cdiscountUploadFileUtilDao = new CdiscountUploadFileUtilDao();
//        cdiscountUploadFileUtilDao.createOffersXml(cdiscountPublishList);
//        
		return false;
	}
}
