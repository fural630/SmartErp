package com.smartErp.cdiscount.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountManageController {
	
	@RequestMapping("cdiscountPublishManage")
	public String cdiscountPublishManage(Model model, HttpServletRequest request, Page page){
		String requestUrl = request.getRequestURI();
		MyLocale myLocale = new MyLocale();
		model.addAttribute("title", myLocale.getText("navigator.cdiscount.publish.manage"));
		model.addAttribute("page", page);
		model.addAttribute("requestUrl", requestUrl);
		return "cdiscount/cdiscountPublishManage";
	}
}
