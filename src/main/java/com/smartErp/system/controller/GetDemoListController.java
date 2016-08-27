package com.smartErp.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.smartErp.code.MainPage;
import com.smartErp.system.model.User;
import com.smartErp.system.service.UserService;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class GetDemoListController extends MainPage{
	@Autowired
	private UserService userService;
	
	@RequestMapping("getDemoList")
	public String getUserManagerList(Model model, HttpServletRequest request){
//		_execute(model, request);
//		Dumper.dump(page);
//		Dumper.dump(request.getParameterMap());
//		
//		String requestUrl = request.getRequestURI();
//		List<User> userList = userService.getUserPage(page);
		MyLocale myLocale = new MyLocale();
		model.addAttribute("title", myLocale.getText("navigator.demo"));
//		model.addAttribute("page", page);
//		model.addAttribute("userList", userList);
//		model.addAttribute("requestUrl", requestUrl);
		return "system/demo";
	}
	
}
