package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.DefaultsDeliveryMode;

public interface DefaultsDeliveryModeDao {
	public void insertDefaultsValueDeliveryMode(DefaultsDeliveryMode defaultsDeliveryMode);
	public void deleteDefaultsValueDeliveryModeByDefaultsId(Integer defaultsId);
	public List<DefaultsDeliveryMode> getDefaultsValueDeliveryModeListByDefaultsId(Integer defaultsId);
}
