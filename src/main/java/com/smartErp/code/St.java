package com.smartErp.code;

public class SystemInfo
/*    */ {
/*    */   public SystemInfo() {}
/*    */   
/*    */   public static String getAppPath()
/*    */   {
/*    */     String appPath;
/*    */     try
/*    */     {
/* 26 */       ActionContext ac = ActionContext.getContext();
/* 27 */       ServletContext sc = (ServletContext)ac.get("com.opensymphony.xwork2.dispatcher.ServletContext");
/* 28 */       String appPath = sc.getRealPath("/");
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
/*    */   public static String getContextPath() {
/* 40 */     HttpServletRequest request = ServletActionContext.getRequest();
/*    */     
/* 42 */     return request.getContextPath();
/*    */   }
/*    */   
/*    */   public static String getScriptPath() {
/* 46 */     return getAppPath() + "WEB-INF/classes/com/tomtop/script/";
/*    */   }
/*    */   
/*    */   public static String getClassesPath() {
/* 50 */     return getAppPath() + "WEB-INF/classes/";
/*    */   }
/*    */   
/*    */   public static String getTomtopPath() {
/* 54 */     return getAppPath() + "WEB-INF/classes/com/tomtop/";
/*    */   }
/*    */ }