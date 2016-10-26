package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.CdiscountCategory;

public interface CdiscountCategoryDao {
	public void insert(CdiscountCategory cdiscountCategory);

	public List<CdiscountCategory> getFirstCategoryByApiId(Integer apiId);
	
	public List<CdiscountCategory> getFirstCategory();

	public void deleteByApiId(Integer aipId);

	public List<CdiscountCategory> getCdiscountCategoryByParentId(Integer parentId);

	public CdiscountCategory getCdiscountCategoryByCode(String categoryCode);
	
	public CdiscountCategory getCdiscountCategoryById(Integer id);
}
