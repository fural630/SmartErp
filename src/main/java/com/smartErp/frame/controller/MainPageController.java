package com.smartErp.frame.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.system.model.User;

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
	public String login(HttpServletRequest request, String username, String password) {
		User user = new User();
		request.getSession().setAttribute("user", user);
		return "redirect:/SmartErp/home"; 
	}
	
}
