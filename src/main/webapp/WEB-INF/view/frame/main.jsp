<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Smart Erp</title>
	<jsp:include page="common.jsp" flush="true" />
</head>
<body>
	<jsp:include page="header.jsp" flush="true" />

</body>



<!-- 
<frameset rows="17,193" cols="*" border="0">
	<frame src="header" />
	<frameset border="0">
		<frame src="center" name="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>

 -->
</html>
