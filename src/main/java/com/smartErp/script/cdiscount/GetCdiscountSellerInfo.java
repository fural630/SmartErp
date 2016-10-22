package com.smartErp.script.cdiscount;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.frame.SpringContextUtil;

public class GetCdiscountSellerInfo {
	
	public boolean runScript(String argList) {
		String email = argList;
		CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getCdiscountApiConfigNoCloseByEmail(email);
		Dumper.dump(cdiscountApiConfig);
		return true;
	}
}
