package com.smartErp.cdiscount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountPublishImageDao;
import com.smartErp.cdiscount.model.CdiscountPublishImage;

@Service
public class CdiscountPublishImageService {
	
	@Autowired
	CdiscountPublishImageDao cdiscountPublishImageDao;
	
	public List<String> getPublishImageList(Integer publishId) {
		return cdiscountPublishImageDao.getPublishImageList(publishId);
	}
	
	public List<CdiscountPublishImage> getPublishImageListByPublishId(Integer publishId) {
		return cdiscountPublishImageDao.getPublishImageListByPublishId(publishId);
	}
	
	public void insertPublishImage(CdiscountPublishImage cdiscountPublishImage) {
		cdiscountPublishImageDao.insertPublishImage(cdiscountPublishImage);
	}
	
	public void deletePublishImageByPublishId(Integer publishId) {
		cdiscountPublishImageDao.deletePublishImageByPublishId(publishId);
	}

	public void insertPublishImage(String imageUrl, Integer publishId) {
		CdiscountPublishImage cdiscountPublishImage = new CdiscountPublishImage();
		cdiscountPublishImage.setPublishId(publishId);
		cdiscountPublishImage.setImageUrl(imageUrl);
		cdiscountPublishImageDao.insertPublishImage(cdiscountPublishImage);
	}
	
}
