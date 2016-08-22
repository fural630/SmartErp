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
  	<input type="hidden" id="moduleAjaxTableUrl" value="/system/getUserManageCollection"/>
  	<input type="hidden" id="moduleUrl" value="${requestUrl}"/>
	<div class="current_nav_name clearfix">${title}
		<div class="fr small_size"> <a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="userManageTable">
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
	          <tr class="conditionTr">
	          	<td style="text-align:left;"></td>
	          	<td style="text-align:left;">
	          		<ul>
	          			<li><label>从：</label><input type="text" class="txt width_20px" name="id[from]" /></li>
	          			<li><label>到：</label><input type="text" class="txt width_20px" name="id[to]" /></li>
	          		</ul>
	          	</td>
	          	<td style="text-align:left;">
	          		<ul>
	          			<li><input type="text" class="txt width_80px" name="name" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找"></li>
	          		</ul>
	          	</td>
	          	<td style="text-align:left;">
	          		<ul>
	          			<li><input type="text" class="txt width_80px" name="username" /></li>
	          			<li></li>
	          		</ul>
	          	</td>
	          	<td style="text-align:left;">
	          		<ul>
	          			<li><input type="text" class="width_80px main_input_search select_filter" name="" value="输入过滤" /></li>
	          			<li>
		          			<select class="sel">
								<option>请选择</option>
								<option>男</option>
								<option>女</option>
							</select>
						</li>
					</ul>	
	          	</td>
	          	<td style="text-align:left;">
	          		<ul>
	          			<li>
	          				<label>从：</label>
	          				<input type="text" class="txt width_80px main_data_time" name="birthday[from]" id="datepicker" />
	          			</li>
	          			<li>
	          				<label>到：</label>
	          				<input type="text" class="txt width_80px" name="birthday[to]" />
	          			</li>
	          		</ul>
	          	</td>
	          	<td style="text-align:left;"></td>
	          	<td style="text-align:left;"></td>
	          	<td style="text-align:left;"></td>
	          </tr>
          	  <#list userList as user>
	      		 <tr>
		            <td><input name="main_page_checkbox" type="checkbox" value="${user.id}" onclick="countCheckbox()" /></td>
		            <td>${user.id}</td>
		            <td>${user.name}</td>
		            <td>${user.username}</td>
		            <td></td>
		            <td></td>
		            <td>${user.phone}</td>
		            <td>
		            	<a href="javascript:void(0)" onclick="showLog(this)"><img src="/design/static/images/common/system-log.png"/></a>
		            	<div class="log_content">
		            		【1、于2016-08-20 00:24 由超级管理员创建信息】<br/>
		            	</div>
		            </td>
		            <td style="width:60px;">
					 <div class="menu">
					  <ul>
					    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)">操作</a>
					      <ul class="menu_ul">
							<li><a href="javascript:void(0)" onclick="" >编辑 </a></li>
					        <li><a href="javascript:void(0)" onclick="" >删除 </a></li>
					      </ul>
					    </li>
					  </ul>
					</div>
		            </td>
		          </tr>
          	</#list>
	      </table>
	      
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
	    </div>
	  </div>
	</div>
  </body>
</html>
