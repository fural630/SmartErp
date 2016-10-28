package com.smartErp.cdiscount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.util.frame.Page;

public interface CdiscountApiConfigDao {
	public void insert(CdiscountApiConfig cdiscountApiConfig);
	public void update(CdiscountApiConfig cdiscountApiConfig);
	public CdiscountApiConfig getById(Integer id);
	public List<CdiscountApiConfig> findAll();
	public List<Map<String, Object>> getCdiscountApiConfigPage(Page page);
	public void removeAll();
	public CdiscountApiConfig getCdiscountApiConfigById(Integer id);
	public List<CdiscountApiConfig> getCdiscountApiConfigByCreator(Integer userId);
	public void deleteCdiscountApiConfigById(Integer id);
	public CdiscountApiConfig getCdiscountApiConfigNoCloseByEmail(String email);
	public void updateToken(@Param("token") String token, @Param("tokenTimeOut") String tokenTimeOut,@Param("id") Integer id);
}
