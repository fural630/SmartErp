package com.smartErp.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
	
	@RequestMapping("main")
	public String goToMainPage() {
		
		return "frame/main";
	} 
	
}
