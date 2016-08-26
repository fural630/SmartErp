package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.CdiscountApiConfig;

public interface CdiscountApiConfigDao {
	public void insert(CdiscountApiConfig cdiscountApiConfig);
	public void update(CdiscountApiConfig cdiscountApiConfig);
	public CdiscountApiConfig getById(Integer id);
	public List<CdiscountApiConfig> findAll();
}
