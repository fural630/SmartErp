<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!""}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/frame/jquery/combo/js/jquery.combo.select.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/frame/jquery/combo/css/combo.select.css"/>
	<link rel="stylesheet" type="text/css" href="/design/static/css/cdiscount/cdiscountApiConfigManage.css"/>
	<script src="/design/static/js/app/cdiscount/cdiscountApiConfigManage.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/cdiscountApiConfigManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix">${title!""}
		<div class="fr small_size"> 
			<a class="btn" onclick="showCreateApiConfigDialog()">
				<img src="/design/frame/style/img/add.png"/>新增
			</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="cdiscountApiConfigTable">
	          <tr>
	          	<th width="80px;">ID</th>
	            <th>店铺名称</th>
	            <th>API账号</th>
	            <th>关闭状态</th>
	            <th>创建人</th>
	            <th>创建时间</th>
	            <th>日志</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_40px" name="params[id]" value="${page.params.id!""}" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="idLike"></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="shopName" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="shopNameLike"></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="sku" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="skuLike"></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="width_100px main_input_search select_filter" name="" value="输入过滤" /></li>
	          			<li>
		          			<@select name="params[closeStatus]" cssClass="sel width_100px" id="closeStatus" selected="2"  optionClass="CdiscountPublishStatus" headerKey="" headerValue=""/>
						</li>
					</ul>	
	          	</td>
	          	<td width="200">
	          		<ul>
	          			<li><@select name="params[sex]" cssClass="sel width_100px" id="test" selected="2"  optionClass="" optionClass="CdiscountPublishStatus" headerKey="" headerValue=""/></li>
	          			<li>
						</li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label>从：</label>
	          				<input type="text" class="txt width_80px main_data_time" name="birthdayFrom" id="datepicker" />
	          			</li>
	          			<li>
	          				<label>到：</label>
	          				<input type="text" class="txt width_80px" name="birthdayTo" />
	          			</li>
	          		</ul>
	          	</td>
	          	<td></td>
	          	<td></td>
	          </tr>
	          </form>
	          <#if (list?size > 0)>
		          <#list list as cdiscountApiConfig>
			          <tr>
				            <td>${cdiscountApiConfig.id}</td>
				            <td>${cdiscountApiConfig.shopName}</td>
				            <td>${cdiscountApiConfig.apiAccount}</td>
				            <td><@matchValue key="${cdiscountApiConfig.closeStatus}" optionClass="YesNo"/></td>
				            <td>${cdiscountApiConfig.creator}</td>
				            <td>${cdiscountApiConfig.lastUpdateTime}</td>
				            <td>
				            	<a href="javascript:void(0)" onclick="showLog(this)"><img src="/design/static/images/common/system-log.png"/></a>
				            	<div class="log_content">
				            		${cdiscountApiConfig.systemLog!""}
				            	</div>
				            </td>
				            <td style="width:60px; text-align:center;" >
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
		<input type="text" name="id"/><input type="text" name="action"/>
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">店铺名<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="shopName"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">邮箱<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="email"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">API账号<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_96" name="apiAccount"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">API密码<i class="star">*</i></td>
	 			<td><input type="password" class="txt width_96" name="apiPassword"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px">收款邮箱</td>
	 			<td><input type="text" class="txt width_96"  name="receivablesEmail"/></td>
	 		</tr>
	 	</table>
	 	<div style="padding : 5px; text-align:right;">
	 		<input type="checkbox"/>&nbsp;勾选确认已阅读<a href="#" style="color:blue">《授权须知》</a>
	 	</div>
	</div>
  </body>
</html>
