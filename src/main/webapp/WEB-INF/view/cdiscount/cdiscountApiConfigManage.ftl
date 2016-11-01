<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!""}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/cdiscount/cdiscountApiConfigManage.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/cdiscountApiConfigManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix">${title!""}
		<div class="fr small_size"> 
			<a class="btn" onclick="showCreateApiConfigDialog('添加授权店铺')">
				<img src="/design/frame/style/img/add.png"/>添加授权店铺
			</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="cdiscountApiConfigTable">
	          <tr>
	            <th>店铺名称</th>
	            <th>API账号</th>
	            <th>邮箱</th>
	            <th>是否开启</th>
	            <th>创建人</th>
	            <th>最近修改时间</th>
	            <th>创建时间</th>
	            <th>日志</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td>
	          		<ul>
	          			<li>
		          			<#if page.params.apiId??> 
	          					<@select id="apiId" name="params[apiId]" selected="${page.params.apiId}" optionClass="CdiscountShopName"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="apiId" name="params[apiId]"  optionClass="CdiscountShopName"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
	          			</li>
	          			<li></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[apiAccount]" value="${page.params.apiAccount!''}"/></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[apiAccountLike]" <#if page.params.apiAccountLike??> checked </#if> /></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[email]" value="${page.params.email!""}"/></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[emailLike]" <#if page.params.emailLike??> checked </#if> /></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.closeStatus??> 
	          					<@select id="closeStatus" name="params[closeStatus]" selected="${page.params.closeStatus}" optionClass="YesNo"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="closeStatus" name="params[closeStatus]"  optionClass="YesNo"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td width="200">
	          		<ul>
	          			<li></li>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label>从：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[lastUpdateTimeFrom]" value="${page.params.lastUpdateTimeFrom!""}" />
	          			</li>
	          			<li>
	          				<label>到：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[lastUpdateTimeTo]" value="${page.params.lastUpdateTimeTo!""}" />
	          			</li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label>从：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createDateFrom]" value="${page.params.createDateFrom!""}" />
	          			</li>
	          			<li>
	          				<label>到：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createDateTo]" value="${page.params.createDateTo!""}" />
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
				            <td>${obj.shopName}</td>
				            <td>${obj.apiAccount}</td>
				            <td>${obj.email}</td>
				            <td><@matchValue key="${obj.closeStatus}" optionClass="YesNo"/></td>
				            <td>${obj.creatorName}</td>
				            <td>${obj.lastUpdateTime}</td>
				            <td>${obj.createDate!""}</td>
				            <td>
				            	<a href="javascript:void(0)" onclick="showLog(this)"><img src="/design/static/images/common/system-log.png"/></a>
				            	<div class="log_content">
				            		${obj.systemLog!""}
				            	</div>
				            </td>
				            <td style="width:60px; text-align:center;" >
							 <div class="menu">
							  <ul>
							    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)">操作</a>
							      <ul class="menu_ul">
									<li><a href="javascript:void(0)" onclick="editCdiscountApiConfig(${obj.id});" >编辑 </a></li>
							        <li><a href="javascript:void(0)" onclick="deleteCdiscountApiConfig(${obj.id})" >删除 </a></li>
							      	<li><a href="javascript:void(0)" onclick="updateShopConfig(${obj.id})">更新店铺运费模板</a></li>
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
				<div class="massaction">
				</div>
			</div>
	    </div>
	  </div>
	</div>
	
	<div id="cdiscountApiConfigDialog" style="display:none;">
	<form id="cdiscountApiConfigDialogForm">
		<input type="hidden" name="id"/>
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">店铺名<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="shopName" required minlength="2"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">邮箱<i class="star">*</i></td>
	 			<td><input type="email" class="txt width_50" name="email" email required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">API账号<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_70" name="apiAccount" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">API密码<i class="star">*</i></td>
	 			<td><input type="password" class="txt width_70" name="apiPassword" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">是否开启<i class="star">*</i></td>
	 			<td>
	 				<@select name="closeStatus" cssClass="sel width_100px" id="closeStatus" selected="1"  optionClass="YesNo"/>
	 			</td>
	 		</tr>
	 		
	 		<tr>
	 			<td class="title width_100px">收款邮箱</td>
	 			<td><input type="text" class="txt width_70"  name="receivablesEmail" /></td>
	 		</tr>
	 	</table>
	 	<div style="padding : 5px; text-align:right;">
	 		<input type="checkbox" name="mastRead"/>&nbsp;勾选确认已阅读<a href="javascript:void(0);" style="color:blue;"  onclick="openMastReadDialog()">《授权须知》</a>，并同意授权。
	 	</div>
	 </form>
	</div>
	
	<div id="mastReadDialog" class="hide" title="授权须知">
		<h2>授权须知</h2>
	</div>
  </body>
</html>
