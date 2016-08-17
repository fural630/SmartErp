package com.smartErp.code;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class MainPage {
	
	private String xmlTemplatePath = "design/layout/block/main";
	
	public void _execute(Model model, HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		ServletContext servletContext = request.getServletContext();
		String appPath = ""; 
		appPath = servletContext.getRealPath("/");
		String mainPageXml = (appPath + xmlTemplatePath + requestUrl).replace("/", "\\");		//windows
//		String mainPageXml = (appPath + xmlTemplatePath + requestUrl);		//linux
		String xmlName = mainPageXml + ".xml";
		XmlReader xmlReader = new XmlReader(xmlName);
		xmlReader.prase();
	}
}
