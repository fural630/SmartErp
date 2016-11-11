package com.smartErp.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.system.dao.SystemPromptDao;
import com.smartErp.system.model.SystemPrompt;
import com.smartErp.util.frame.Page;

@Service
public class SystemPromptService {
	
	@Autowired(required = false)
	private SystemPromptDao systemPromptDao;

	public SystemPrompt getSystemPromptById(Integer id) {
		return systemPromptDao.getSystemPromptById(id);
	}

	public void insertSystemPrompt(SystemPrompt systemPrompt) {
		systemPromptDao.insertSystemPrompt(systemPrompt);
	}

	public void updateSystemPrompt(SystemPrompt systemPrompt) {
		systemPromptDao.updateSystemPrompt(systemPrompt);
	}
	
	public List<Map<String, Object>> getSystemPromptPage(Page page) {
		return systemPromptDao.getSystemPromptPage(page);
	}
	
}
