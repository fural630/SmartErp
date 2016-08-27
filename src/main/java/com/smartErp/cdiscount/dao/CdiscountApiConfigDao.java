package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.util.frame.Page;

public interface CdiscountApiConfigDao {
	public void insert(CdiscountApiConfig cdiscountApiConfig);
	public void update(CdiscountApiConfig cdiscountApiConfig);
	public CdiscountApiConfig getById(Integer id);
	public List<CdiscountApiConfig> findAll();
	public List<CdiscountApiConfig> getCdiscountApiConfigPage(Page page);
}
