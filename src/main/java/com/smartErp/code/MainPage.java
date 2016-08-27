package com.smartErp.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

public class MainPage {
	
	public void _execute(Page page, HttpServletRequest request, Model model) {
		MyLocale myLocale = new MyLocale();
		model.addAttribute("title", myLocale.getText("navigator.cdiscount.api.config"));
		model.addAttribute("page", page);
	}
}
