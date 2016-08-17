package com.smartErp.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("SmartErp")
public class MainPageController {
	
	@RequestMapping("home")
	public String goToMainPage() {
		
		return "frame/main";
	} 
	
}
