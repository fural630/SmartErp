package com.smartErp.frame.controller;

import java.lang.reflect.Method;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.util.code.JsonUtil;

@Controller
@RequestMapping("authenticate")
public class AuthenticateController {
	
	@RequestMapping("runScript")
	@ResponseBody
	public String runScript(String type, String args) { 
		String resultMassage = "";
		if (null == type) {
			resultMassage = "type is null";
			return JsonUtil.toJsonStr(resultMassage);
		}
		String argList = args;
		type = type.replace("/", ".");
		String className = "com.smartErp.script." + type;
		String methodName = "runScript";
		try {
			Class customClass = Class.forName(className);
			Method method = customClass.getMethod(methodName, String.class);
			Object result = method.invoke(customClass.newInstance(), argList);
		} catch (Exception e) {
            e.printStackTrace();
		}
		resultMassage = "Done!";
		System.out.println(resultMassage);
		return JsonUtil.toJsonStr(resultMassage);
	}
}
