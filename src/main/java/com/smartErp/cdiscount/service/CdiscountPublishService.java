package com.smartErp.cdiscount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountPublishDao;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountPublishService {
	
	@Autowired
	CdiscountPublishDao cdiscountPublishDao;

	public void insertCdiscountPublish(CdiscountPublish cdiscountPublish) {
		cdiscountPublishDao.insertCdiscountPublish(cdiscountPublish);
	}

	public List<Map<String, Object>> getCdiscountPublishByPage(Page page) {
		return cdiscountPublishDao.getCdiscountPublishByPage(page);
	}
	
	public CdiscountPublish getCdiscountPublishById(Integer id) {
		return cdiscountPublishDao.getCdiscountPublishById(id);
	}
	
	
}
