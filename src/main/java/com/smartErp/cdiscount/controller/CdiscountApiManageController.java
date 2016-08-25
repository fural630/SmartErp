package com.smartErp.cdiscount.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountApiManageController {
	
	private CdiscountApiConfigService cdiscountApiConfigService;
	
	
	@RequestMapping("cdiscountApiConfigManage")
	public String cdiscountApiConfigManage(Model model, HttpServletRequest request, Page page){
		String requestUrl = request.getRequestURI();
		MyLocale myLocale = new MyLocale();
		model.addAttribute("title", myLocale.getText("navigator.cdiscount.api.config"));
		model.addAttribute("page", page);
		model.addAttribute("requestUrl", requestUrl);
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
