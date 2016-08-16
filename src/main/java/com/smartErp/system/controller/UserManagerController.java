package com.smartErp.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.system.model.User;
import com.smartErp.system.service.UserService;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class UserManagerController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("getUserManagerList")
	public String getUserManagerList(Model model, HttpServletRequest request){
		Page<User> page = new Page<User>();
		String requestUrl = request.getRequestURI();
		List<User> userList = userService.getUserPage(page);
		Dumper.dump(page);
		model.addAttribute("page", page);
		model.addAttribute("userList", userList);
		model.addAttribute("requestUrl", requestUrl);
		return "system/userManagerList";
	}
}