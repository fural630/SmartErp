package com.smartErp.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smartErp.system.dao.DictionaryTypeDao;

@Service
public class DictionaryTypeService {
	
	@Resource
	private DictionaryTypeDao dictionaryTypeDao;
	
	public List<Map<String, Object>> getDictionaryByParentId(Integer id) {
		return dictionaryTypeDao.getDictionaryByParentId(id);
	}

	public List<Map<String, Object>> getDictionaryAll() {
		return dictionaryTypeDao.getDictionaryAll();
	}

	public Map<String, Object> getDictionaryById(Integer id) {
		return dictionaryTypeDao.getDictionaryById(id);
	}
}
