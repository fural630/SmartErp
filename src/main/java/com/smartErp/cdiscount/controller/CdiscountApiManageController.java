package com.smartErp.cdiscount.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.code.MainPage;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountApiManageController extends MainPage{
	
	@Autowired
	private CdiscountApiConfigService cdiscountApiConfigService;
	
	@RequestMapping("cdiscountApiConfigManage")
	public String cdiscountApiConfigManage(Model model, HttpServletRequest request, Page page){
		_execute(page, request, model);
		List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountApiConfigService.getCdiscountApiConfigPage(page);
		model.addAttribute("list", cdiscountApiConfigList);
		return "cdiscount/cdiscountApiConfigManage";
	}
	
	@RequestMapping("saveCdiscountApiConfig")
	@ResponseBody
	public String saveCdiscountApiConfig(CdiscountApiConfig cdiscountApiConfig) {
		Integer id = cdiscountApiConfig.getId();
		if (null != id) {
			cdiscountApiConfigService.updateCdiscountApiConfige(cdiscountApiConfig);
		} else {
			cdiscountApiConfigService.insertCdiscountApiConfig(cdiscountApiConfig);
		}
		return "T";
	}
}
