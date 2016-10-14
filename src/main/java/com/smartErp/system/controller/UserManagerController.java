package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.print.resources.serviceui;

import com.smartErp.code.MainPage;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.User;
import com.smartErp.system.service.UserService;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.code.MyLocale;
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
	
	@RequestMapping("saveUser")
	@ResponseBody
	public String saveUser (User user) {
		MyLocale myLocale = new MyLocale();
		ReturnMessage returnMessage = new ReturnMessage();
		if (null != user) {
			boolean isExist = userService.checkAccountExist(user.getUsername());
			if (null == user.getId()) {
				if (isExist) {
					returnMessage.setStatus(0);
					returnMessage.setMessage(myLocale.getText("account.is.exist.try.other.one"));
				} else {
					userService.insertUser(user); 
				}
			} else {
				if (isExist) {
					User oldUser = userService.getUserById(user.getId());
					if (oldUser.getUsername().equals(user.getUsername())) {
						userService.updateUser(user);
					} else {
						returnMessage.setStatus(0);
						returnMessage.setMessage(myLocale.getText("account.is.exist.try.other.one"));
					}
				} else {
					userService.updateUser(user);
				}
			}
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("getUserInfo")
	@ResponseBody
	public String getUserInfo (Integer id) {
		User user = userService.getUserById(id);
		user.setPassword(DESEncrypt.DataDecrypt(user.getPassword()));
		return JsonUtil.toJsonStr(user);
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public String deleteUser (Integer id) {
		userService.deleteUserById(id);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	} 
}
