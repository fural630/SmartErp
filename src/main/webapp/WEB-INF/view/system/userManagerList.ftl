<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/system/userManagerList.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<input type="hidden" id="moduleUrl" value="${requestUrl}"/>
	<div class="current_nav_name clearfix">${title}
		<div class="fr small_size"> <a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <!-- tableview start -->
	  <div class="tableview clearfix">
	    <div class="content">
	      <table class="tb_border tb_full stripe">
	          <tr>
	          	<th></th>
	          	<th>ID</th>
	            <th width="100px;">姓名</th>
	            <th>账号</th>
	            <th>性别</th>
	            <th width="230px;">级别</th>
	            <th>电话</th>
	            <th>日志</th>
	            <th>操作</th>
	          </tr>
	          <tr>
	          	<td></td>
	          	<td>
	          		<label>从：</label><input type="text" class="txt width_20px params_margin_5px" name="params.name" /><br/>
	          		<label>到：</label><input type="text" class="txt width_20px params_margin_5px" name="params.name" /><br/>
	          	</td>
	          	<td>
	          		<input type="text" class="txt width_80px params_margin_5px" name="params.name" />
	          		<input type="checkbox"> * 
	          	</td>
	          	<td>
	          		<input type="text" class="txt width_80px params_margin_5px" name="" />
	          	</td>
	          	<td>
	          		<input type="text" class="txt width_80px params_margin_5px" name="" value="搜索" /><br/>
	          		<select class="sel">
						<option>请选择</option>
						<option>男</option>
						<option>女</option>
					</select>
	          	</td>
	          	<td>
	          		<label>从：</label>
	          		<input type="text" class="txt width_80px params_margin_5px" name="params.name" id="datepicker" />
	          		<br/>
	          		<label>到：</label>
	          		<input type="text" class="txt width_80px params_margin_5px" name="params.name" /><br/>
	          	</td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          </tr>
          	  <#list userList as user>
	      		 <tr>
		            <td><input name="test1" type="checkbox" value="${user.id}" /></td>
		            <td>${user.id}</td>
		            <td>${user.name}</td>
		            <td>${user.username}</td>
		            <td><input type="text" class="txt width_80px params_margin_5px" name="params.name"  /></td>
		            <td></td>
		            <td>${user.phone}</td>
		            <td>
		            	<a href="javascript:void(0)"><img name="log_notepad" src="/design/static/images/common/notepad_32px.png"/></a>
		            	<div class="log_content">
		            		【1、于2016-08-20 00:24 由超级管理员创建信息】<br/>
		            	</div>
		            </td>
		            <td>
		            	<div name="option_btn">
			            	<a class="btn">操作</a>
			            	<div class="option_menu">
			            		<ul>
			            			<li><a href="javascript:void(0)" class="option_menu_a">编辑</a></li>
			            			<li><a href="javascript:void(0)" class="option_menu_a">删除</a></li>
			            			<li><a href="javascript:void(0)" class="option_menu_a">修改状态</a></li>
			            		</ul>
			            	</div>
		            	</div>
		            </td>
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
