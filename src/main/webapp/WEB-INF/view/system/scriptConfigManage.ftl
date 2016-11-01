<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!""}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/system/scriptConfigManage.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/scriptConfigManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix">${title!""}
		<div class="fr small_size"> 
			<a class="btn" onclick="showCreateApiConfigDialog('添加脚本配置')">
				<img src="/design/frame/style/img/add.png"/>添加脚本配置
			</a>
			<a class="btn" onclick="showCreateApiConfigDialog('添加脚本配置')">
				脚本生成
			</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="scriptConfigTable" name="pageTable">
	          <tr>
	            <th width="120px;">crontab表达式</th>
	            <th>脚本地址</th>
	            <th>脚本类型</th>
	            <th>随机数范围</th>
	            <th>是否开启</th>
	            <th>创建人</th>
	            <th>脚本备注</th>
	            <th width="180px;">时间</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td></td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[scriptUrl]" value="${page.params.scriptUrl!''}"/></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[scriptUrlLike]" <#if page.params.scriptUrlLike??> checked </#if> /></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.scriptType??> 
	          					<@select id="scriptType" name="params[scriptType]" selected="${page.params.scriptType}" optionClass="ScriptType"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="scriptType" name="params[scriptType]"  optionClass="ScriptType"  cssClass="sel width_80px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.randomRange??> 
	          					<@select id="randomRange" name="params[randomRange]" selected="${page.params.randomRange}" optionClass="RandomRange"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="randomRange" name="params[randomRange]"  optionClass="RandomRange"  cssClass="sel width_80px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.isOpened??> 
	          					<@select id="isOpened" name="params[isOpened]" selected="${page.params.isOpened}" optionClass="OpenClose"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="isOpened" name="params[isOpened]"  optionClass="OpenClose"  cssClass="sel width_50px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td></td>
	          	<td></td>
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
	          </tr>
	          </form>
	          <#if (collection?size > 0)>
		          <#list collection as obj>
			          <tr>
				            <td>${obj.crontab}</td>
				            <td>${obj.scriptUrl}</td>
				            <td><@matchValue key="${obj.scriptType}" optionClass="ScriptType"/></td>
				            <td><@matchValue key="${obj.randomRange}" optionClass="RandomRange"/></td>
				            <td><@matchValue key="${obj.isOpened}" optionClass="OpenClose"/></td>
				            <td>${obj.createName!""}</td>
				            <td>${obj.remark!""}</td>
				            <td>
				            	创建时间:<br/>${obj.createTime}<br/>
								修改时间:<br/>${obj.updateTime}
							</td>
				            <td style="width:60px; text-align:center;" >
							 <div class="menu">
							  <ul>
							    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)">操作</a>
							      <ul class="menu_ul">
									<li><a href="javascript:void(0)" onclick="editScriptConfig(${obj.id});" >编辑 </a></li>
							        <li><a href="javascript:void(0)" onclick="deleteScriptConfig(${obj.id})" >删除 </a></li>
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
	
	<div id="scriptConfigDialog" style="display:none;">
	<form id="scriptConfigDialogForm">
		<input type="hidden" name="id"/>
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">店铺名<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="shopName" required minlength="2"/></td>
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
	 </form>
	</div>
	
  </body>
</html>
