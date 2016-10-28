package com.smartErp.script.cdiscount;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.util.frame.SpringContextUtil;

public class ScriptTest {
	
	
	public boolean runScript(String argList) {
		
		String email = "ehome2016@kkmoon.com";
		CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getCdiscountApiConfigNoCloseByEmail(email);
		if (null == cdiscountApiConfig) {
			System.out.println("cdiscountApiConfig is empty !");
			return false;
		}
		
		String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
		System.out.println(token);
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
