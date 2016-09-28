package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.CdiscountPublishImage;

public interface CdiscountPublishImageDao {
	public List<CdiscountPublishImage> getPublishImageListByPublishId(Integer publishId);
	
	public void insertPublishImage(CdiscountPublishImage cdiscountPublishImage);
	
	public void deletePublishImageByPublishId(Integer publishId);

	public List<String> getPublishImageList(Integer publishId);
	
}
