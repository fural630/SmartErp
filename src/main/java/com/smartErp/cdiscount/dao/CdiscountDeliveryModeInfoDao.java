package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.DeliveryModeInfor;

public interface CdiscountDeliveryModeInfoDao {
	public void insertDeliveryModeInfor(DeliveryModeInfor deliveryModeInfor);
	
	public void deleteDeliveryModeInforByApiId(Integer apiId);
	
	public List<DeliveryModeInfor> getDeliveryModeInforListByApiId(Integer apiId); 
}
