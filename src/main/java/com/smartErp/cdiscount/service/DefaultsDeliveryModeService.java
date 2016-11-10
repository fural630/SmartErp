package com.smartErp.cdiscount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.DefaultsDeliveryModeDao;
import com.smartErp.cdiscount.model.DefaultsDeliveryMode;

@Service
public class DefaultsDeliveryModeService {

	@Autowired(required = false)
	DefaultsDeliveryModeDao defaultsDeliveryModeDao;
	
	public void insertDefaultsValueDeliveryMode(DefaultsDeliveryMode defaultsDeliveryMode){
		defaultsDeliveryModeDao.insertDefaultsValueDeliveryMode(defaultsDeliveryMode);
	}
	public void deleteDefaultsValueDeliveryModeByDefaultsId(Integer defaultsId) {
		defaultsDeliveryModeDao.deleteDefaultsValueDeliveryModeByDefaultsId(defaultsId);
	}
	public List<DefaultsDeliveryMode> getDefaultsValueDeliveryModeListByDefaultsId(Integer defaultsId) {
		return defaultsDeliveryModeDao.getDefaultsValueDeliveryModeListByDefaultsId(defaultsId);
	}
	
	
}
