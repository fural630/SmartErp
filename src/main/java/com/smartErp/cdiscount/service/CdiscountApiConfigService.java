package com.smartErp.cdiscount.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountApiConfigService {
	
	@Resource
	private CdiscountApiConfigDao cdiscountApiConfigDao;
	
	public void insertCdiscountApiConfig(CdiscountApiConfig cdiscountApiConfig) {
		Integer userId = new Integer(630);
		String createTime = new MyDate().getCurrentDateTime();
		cdiscountApiConfig.setCreator(userId);
		cdiscountApiConfig.setLastUpdateTime(createTime);
		cdiscountApiConfig.setApiPassword(DESEncrypt.DataEncrypt(cdiscountApiConfig.getApiPassword()));
		cdiscountApiConfigDao.insert(cdiscountApiConfig);
	}
	
	public void updateCdiscountApiConfige(CdiscountApiConfig cdiscountApiConfig) {
		String updateTime = new MyDate().getCurrentDateTime();
		cdiscountApiConfig.setLastUpdateTime(updateTime);
		cdiscountApiConfig.setApiPassword(DESEncrypt.DataEncrypt(cdiscountApiConfig.getApiPassword()));
		cdiscountApiConfigDao.update(cdiscountApiConfig);
	}
	
	public CdiscountApiConfig getCdiscountApiConfigById(Integer id) {
		return cdiscountApiConfigDao.getById(id);
	}
	
	public List<CdiscountApiConfig> getAllCdiscountApiConfig() {
		return cdiscountApiConfigDao.findAll();
	}
	
	public List<CdiscountApiConfig> getCdiscountApiConfigPage(Page page) {
		return cdiscountApiConfigDao.getCdiscountApiConfigPage(page);
	}
}
