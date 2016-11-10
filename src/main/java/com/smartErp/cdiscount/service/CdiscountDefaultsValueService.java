package com.smartErp.cdiscount.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.application.libraries.constentEnum.YesNoEnum;
import com.smartErp.cdiscount.dao.CdiscountDefaultsValueDao;
import com.smartErp.cdiscount.model.CdiscountDefaultsValue;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountDefaultsValueService {
	
	@Autowired(required = false)
	CdiscountDefaultsValueDao cdiscountDefaultsValueDao;


	public boolean isHaveDefaultsTemplate(Integer userId) {
		List<CdiscountDefaultsValue> list = cdiscountDefaultsValueDao.getDefaultsTemplate(userId, YesNoEnum.YES.getValue());
		if (CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}

	public void insertCdiscountDefaultsValue(CdiscountDefaultsValue cdiscountDefaultsValue) {
		cdiscountDefaultsValueDao.insertCdiscountDefaultsValue(cdiscountDefaultsValue);
	}
	
	public List<Map<String, Object>> getCdiscountDefaultsValueByPage(Page page) {
		return cdiscountDefaultsValueDao.getCdiscountDefaultsValueByPage(page);
	}

	public void deleteCdiscountDefaultsValueById(Integer id) {
		cdiscountDefaultsValueDao.deleteById(id);
	}

	public CdiscountDefaultsValue getCdiscountDefaultsValueById(Integer id) {
		CdiscountDefaultsValue cdiscountDefaultsValue = cdiscountDefaultsValueDao.getCdiscountDefaultsValueById(id);
		return cdiscountDefaultsValue;
	}

	public void udpateCdiscountDefaultsValue(
			CdiscountDefaultsValue cdiscountDefaultsValue) {
		cdiscountDefaultsValueDao.udpateCdiscountDefaultsValue(cdiscountDefaultsValue);
	}

	public void updateIsDefaultsTemplate(Integer isDefaults, Integer userId) {
		cdiscountDefaultsValueDao.updateIsDefaultsTemplate(isDefaults, userId);
	}

	public void setAsDefaultsTemplate(Integer id) {
		cdiscountDefaultsValueDao.setAsDefaultsTemplate(id, YesNoEnum.YES.getValue());
	}

	public List<CdiscountDefaultsValue> getCdiscountDefaultsValueByCreator(
			Integer userId) {
		return cdiscountDefaultsValueDao.getCdiscountDefaultsValueByCreator(userId);
	}

	public CdiscountDefaultsValue getDefaultsTemplateValue(Integer userId, Integer isDefaults) {
		return cdiscountDefaultsValueDao.getDefaultsTemplateValue(userId, isDefaults);
	}
	
	
}
