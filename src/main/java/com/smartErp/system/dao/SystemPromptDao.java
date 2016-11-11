package com.smartErp.system.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.system.model.SystemPrompt;
import com.smartErp.util.frame.Page;

public interface SystemPromptDao {

	public SystemPrompt getSystemPromptById(Integer id);

	public void insertSystemPrompt(SystemPrompt systemPrompt);

	public void updateSystemPrompt(SystemPrompt systemPrompt);
	
	public void deleteById (Integer id);
	
	public List<Map<String, Object>> getSystemPromptPage(Page page);

}
