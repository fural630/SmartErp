package com.smartErp.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.smartErp.util.frame.Page;

public class MainPage {
	
	public String _execute(Page page, HttpServletRequest request, List<Map<String, Object>> resultList) {
		Gson gson = new Gson();
		String requestUrl = request.getRequestURI();
		ServletContext servletContext = request.getServletContext();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", page);
		resultMap.put("collection", resultList);
		String resultJson = gson.toJson(resultMap);
		return resultJson;
	}
}
