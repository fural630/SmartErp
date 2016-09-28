package com.smartErp.cdiscount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountPublishDao;
import com.smartErp.cdiscount.model.CdiscountPublish;

@Service
public class CdiscountPublishService {
	
	@Autowired
	CdiscountPublishDao cdiscountPublishDao;

	public void insertCdiscountPublish(CdiscountPublish cdiscountPublish) {
		cdiscountPublishDao.insertCdiscountPublish(cdiscountPublish);
	}
	
	
}
