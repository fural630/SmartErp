package com.smartErp.code;

import org.springframework.ui.Model;

import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

public class MainPage {
	
	public void _execute(Page page, Model model, String title) {
		MyLocale myLocale = new MyLocale();
		User user = UserSingleton.getInstance().getUser();
		page.getParams().put("userId", user.getId());
		model.addAttribute("loginUserName", user.getName());
		model.addAttribute("loginUserId", user.getId());
		model.addAttribute("title", myLocale.getText(title));
		model.addAttribute("page", page);
//		Dumper.dump(page);
	}
}
