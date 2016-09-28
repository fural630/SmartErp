package com.smartErp.cdiscount.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.util.frame.Page;

public interface CdiscountPublishDao {
	
	public void insertCdiscountPublish(CdiscountPublish cdiscountPublish);

	public List<Map<String, Object>> getCdiscountPublishByPage(Page page);
}
