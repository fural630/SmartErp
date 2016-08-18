<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户信息管理</title>
	<#include "../frame/common.ftl"/>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <!-- tableview start -->
	  <div class="tableview clearfix">
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
	      </div>
	      <table class="tb_border tb_full stripe">
	          <tr>
	          	<th></th>
	          	<th>ID</th>
	            <th>姓名</th>
	            <th>账号</th>
	            <th>性别</th>
	            <th>级别</th>
	            <th>电话</th>
	            <th>操作</th>
	          </tr>
          	<#list userList as user>
	      		 <tr>
		            <td><input name="test1" type="checkbox" value="${user.id}" /></td>
		            <td>${user.id}</td>
		            <td>${user.name}</td>
		            <td>${user.username}</td>
		            <td></td>
		            <td>13000000000</td>
		            <td>${user.phone}</td>
		            <td><a class="operate">查看</a><a class="operate">编辑</a><a class="operate">删除</a><a class="operate">审核</a></td>
		          </tr>
          	</#list>
	      </table>
	      <#include "../frame/batchOption.ftl"/>
	    </div>
	  </div>
	  <!-- tableview end --> 
	</div>
  </body>
</html>
