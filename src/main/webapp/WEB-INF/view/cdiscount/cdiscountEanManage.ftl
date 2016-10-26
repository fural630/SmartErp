<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/cdiscount/cdiscountEan.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/static/css/cdiscount/cdiscountEanManage.css"/>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/cdiscountEanManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix">${title}
		<div class="fr small_size">
			<a class="btn" onclick="showCdiscountEanDialog('批量导入EAN')"><img src="/design/frame/style/img/add.png"/>添加EAN</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	      <table class="tb_border tb_full stripe" id="cdiscountEanManageTable" name="pageTable">
	          <tr>
	          	<th></th>
	            <th width="140px;">店铺名称</th>
	            <th width="180px;">SKU</th>
	            <th width="180px;">EAN</th>
	            <th>是否已使用</th>
	            <th>使用时间</th>
	            <th>创建时间</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td></td>
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
	          			<li><input type="text" class="txt width_100px" name="params[sku]" value="${page.params.sku!''}" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="params[skuLike]" <#if page.params.skuLike??> checked </#if>></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[ean]" value="${page.params.ean!''}" /></li>
	          			<li></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.closeStatus??> 
	          					<@select id="isUsed" name="params[isUsed]" selected="${page.params.isUsed}" optionClass="YesNo"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="isUsed" name="params[isUsed]"  optionClass="YesNo"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label>从：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[usedTimeFrom]" value="${page.params.usedTime!""}" />
	          			</li>
	          			<li>
	          				<label>到：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[usedTimeTimeTo]" value="${page.params.usedTime!""}" />
	          			</li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<label>从：</label>
	          				<input type="text" class="txt width_100px datepicker" name="params[createTimeFrom]" value="${page.params.createTimeFrom!""}" />
	          			</li>
	          			<li>
	          				<label>到：</label>
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
						<td style="text-align:center"><input name="main_page_checkbox" type="checkbox" value="${obj.id}" onclick="countCheckbox()" /></td>
						<td>${obj.shopName!""}</td>
						<td>${obj.sku!""}</td>
						<td>${obj.ean}</td>
						<td><@matchValue key="${obj.isUsed}" optionClass="YesNo"/></td>
						<td>${obj.usedTime}</td>
						<td>${obj.createTime}</td>
						<td style="width:60px; text-align:center;" >
						 <div class="menu">
						  <ul>
						    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)">操作</a>
						      <ul class="menu_ul">
						        <li><a href="javascript:void(0)" onclick="deleteCdiscountEan(${obj.id})" >删除 </a></li>
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
					<table class="tb_common">
						<tr>
							<td style="width:40%" class="td_left">
								<a href="javascript:void(0)" onclick="pageSelectAll();">全选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="pageNoSelectAll();">全不选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="pageUnselected();">反选</a>&nbsp;&nbsp;|&nbsp;&nbsp;
								已选择&nbsp;<span id="pageCheckCount">0</span>&nbsp;条
							</td>
							<td class="td_right">操作&nbsp;&nbsp;
								<select class="sel" id="batchOptionSelect">
									<option value="" selected></option>
									<option value="batchDelete">批量删除</option>
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
	
	<div id="cdiscountEanDialog" style="display:none;">
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">EAN<i class="star">*</i></td>
	 			<td>
	 				<textarea class="txt width_96 remark" id="eanTextArea" name="eanTextArea" placeholder="每行为一个EAN" style="height:150px;"></textarea>
	 				填写格式：<br/>
	 				45648654713<br/>
	 				32342342343<br/>
	 			</td>
	 		</tr>
	 	</table>
	 	<div class="resultMassage" style="display:none;" id="resultMassage">
		 	<div class="totalResultInfo">导入结果：成功<sapn id="successCount">0</span>条，失败<span id="failCount">0</span>条</div>
		 	<div id="importEanResult" class="importEanResult">
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 		<div>2123123---添加成功</div>
		 		<div>1231233---添加失败，EAN重复</div>
		 	</div>
	 	</div>
	</div>
	
  </body>
</html>
