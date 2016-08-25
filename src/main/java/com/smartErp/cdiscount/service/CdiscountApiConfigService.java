package com.smartErp.cdiscount.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.util.code.MyDate;

@Service
public class CdiscountApiConfigService {
	
	@Resource
	private CdiscountApiConfigDao cdiscountApiConfigDao;
	
	public void insertCdiscountApiConfig(CdiscountApiConfig cdiscountApiConfig) {
		
		Integer userId = new Integer(630);
		String createTime = new MyDate().getCurrentDateTime();
		cdiscountApiConfig.setCreator(userId);
		cdiscountApiConfig.setLastUpdateTime(createTime);
		cdiscountApiConfigDao.insert(cdiscountApiConfig);
	}
	
	public void updateCdiscountApiConfige(CdiscountApiConfig cdiscountApiConfig) {
		String updateTime = new MyDate().getCurrentDateTime();
		cdiscountApiConfig.setLastUpdateTime(updateTime);
		cdiscountApiConfigDao.update(cdiscountApiConfig);
	}
}
