package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.code.MainPage;
import com.smartErp.system.service.ScriptConfigService;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class ScriptConfigManageController extends MainPage {
	
	@Autowired
	private ScriptConfigService scriptConfigService;
	
	@RequestMapping("scriptConfigManage")
	public String scriptManage (Model model, Page page){
		String title = "navigator.script.config.manage";
		_execute(page, model, title);
		List<Map<String, Object>> collection = scriptConfigService.getScriptConfigPage(page);
		model.addAttribute("collection", collection);
		return "system/scriptConfigManage";
	}
}
