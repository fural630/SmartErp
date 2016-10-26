package com.smartErp.cdiscount.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.cdiscount.model.CdiscountEan;
import com.smartErp.util.frame.Page;

public interface CdiscountEanDao {
	public List<Map<String, Object>> getCdiscountEanManagePage(Page page);

	public CdiscountEan getCdiscountEanByEan(String ean);

	public void insert(CdiscountEan cdiscountEan);
}
