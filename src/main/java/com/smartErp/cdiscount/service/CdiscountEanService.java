package com.smartErp.cdiscount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountEanDao;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountEanService {
	
	@Autowired
	private CdiscountEanDao cdiscountEanDao;

	public List<Map<String, Object>> getCdiscountEanManagePage(Page page) {
		return cdiscountEanDao.getCdiscountEanManagePage(page);
	}

}
