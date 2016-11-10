<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><@s.message "navigator.cdiscount.defaults.value"/></title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/cdiscount/cdiscountDefaultsValue.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/static/css/cdiscount/cdiscountDefaultsValue.css"/>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/cdiscountDefaultsValue" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix"><@s.message "navigator.cdiscount.defaults.value"/>
		<div class="fr small_size"> 
			<a class="btn" onclick="showDefaultsValueDialog('<@s.message "add.template"/>')">
				<img src="/design/frame/style/img/add.png"/><@s.message "add.template"/>
			</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	      <table class="tb_border tb_full stripe" id="cdiscountDefaultsValueTable" name="pageTable">
	          <tr>
	            <th><@s.message "template.name"/></th>
	            <th><@s.message "is.defaults"/></th>
	            <th><@s.message "brand.name"/></th>
	            <th><@s.message "quantity"/></th>
	            <th>VAT</th>
	            <th>DEA</th>
	            <th>Eco Part</th>
	            <th><@s.message "stocking.time"/></th>
	            <th><@s.message "freight.template"/></th>
	            <th><@s.message "operating"/></th>
	          </tr>
	          <tr class="conditionTr">
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[templateName]" value="${page.params.templateName!''}" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="<@s.message 'check.to.enable.blur.search'/>" name="params[templateNameLike]" <#if page.params.templateNameLike??> checked </#if>></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
	          				<#if page.params.isDefaults??> 
	          					<@select id="isDefaults" name="params[isDefaults]" selected="${page.params.isDefaults}" optionClass="YesNo"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="isDefaults" name="params[isDefaults]"  optionClass="YesNo"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
	          			<li></li>
					</ul>	
	          	</td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          </tr>
	          </form>
	          <#if (collection?size > 0)>
		          <#list collection as obj>
			          <tr>
				            <td>${obj.templateName}</td>
				            <td><@matchValue key="${obj.isDefaults}" optionClass="YesNo"/></td>
				            <td>${obj.brandName!''}</td>
				            <td>${obj.quantity!''}</td>
				            <td>${obj.vat!''}</td>
				            <td>${obj.dea!''}</td>
				            <td>${obj.ecoPart!}</td>
				            <td>${obj.stockingTime!""}</td>
				            <td>
				            	<table class="width_100">
				            		<tr class="normal">
				            			<td class="shippingChargesTitle" style="width:33%"><@s.message "shipping.methods"/></td>
	            						<td class="shippingChargesTitle"><@s.message "freight.eur"/></td>
	            						<td class="shippingChargesTitle"><@s.message "additional.freight"/></td>
				            		</tr>
			            			<#if (obj.defaultsDeliveryModeList?size > 0)>
			            				<#list obj.defaultsDeliveryModeList as item>
			            					<tr class="normal">
			            						<td>${item.deliveryMode}</td>
				            					<td>${item.shippingCharges}</td>
				            					<td>${item.addShippingCharges}</td>
				            				</tr>
			            				</#list>
			            			</#if>
				            	</table>
				            </td>
				            <td style="width:60px; text-align:center;" >
							 <div class="menu">
							  <ul>
							    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)"><@s.message "operating"/></a>
							      <ul class="menu_ul">
									<li><a href="javascript:void(0)" onclick="editCdiscountDefaultsValue(${obj.id});" ><@s.message "edit"/></a></li>
							      	<li><a href="javascript:void(0)" onclick="setAsDefaultsTemplate(${obj.id})"><@s.message "set.as.defaults.template"/></a></li>
							        <li><a href="javascript:void(0)" onclick="deleteCdiscountDefaultsValue(${obj.id})" ><@s.message "delete"/></a></li>
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
	 			<td class="title width_100px"><@s.message "template.name"/><i class="star">*</i></td>
	 			<td><input type="text" class="txt width_50" name="templateName" id="templateName" required/></td>
	 		</tr>
	 		<tr>
	 			<td class="title"><@s.message "brand.name"/></td>
	 			<td><input type="text" class="txt width_50" name="brandName" id="brandName"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title"><@s.message "quantity"/></td>
	 			<td>
	 				<input type="text" class="txt width_100px" name="quantity" id="quantity" onkeyup="inputNumOnly(this)"/>
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
	 			<td class="title"><@s.message "stocking.time"/></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="stockingTime"  name="stockingTime" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title"><@s.message "freight.message"/></td>
	 			<td>
	 				<table class="width_100 devaultsShippingCharges">
	            		<tr>
	            			<td class="title" style="width:33%"><@s.message "shipping.methods"/></td>
	            			<td class="title"><@s.message "freight.eur"/></td>
	            			<td class="title"><@s.message "additional.freight"/></td>
	            		</tr>
	            		<tr class="deliveryModeTr">
	            			<td>Standard<input type="hidden" name="deliveryMode" value="Standard"/></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr class="deliveryModeTr">
	            			<td>Tracked<input type="hidden" name="deliveryMode" value="Tracked"/></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr class="deliveryModeTr">
	            			<td>Registered<input type="hidden" name="deliveryMode" value="Registered"/></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr class="deliveryModeTr">
	            			<td>BigParcelEco<input type="hidden" name="deliveryMode" value="BigParcelEco"/></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		
	            		<tr class="deliveryModeTr">
	            			<td>BigParcelStandard<input type="hidden" name="deliveryMode" value="BigParcelStandard"/></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="shippingCharges" class="txt width_50px"></td>
	            			<td><input type="text" onkeyup="inputNumOnly(this)" name="addShippingCharges" class="txt width_50px"></td>
	            		</tr>
	            		<tr class="deliveryModeTr">
	            			<td>BigParcelComfort<input type="hidden" name="deliveryMode" value="BigParcelComfort"/></td>
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
