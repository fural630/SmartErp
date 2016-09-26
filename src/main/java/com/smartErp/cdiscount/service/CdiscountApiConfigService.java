package com.smartErp.cdiscount.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountApiConfigService {
	
	@Resource
	private CdiscountApiConfigDao cdiscountApiConfigDao;
	
	public void insertCdiscountApiConfig(CdiscountApiConfig cdiscountApiConfig) {
		cdiscountApiConfigDao.insert(cdiscountApiConfig);
	}
	
	public void updateCdiscountApiConfige(CdiscountApiConfig cdiscountApiConfig) {
		cdiscountApiConfigDao.update(cdiscountApiConfig);
	}
	
	public CdiscountApiConfig getCdiscountApiConfigById(Integer id) {
		return cdiscountApiConfigDao.getById(id);
	}
	
	public List<CdiscountApiConfig> getAllCdiscountApiConfig() {
		return cdiscountApiConfigDao.findAll();
	}
	
	public List<Map<String, Object>> getCdiscountApiConfigPage(Page page) {
		return cdiscountApiConfigDao.getCdiscountApiConfigPage(page);
	}
	
	public List<CdiscountApiConfig> getCdiscountApiConfigByCreator(Integer userId) {
		return cdiscountApiConfigDao.getCdiscountApiConfigByCreator(userId);
	}
	
	public void removeAll() {
		cdiscountApiConfigDao.removeAll();
	}

	public String testConnectApi(String apiAccount, String apiPassword) {
		String encryptionPassword = DESEncrypt.DataEncrypt(apiPassword);
		String token = CdiscountTokenUtil.getToken(apiAccount, encryptionPassword);
		return token;
	}

	public void deleteCdiscountApiConfigById(Integer id) {
		cdiscountApiConfigDao.deleteCdiscountApiConfigById(id);
	}
	
}
