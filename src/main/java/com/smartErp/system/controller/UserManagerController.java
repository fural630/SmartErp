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
import com.smartErp.system.model.User;
import com.smartErp.system.service.UserService;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class UserManagerController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("getUserManagerList")
	public String getUserManagerList(Model model, HttpServletRequest request, Page page){
//		_execute(model, request);
		Dumper.dump(page);
		Dumper.dump(request.getParameterMap());
		String requestUrl = request.getRequestURI();
		List<Map<String, Object>> userList = userService.getUserPage(page);
		MyLocale myLocale = new MyLocale();
		model.addAttribute("title", myLocale.getText("navigator.user.manage"));
		model.addAttribute("page", page);
		model.addAttribute("userList", userList);
		model.addAttribute("requestUrl", requestUrl);
		return "system/userManagerList";
	}
	
	@RequestMapping("getUserManageCollection")
	@ResponseBody
	public String getUserManageCollection(Page page) {
		Dumper.dump(page);
		List<Map<String, Object>> userList = userService.getUserPage(page);
		Map<String, Object> resutMap = new HashMap<String, Object>();
		resutMap.put("userList", userList);
		resutMap.put("page", page);
		Gson gson = new Gson();
		return gson.toJson(resutMap);
	}
}
