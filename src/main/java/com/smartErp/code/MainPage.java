package com.smartErp.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class MainPage {
	
	public void _execute(Model model, HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		System.out.println("mainpage requestUrl :" + requestUrl);
		System.out.println(request.getServletContext().getRealPath("/"));
	}
}
