package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.PublishDeliveryMode;

public interface PublishDeliveryModeDao {
	public void insertPublishDeliveryMode(PublishDeliveryMode publishDeliveryMode);
	public void deletePublishDeliveryModeByPublishId(Integer publishId);
	public List<PublishDeliveryMode> getPublishDeliveryModeListByPublishId(Integer publishId);
}
