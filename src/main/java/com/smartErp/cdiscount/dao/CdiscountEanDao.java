package com.smartErp.cdiscount.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.util.frame.Page;

public interface CdiscountEanDao {
	public List<Map<String, Object>> getCdiscountEanManagePage(Page page);
}
