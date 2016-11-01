package com.smartErp.system.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.util.frame.Page;

public interface ScriptConfigDao {

	List<Map<String, Object>> getScriptConfigPage(Page page);
	

}
