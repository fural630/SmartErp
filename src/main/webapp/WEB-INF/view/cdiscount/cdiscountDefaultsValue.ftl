<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!""}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/cdiscount/cdiscountDefaultsValue.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/static/css/cdiscount/cdiscountDefaultsValue.css"/>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/cdiscountDefaultsValue" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix">${title!""}
		<div class="fr small_size"> 
			<a class="btn" onclick="showDefaultsValueDialog('添加默认值模板')">
				<img src="/design/frame/style/img/add.png"/>添加默认值模板
			</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="cdiscountApiConfigTable" name="pageTable">
	          <tr>
	            <th>模板名称</th>
	            <th>是否默认</th>
	            <th>品牌名</th>
	            <th>数量</th>
	            <th>VAT</th>
	            <th>DEA</th>
	            <th>Eco Part</th>
	            <th>备货时间</th>
	            <th>运费模板</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td>
	          		<ul>
	          			<li></li>
	          			<li></li>
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
				            <td></td>
				            <td>${obj.apiAccount}</td>
				            <td>${obj.email}</td>
				            <td><@matchValue key="${obj.closeStatus}" optionClass="YesNo"/></td>
				            <td>${obj.creatorName}</td>
				            <td>${obj.lastUpdateTime}</td>
				            <td>${obj.createDate!""}</td>
				            <td>
				            	<table class="width_100">
				            		<tr class="normal">
				            			<td class="title" style="width:33%">物流方式</td>
				            			<td class="title">运费(€)</td>
				            			<td class="title">额外运费(€)</td>
				            		</tr>
				            		<tr class="normal">
				            			<td>Standard</td>
				            			<td>0</td>
				            			<td>5</td>
				            		</tr>
				            		<tr class="normal">
				            			<td>Tracked</td>
				            			<td>0</td>
				            			<td>8</td>
				            		</tr>
				            		<tr class="normal">
				            			<td>Registered</td>
				            			<td>0</td>
				            			<td>10</td>
				            		</tr>
				            	</table>
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
	
	<div id="cdiscountDefaultsValueDialog" style="display:none;">
	<form id="cdiscountDefaultsValueDialogForm">
		<input type="hidden" name="id"/>
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">模板名称<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="templateName" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">品牌名</td>
	 			<td><input type="text" class="txt width_50" id="brandName"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">数量</td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="stockQty" name="stockQty" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">VAT(%)</td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="vat"  name="vat" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">DEA(€)</td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="dea" name="dea" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">Eco part(€)</td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="ecoPart"  name="ecoPart" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">运费信息</td>
	 			<td>
	 				<table class="width_100 devaultsShippingCharges">
	            		<tr>
	            			<td class="title" style="width:33%">物流方式</td>
	            			<td class="title">运费(€)</td>
	            			<td class="title">额外运费(€)</td>
	            		</tr>
	            		<tr>
	            			<td>Standard</td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr>
	            			<td>Tracked</td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr>
	            			<td>Registered</td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		
	            		<tr>
	            			<td>BigParcelEco</td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		
	            		<tr>
	            			<td>BigParcelStandard</td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr>
	            			<td>BigParcelComfort</td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
            		</table>
	 			</td>
	 		</tr>
	 	</table>
	 </form>
	</div>
	
  </body>
</html>
