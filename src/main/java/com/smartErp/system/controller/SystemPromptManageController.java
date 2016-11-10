package com.smartErp.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.code.MainPage;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class SystemPromptManageController extends MainPage{
	
	@RequestMapping("systemPromptManage")
	public String systemPromptManage (Model model, Page page){
		String title = "navigator.system.prompt.manage";
		_execute(page, model, title);
		List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
		model.addAttribute("collection", collection);
		return "system/systemPromptManage";
	}
}
