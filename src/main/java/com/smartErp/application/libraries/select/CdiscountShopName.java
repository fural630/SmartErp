package com.smartErp.application.libraries.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;
import com.smartErp.util.frame.SpringContextUtil;

public class CdiscountShopName {
	
	public Map<String, String> getOptions() {
        Map<String, String> options = new LinkedHashMap<String, String>();
        User user = UserSingleton.getInstance().getUser();
        CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
        List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountApiConfigDao.getCdiscountApiConfigByCreator(user.getId());
        if (CollectionUtils.isNotEmpty(cdiscountApiConfigList)) {
        	for (CdiscountApiConfig cdiscountApiConfig : cdiscountApiConfigList) {
        		options.put(String.valueOf(cdiscountApiConfig.getId()), cdiscountApiConfig.getShopName());
			}
        }
        return options;
    }	
}
