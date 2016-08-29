package com.smartErp.system.dao;

import java.util.List;
import java.util.Map;

public interface DictionaryTypeDao {
	public List<Map<String, Object>> getDictionaryByParentId(Integer id);

	public List<Map<String, Object>> getDictionaryAll();

	public Map<String, Object> getDictionaryById(Integer id);
}
