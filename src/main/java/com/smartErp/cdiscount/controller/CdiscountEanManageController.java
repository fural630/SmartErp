package com.smartErp.cdiscount.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.cdiscount.service.CdiscountEanService;
import com.smartErp.code.MainPage;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountEanManageController extends MainPage{
	
	@Autowired
	private CdiscountEanService cdiscountEanService;
	
	@RequestMapping("cdiscountEanManage")
	public String cdiscountEanManage(Model model, Page page){
		String title = "navigator.cdiscount.ean.manage";
		_execute(page, model, title);
		List<Map<String, Object>> collection = cdiscountEanService.getCdiscountEanManagePage(page);
		Dumper.dump(collection);
		model.addAttribute("collection", collection);
		return "cdiscount/cdiscountEanManage";
	}
	
	@RequestMapping("batchSaveCdiscountEan")
	public String batchSaveCdiscountEan () {
		return null;
	}
}
