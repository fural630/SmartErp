package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.code.MainPage;
import com.smartErp.system.service.UserService;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class UserManagerController extends MainPage{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("userManage")
	public String userManage(Model model, HttpServletRequest request, Page page){
		String title = "navigator.user.manage";
		_execute(page, request, model, title);
		List<Map<String, Object>> collection = userService.getUserPage(page);
		model.addAttribute("collection", collection);
		return "system/userManage";
	}
}
