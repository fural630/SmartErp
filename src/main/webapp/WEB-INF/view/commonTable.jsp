<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>右侧主体内容</title>
	<jsp:include page="../frame/common.jsp" flush="true" />
  </head>
  
  <body>
  	<jsp:include page="../frame/header.jsp" flush="true" />
  
	<div class="mainbody clearfix"> 
	  <!--locationTime start-->
	  <div class="current_nav_name clearfix">用户信息管理</div>
	  <jsp:include page="../frame/page.jsp" flush="true" />
	  
	  <!--locationTime end--> 
	  <!-- tableview start -->
	  <div class="tableview clearfix">
	    <div class="heading clearfix">
	      <h3 class="fl"></h3>
	      <a class="expand fr"></a>
	    </div>
	    <div class="content">
	      <div class="filters clearfix">
	        <div class="query fl">
	          <div class="query_inner">
	            <ul class="query_ul clearfix">
	              <li>
	                <label>角色名：</label>
	                <input class="txt width_80px" />
	              </li>
	              <li>
	                <label>性别：</label>
	                <select class="sel width_80px">
	                  <option>请选择</option>
	                  <option>男</option>
	                  <option>女</option>
	                </select>
	              </li>
	            </ul>
	          </div>
	        </div>
	        <div class="fr"> <a class="btn"><img src="design/frame/style/img/add.png"/>新增</a> <a class="btn"><img src="design/frame/style/img/edite.png"/>修改</a> <a class="btn"><img src="design/frame/style/img/del.png"/>删除</a> <a class="btn"><img src="design/frame/style/img/view.png"/>查看</a> </div>
	      </div>
	      <table class="tb_border tb_full stripe">
	        <thead>
	          <tr>
	            <th>男方姓名</th>
	            <th>证件号码</th>
	            <th>联系电话</th>
	            <th>住宅电话</th>
	            <th>家庭住址</th>
	            <th>邮政编码</th>
	            <th>操作</th>
	          </tr>
	        </thead>
	        <tbody>
	          <tr>
	            <td><input name="test1" type="checkbox" value="" /></td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td><input name="test1" type="checkbox" value="" /></td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td><input name="test1" type="checkbox" value="" /></td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td>6575981987081465830</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td>6575981987081465830</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td>6575981987081465830</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td>6575981987081465830</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td><input name="test-radio" type="radio" value="" /></td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td><input name="test-radio" type="radio" value="" /></td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	          <tr>
	            <td><input name="test-radio" type="radio" value="" /></td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td>广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区广西壮族自治区南宁市青秀区</td>
	            <td>13000000000</td>
	            <td>13000000000</td>
	            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
	          </tr>
	        </tbody>
	      </table>
	      <jsp:include page="../frame/batchOption.jsp" flush="true" />
	    </div>
	  </div>
	  <!-- tableview end --> 
	</div>
  </body>
</html>
