package com.smartErp.cdiscount.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.code.MainPage;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountDefaultsValueController extends MainPage{
	
	@Autowired
	private CdiscountApiConfigService cdiscountApiConfigService;
	
	@RequestMapping("cdiscountDefaultsValue")
	public String cdiscountDefaultsValue (Model model, Page page){
		String title = "navigator.cdiscount.defaults.value";
		_execute(page, model, title);
		List<Map<String, Object>> collection = cdiscountApiConfigService.getCdiscountApiConfigPage(page);
		model.addAttribute("collection", collection);
		return "cdiscount/cdiscountDefaultsValue";
	}
}
