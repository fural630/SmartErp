package com.smartErp.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.system.dao.ScriptConfigDao;
import com.smartErp.util.frame.Page;

@Service
public class ScriptConfigService {
	
	@Autowired(required = false)
	private ScriptConfigDao scriptConfigDao;
	
	public List<Map<String, Object>> getScriptConfigPage(Page page) {
		return scriptConfigDao.getScriptConfigPage(page);
	}
	
	
}
