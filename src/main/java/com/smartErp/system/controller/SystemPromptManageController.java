package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.code.MainPage;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.SystemPrompt;
import com.smartErp.system.service.SystemPromptService;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class SystemPromptManageController extends MainPage{
	
	@Autowired
	private SystemPromptService systemPromptService;
	
	@RequestMapping("systemPromptManage")
	public String systemPromptManage (Model model, Page page){
		_execute(page, model);
		List<Map<String, Object>> collection = systemPromptService.getSystemPromptPage(page);
		model.addAttribute("collection", collection);
		return "system/systemPromptManage";
	}
	
	@RequestMapping("saveSystemPromptConfig")
	@ResponseBody
	public String saveSystemPromptConfig(SystemPrompt systemPrompt) {
		MyDate myDate = new MyDate();
		String currentTime = myDate.getCurrentDateTime();
		if (null == systemPrompt.getId()) {		//create
			systemPrompt.setCreateTime(currentTime);
			systemPrompt.setUpdateTime(currentTime);
			systemPromptService.insertSystemPrompt(systemPrompt);
		} else {
			SystemPrompt oldSystemPrompt = systemPromptService.getSystemPromptById(systemPrompt.getId());
			systemPrompt.setCreateTime(oldSystemPrompt.getCreateTime());
			systemPrompt.setUpdateTime(currentTime);
			systemPromptService.updateSystemPrompt(systemPrompt);
		}
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("editSystemPrompt")
	@ResponseBody
	public String editSystemPrompt (Integer id) {
		SystemPrompt systemPrompt = systemPromptService.getSystemPromptById(id);
		return JsonUtil.toJsonStr(systemPrompt);
	}
	
	@RequestMapping("showSystemPromptDialog")
	@ResponseBody
	public String showSystemPromptDialog(Integer id) {
		SystemPrompt systemPrompt = systemPromptService.getSystemPromptById(id);
		return JsonUtil.toJsonStr(systemPrompt);
	}
}
