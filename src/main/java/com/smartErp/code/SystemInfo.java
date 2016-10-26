package com.smartErp.code;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SystemInfo
/*    */ {
/*    */   public SystemInfo() {}
/*    */   
/*    */   public static String getAppPath()
/*    */   {
/*    */     String appPath;
/*    */     try
/*    */     {
				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
			    ServletContext servletContext = webApplicationContext.getServletContext();  
/* 28 */       	appPath = servletContext.getRealPath("/");
/* 29 */       if (!appPath.endsWith("/")) {
/* 30 */         appPath = appPath + "/";
/*    */       }
/*    */     } catch (Exception e) {
/* 33 */       appPath = "/project/jtomtoperp/web/";
/*    */     }
/*    */     
/* 36 */     return appPath;
/*    */   }
/*    */   
/*    */   public static String getScriptPath() {
/* 46 */     return getAppPath() + "WEB-INF/classes/com/smartErp/script/";
/*    */   }
/*    */   
/*    */   public static String getClassesPath() {
/* 50 */     return getAppPath() + "WEB-INF/classes/";
/*    */   }
/*    */   
/*    */   public static String getTomtopPath() {
/* 54 */     return getAppPath() + "WEB-INF/classes/com/smartErp/";
/*    */   }
/*    */ }