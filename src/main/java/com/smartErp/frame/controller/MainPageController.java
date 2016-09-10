package com.smartErp.frame.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.system.model.User;
import com.smartErp.system.service.UserService;
import com.smartErp.util.code.Dumper;

@Controller
@RequestMapping("SmartErp")
public class MainPageController {
	
	@Autowired
	UserService userService;
	
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
		User user = userService.getUserByUserName(username);
		Dumper.dump(user);
		request.getSession().setAttribute("user", user);
		return "redirect:/SmartErp/home"; 
	}
	
}
