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
	<title>左侧菜单</title>
	<link rel="stylesheet" type="text/css" href="design/frame/style/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="design/frame/style/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="design/frame/style/css/sidebar.css"/>
	<script type="text/javascript" src="design/frame/style/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="design/frame/style/js/common.js"></script>
	<script type="text/javascript" src="design/frame/style/js/sidebar.js"></script>
	<script type="text/javascript">
	$(function(){
	   $(".nav").accordion({
	        speed: 500,
		    closedSign: '+',
			openedSign: '-'
		});
	}); 
</script>
</head>
<body>
	<dl class="sidebar clearfix">
	<dd class="menutitle"><img src="design/frame/style/img/mac.png"/>导 航 菜 单</dd>
	<dd>
		<ul class="nav">
		<!-- 
			<li>
			<a href="#">工作管理</a>
				<ul>
					<li><a href="javascript:void(0);" onClick="parent.frames['mainFrame'].location='../function/工时管理/__工时填写.html'">工时填写</a></li>
					<li><a href="javascript:void(0);" onClick="parent.frames['mainFrame'].location='../function/工时管理/__工作计划.html'">工作计划</a></li>
					<li><a href="javascript:void(0);" onClick="parent.frames['mainFrame'].location='../function/工时管理/__工作总结.html'">工作总结</a></li>
				</ul>
			</li>
		 -->
			<li><a href="#">系统管理</a>
				<ul class="clearfix">
					<li><a href="javascript:void(0);" onclick="parent.frames['mainFrame'].location='system/getUserManagerList'">用户管理</a></li>
				</ul>
			</li>
		</ul>
	</dd>
	</dl>
  </body>
</html>
