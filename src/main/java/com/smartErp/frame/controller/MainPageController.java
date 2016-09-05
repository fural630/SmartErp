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
	
	@RequestMapping("loginForm")
	public String goToLoginPage() {
		
		return "frame/loginForm";
	} 
	
	@RequestMapping("login")
	public String login(String username, String password) {
		
		System.out.println(username);
		System.out.println(password);
		
		return "redirect:/SmartErp/home"; 
	}
	
}
