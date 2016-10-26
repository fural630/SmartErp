package com.smartErp.cdiscount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.PublishDeliveryModeDao;
import com.smartErp.cdiscount.model.PublishDeliveryMode;

@Service
public class PublishDeliveryModeService {
	
	@Autowired(required=false)
	PublishDeliveryModeDao publishDeliveryModeDao;
	
	public void insertPublishDeliveryMode(PublishDeliveryMode publishDeliveryMode) {
		publishDeliveryModeDao.insertPublishDeliveryMode(publishDeliveryMode);
	}
	public void deletePublishDeliveryModeByPublishId(Integer publishId) {
		publishDeliveryModeDao.deletePublishDeliveryModeByPublishId(publishId);
	}
	public List<PublishDeliveryMode> getPublishDeliveryModeListByPublishId(Integer publishId) {
		return publishDeliveryModeDao.getPublishDeliveryModeListByPublishId(publishId);
	}
	
}
