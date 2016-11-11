<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><@s.message "navigator.system.prompt.manage"/></title>
	<#include "../frame/common.ftl"/>
	<script src="/design/frame/ckeditor/ckeditor.js"></script>
	<script src="/design/static/js/app/system/systemPromptManage.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/system/systemPromptManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix"><@s.message "navigator.system.prompt.manage"/>
		<div class="fr small_size"> 
			<a class="btn" onclick="showSystemPromptConfigDialog('<@s.message "create.system.prompt"/>')">
				<img src="/design/frame/style/img/add.png"/><@s.message "create.system.prompt"/>
			</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	      <table class="tb_border tb_full stripe" id="systemPromptTable" name="pageTable">
	          <tr>
	            <th>ID</th>
	            <th><@s.message "title"/></th>
	            <th><@s.message "belong.module"/></th>
	            <th><@s.message "create.time"/></th>
	            <th><@s.message "update.time"/></th>
	            <th><@s.message "operating"/></th>
	          </tr>
	          <tr class="conditionTr">
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[id]" value="${page.params.id!''}"/></li>
	          			<li></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[title]" value="${page.params.title!''}"/></li>
	          			<li>*&nbsp;<input type="checkbox" title="<@s.message 'check.to.enable.blur.search'/>" name="params[titleNameLike]" <#if page.params.titleLike??> checked </#if> /></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[belongModule]" value="${page.params.belongModule!''}"/></li>
	          			<li>*&nbsp;<input type="checkbox" title="<@s.message 'check.to.enable.blur.search'/>" name="params[belongModuleLike]" <#if page.params.belongModuleLike??> checked </#if> /></li>
	          		</ul>
	          	</td>
	            <td>
	          		<ul>
	          			<li>
	          				<label><@s.message "from"/>：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createTimeFrom]" value="${page.params.createTimeFrom!""}" />
	          			</li>
	          			<li>
	          				<label><@s.message "to"/>：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createTimeTo]" value="${page.params.createTimeTo!""}" />
	          			</li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label><@s.message "from"/>：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createTimeFrom]" value="${page.params.createTimeFrom!""}" />
	          			</li>
	          			<li>
	          				<label><@s.message "to"/>：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createTimeTo]" value="${page.params.createTimeTo!""}" />
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
				            <td>${obj.scriptName!""}</td>
				            <td>${obj.remark!""}</td>
				            <td><@matchValue key="${obj.scriptType}" optionClass="ScriptType"/></td>
				            <td><@matchValue key="${obj.randomRange!''}" optionClass="RandomRange"/></td>
				            <td><@matchValue key="${obj.isOpened}" optionClass="OpenClose"/></td>
				            <td>${obj.createName!""}</td>
				            <td>
				            	<@s.message "create.time"/>:<br/>${obj.createTime}<br/>
								<@s.message "update.time"/>:<br/>${obj.updateTime}
							</td>
				            <td style="width:60px; text-align:center;" >
							 <div class="menu">
							  <ul>
							    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)"><@s.message "operating"/></a>
							      <ul class="menu_ul">
									<li><a href="javascript:void(0)" onclick="editScriptConfig(${obj.id});" ><@s.message "edit"/></a></li>
							        <li><a href="javascript:void(0)" onclick="deleteScriptConfig(${obj.id})" ><@s.message "delete"/></a></li>
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
	
	<div id="systemPromptConfigDialog" style="display:none;">
		<input type="hidden" name="id"/>
		<form id="systemPromptConfigDialogForm">
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px"><@s.message "title"/><i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="title" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px"><@s.message "height"/><i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px" value="310" name="height" required/>&nbsp;px</td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px"><@s.message "width"/><i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px" value="430" name="width" required/>&nbsp;px</td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px"><@s.message "prompt.address"/></td>
	 			<td>
	 				<textarea class="txt width_96 remark" name="address" style="height:50px;"></textarea>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title width_100px"><@s.message "prompt.content"/><i class="star">*</i></td>
	 			<td>
	 				<textarea class="txt width_96 remark" id="content" name="content"></textarea>
	 			</td>
	 		</tr>
	 	</table>
	 	</form>
	</div>
	
  </body>
</html>
