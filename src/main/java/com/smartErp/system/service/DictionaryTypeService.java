package com.smartErp.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.smartErp.system.dao.DictionaryTypeDao;
import com.smartErp.system.model.DictionaryType;

@Service
public class DictionaryTypeService {
	
	@Resource
	private DictionaryTypeDao dictionaryTypeDao;
	
	public List<DictionaryType> getDictionaryByParentId(Integer id) {
		return dictionaryTypeDao.getDictionaryByParentId(id);
	}

	public List<Map<String, Object>> getAllDictionaryTree() {
		return dictionaryTypeDao.getAllDictionaryTree();
	}

	public DictionaryType getDictionaryById(Integer id) {
		return dictionaryTypeDao.getDictionaryById(id);
	}

	public void addDictionaryType(Integer id, String moduleName) {
		List<DictionaryType> dictionaryTypeList = dictionaryTypeDao.getDictionaryByParentId(id);
		DictionaryType newDictionaryType = new DictionaryType();
		newDictionaryType.setModuleName(moduleName);
		newDictionaryType.setParentId(id);
		if (CollectionUtils.isEmpty(dictionaryTypeList)) {
			DictionaryType dictionaryType = dictionaryTypeDao.getDictionaryById(id);
			String moduleId = dictionaryType.getModuleId();
			moduleId = moduleId + "-01";
			newDictionaryType.setModuleId(moduleId);
		} else {
			DictionaryType dictionaryType = dictionaryTypeDao.getMaxModuleDictionaryTypeByParentId(id);
			String moduleId = dictionaryType.getModuleId();
			String moduleIdAry[] = moduleId.split("-");
			int size = moduleIdAry.length;
			Integer maxModuleId = Integer.parseInt(moduleIdAry[size - 1]);
			String newModuleId = moduleId.substring(0, moduleId.lastIndexOf("-") + 1);
			if (maxModuleId + 1 >= 10) {
				newModuleId += String.valueOf((maxModuleId + 1));
			} else {
				newModuleId += "0" + (maxModuleId + 1);
			}
			newDictionaryType.setModuleId(newModuleId);
		}
		dictionaryTypeDao.saveDictionaryType(newDictionaryType);
		
//		DictionaryType dictionaryType = dictionaryTypeDao.getMaxModuleDictionaryTypeByParentId(id);
////		if (null == )
//		String moduleId = dictionaryType.getModuleId();
//		String moduleIdAry[] = moduleId.split("-");
//		int size = moduleIdAry.length;
//		Integer maxModuleId = Integer.parseInt(moduleIdAry[size - 1]);
//		String newModuleId = "";
//		if (maxModuleId + 1 >= 10) {
//			newModuleId = String.valueOf((maxModuleId + 1));
//		} else {
//			newModuleId = "0" + (maxModuleId + 1);
//		}
//		DictionaryType newDictionaryType = new DictionaryType();
//		newDictionaryType.setModuleName(moduleName);
//		newDictionaryType.setParentId(id);
//		newDictionaryType.setModuleId(newModuleId);
//		dictionaryTypeDao.saveDictionaryType(newDictionaryType);
		
	}

	public void addRootDictionaryType(Integer id, String moduleName) {
		List<DictionaryType> dictionaryTypeList = dictionaryTypeDao.getDictionaryByParentId(id);
		DictionaryType newDictionaryType = new DictionaryType();
		newDictionaryType.setModuleName(moduleName);
		newDictionaryType.setParentId(id);
		if (CollectionUtils.isEmpty(dictionaryTypeList)) {
			newDictionaryType.setModuleId("01");
		} else {
			DictionaryType dictionaryType = dictionaryTypeDao.getMaxModuleDictionaryTypeByParentId(id);
			String moduleId = dictionaryType.getModuleId();
			Integer maxModuleId = Integer.parseInt(moduleId);
			String newModuleId = "";
			if (maxModuleId + 1 >= 10) {
				newModuleId = String.valueOf((maxModuleId + 1));
			} else {
				newModuleId = "0" + (maxModuleId + 1);
			}
			newDictionaryType.setModuleId(newModuleId);
		}
		dictionaryTypeDao.saveDictionaryType(newDictionaryType);
		
	}
}
