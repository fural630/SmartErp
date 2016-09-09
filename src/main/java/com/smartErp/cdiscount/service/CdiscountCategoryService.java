package com.smartErp.cdiscount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountCategoryDao;
import com.smartErp.cdiscount.model.CdiscountCategory;

@Service
public class CdiscountCategoryService {
	
	@Autowired
	private CdiscountCategoryDao cdiscountCategoryDao;
	
	public void saveCdiscountCategory(CdiscountCategory cdiscountCategory) {
		cdiscountCategoryDao.insert(cdiscountCategory);
	}
}
