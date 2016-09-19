package com.smartErp.cdiscount.dao;

import java.util.List;

import com.smartErp.cdiscount.model.CdiscountCategory;

public interface CdiscountCategoryDao {
	public void insert(CdiscountCategory cdiscountCategory);

	public List<CdiscountCategory> getFirstCategoryByApiId(Integer apiId);

	public void deleteByApiId(Integer aipId);

	public List<CdiscountCategory> getCdiscountCategoryByParentId(Integer parentId);
}
