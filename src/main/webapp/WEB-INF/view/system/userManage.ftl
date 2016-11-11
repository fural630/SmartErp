<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><@s.message "navigator.user.manage"/></title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/system/userManage.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/system/userManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix"><@s.message "navigator.user.manage"/>
		<div class="fr small_size"> <a class="btn" onclick="showUserDialog('添加用户')"><img src="/design/frame/style/img/add.png"/>新增</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="userManageTable" name="pageTable">
	          <tr>
	          	<th></th>
	            <th>昵称</th>
	            <th>账号</th>
	            <th>邮箱</th>
	            <th>电话</th>
	            <th>账号状态</th>
	            <th>创建时间</th>
	            <th>日志</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td></td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[name]" value="${page.params.name!''}" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[nameLike]" <#if page.params.nameLike??> checked </#if>></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[username]" value="${page.params.username!''}" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[usernameLike]" <#if page.params.usernameLike??> checked </#if>></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[email]" value="${page.params.email!''}" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[emailLike]" <#if page.params.emailLike??> checked </#if>></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<input type="text" class="txt width_100px" name="params[phone]" value="${page.params.phone!''}" />
	          			</li>
	          			<li></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.status??> 
	          					<@select id="status" name="params[status]" selected="${page.params.status}" optionClass="OpenClose"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="status" name="params[status]"  optionClass="OpenClose"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label>类型：</label>
	          				<#if page.params.timeQuery??> 
	          					<@select id="timeQuery" name="params[timeQuery]" selected="${page.params.timeQuery}" optionClass="TimeQuery"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="timeQuery" name="params[timeQuery]"  optionClass="TimeQuery"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
	          			</li>
						<li>
							<label>从：</label>
							<input type="text" class="txt width_100px datepicker" name="params[timeFrom]" value="${page.params.timeFrom!""}" />
						</li>
						<li>
							<label>到：</label>
							<input type="text" class="txt width_100px datepicker" name="params[timeTo]" value="${page.params.timeTo!""}" />
						</li>
					</ul>
	          	</td>
	          	<td></td>
	          	<td></td>
	          </tr>
	          </form>
          	  <#if (collection?size > 0)>
		          <#list collection as obj>
	      		 <tr>
		            <td style="text-align:center"><input name="main_page_checkbox" type="checkbox" value="${obj.id}" onclick="countCheckbox()" /></td>
		            <td>${obj.name!""}</td>
		            <td>${obj.username!""}</td>
		            <td>${obj.email!""}</td>
		            <td>${obj.phone!""}</td>
		            <td><@matchValue key="${obj.status}" optionClass="OpenClose"/></td>
		            <td>
		            	创建时间:<br/>${obj.createTime!""}<br/>
						修改时间:<br/>${obj.updateTime!""}
		            </td>
		            <td>
		            	<a href="javascript:void(0)" onclick="showLog(this)"><img src="/design/static/images/common/system-log.png"/></a>
		            	<div class="log_content">
		            		${obj.log!""}
		            	</div>
		            </td>
		            <td style="width:60px;text-align:center;">
					 <div class="menu">
					  <ul>
					    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)">操作</a>
					      <ul class="menu_ul">
							<li><a href="javascript:void(0)" onclick="editUserInfo(${obj.id})" >编辑 </a></li>
					        <li><a href="javascript:void(0)" onclick="deleteUser(${obj.id})" >删除 </a></li>
					      </ul>
					    </li>
					  </ul>
					</div>
		            </td>
		          </tr>
	          	</#list>
	          </#if>
	      </table>
	      
			<div class="paging clearfix">
				<div class="massaction"></div>
			</div>
	      
	      <!--
		      <div class="paging clearfix">
				<div class="massaction">
					<table class="tb_common">
						<tr>
							<td style="width:40%" class="td_left">
								<a href="javascript:void(0)" onclick="pageSelectAll();">全选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="pageNoSelectAll();">全不选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="pageUnselected();">反选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								已选择&nbsp;<span id="pageCheckCount">0</span>&nbsp;条
							</td>
							<td class="td_right">操作&nbsp;&nbsp;
								<select class="sel">
										<option id="batchDelete">批量删除</option>
										<option id="batchUpdateStatus">批量修改状态</option>
								</select>
								&nbsp; <a class="btn" onclick="batchOptionSubmit()">提交</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			-->
	    </div>
	  </div>
	</div>
	
	
	<div id="userDialog" style="display:none;">
		<input type="hidden" name="id"/>
		<form id="userDialogFrom">
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">账号<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="username" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">密码<i class="star">*</i></td>
	 			<td><input type="password" class="txt width_50" name="password" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">昵称<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="name" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">邮箱</td>
	 			<td><input type="email" class="txt width_50" name="email" email/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">电话</td>
	 			<td><input type="text" class="txt width_50" name="phone" /></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">账号状态<i class="star">*</i></td>
	 			<td><@select name="status" cssClass="sel width_100px" id="status" selected="1" optionClass="OpenClose"/></td>
	 		</tr>
	 	</table>
	 	</form>
	</div>
  </body>
</html>
