package com.smartErp.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("system")
public class DictionaryManageListController {
	
	@RequestMapping("dictionaryManageList")
	public String dictionaryManageList() {
		
		
		return "system/dictionaryManageList";
	}
}
