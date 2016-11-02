package com.smartErp.system.dao;

import java.util.List;
import java.util.Map;

import com.smartErp.system.model.ScriptConfig;
import com.smartErp.util.frame.Page;

public interface ScriptConfigDao {

	public List<Map<String, Object>> getScriptConfigPage(Page page);

	public void createScriptConfig(ScriptConfig scriptConfig);

	public void updateScriptConfig(ScriptConfig scriptConfig);

	public ScriptConfig getScriptConfigById(Integer id);

	public void deleteScriptConfig(Integer id);

	public List<ScriptConfig> getScriptConfigByOpenStatus(Integer status);

}
