package com.smartErp.system.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.system.model.DictionaryType;

public interface DictionaryTypeDao {
	public List<DictionaryType> getDictionaryByParentId(Integer id);

	public List<Map<String, Object>> getAllDictionaryTree();

	public DictionaryType getDictionaryById(Integer id);
	
	public DictionaryType getMaxModuleDictionaryTypeById(Integer id);
	
	public DictionaryType getMaxModuleDictionaryTypeByParentId(Integer id);
	
	public void saveDictionaryType(DictionaryType dictionaryType);
}
