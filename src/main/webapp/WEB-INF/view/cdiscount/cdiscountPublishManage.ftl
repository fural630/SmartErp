<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><@s.message "navigator.cdiscount.publish.manage"/></title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/cdiscount/cdiscountPublishManage.js"></script>
	<script src="/design/frame/ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/static/css/cdiscount/cdiscountPublishManage.css"/>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<form action="/cdiscount/cdiscountPublishManage" id="mainPageForm" method="post">
	<div class="current_nav_name clearfix"><@s.message "navigator.cdiscount.publish.manage"/>
		<div class="fr small_size">
			<a class="btn" onclick="createCdiscountPublish()"><img src="/design/frame/style/img/add.png"/>创建商品刊登</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	      <table class="tb_border tb_full stripe" id="cdiscountPublishManageTable" name="pageTable">
	          <tr>
	          	<th width="40px;"></th>
	          	<th width="60px;">ID</th>
	            <th width="120px;">店铺名称</th>
	            <th width="110px;">主图</th>
	            <th width="120px;">SKU</th>
	            <th width="120px;">EAN</th>
	            <th>价格</th>
	            <th>数量</th>
	            <th>备货时间</th>
	            <th width="120px;">刊登状态</th>
	            <th>上传打包批次号</th>
	            <th width="180px;">创建时间</th>
	            <th>日志</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td></td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_40px" name="params[id]"  value="${page.params.id!''}"/></li>
	          			<li></li>
	          		</ul>
	          	</td>
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
	          	<td></td>
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
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td>
	          		<ul>
	          			<li>
		          			<#if page.params.publishStatus??> 
	          					<@select id="publishStatus" name="params[publishStatus]" selected="${page.params.publishStatus}" optionClass="CdiscountPublishStatus"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				<#else>
	          					<@select id="publishStatus" name="params[publishStatus]"  optionClass="CdiscountPublishStatus"  cssClass="sel width_100px" headerKey="" headerValue=""/>
	          				</#if>
						</li>
						<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_100px" name="params[productPackageId]" value="${page.params.productPackageId!''}" /></li>
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
						<td>${obj.id}</td>
						<td>${obj.shopName!""}</td>
						<td><img width="100" height="100" src="${obj.mainImage!''}"/></td>
						<td>${obj.sku!""}</td>
						<td>${obj.ean}</td>
						<td>${obj.price}</td>
						<td>${obj.stockQty}</td>
						<td>${obj.preparationTime}</td>
						<td><@matchValue key="${obj.publishStatus}" optionClass="CdiscountPublishStatus"/></td>
						<td>${obj.productPackageId!""}</td>
						<td>
							创建时间:<br/>${obj.createTime}<br/>
							修改时间:<br/>${obj.updateTime}
						</td>
						<td>
							<a href="javascript:void(0)" onclick="showLog(this)"><img src="/design/static/images/common/system-log.png"/></a>
							<div class="log_content">
								${obj.log!""}
							</div>
						</td>
						<td style="width:60px; text-align:center;" >
						 <div class="menu">
						  <ul>
						    <li class="option_btn" onmouseover="optionMouserover(this)" onmouseout="optionMouseout(this)"><a class="btn" href="javascript:void(0)">操作</a>
						      <ul class="menu_ul">
								<li><a href="javascript:void(0)" onclick="editCdiscountPublish(${obj.id})" >编辑 </a></li>
						        <li><a href="javascript:void(0)" onclick="deleteCdiscountPublish(${obj.id})" >删除 </a></li>
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
									<option value="batchShelvesProduct">上传商品基本信息</option>
									<option value="batchUploadOffers">上传offers</option>
									<option value="batchUpdateToWaitPendding">修改为待处理</option>
									<!--<option value="batchDelete">批量删除</option>-->
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
	
	
	<div id="cdiscountPublishDialog" style="display:none;">
		<input type="hidden" name="publishId" value="" />
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">店铺名<i class="star">*</i></td>
	 			<td>
	 				<select class="sel width_100px" id="shopName" name="shopName" onchange="onChangeShopName()">
					</select>
	 			</td>
	 		</tr>
	 		<tr id="publish_template_area" style="display:none;">
				<td class="title width_100px">引用刊登模板</td>
	 			<td>
	 				<select class="sel width_100px" id="defaultValuteTemplate" name="defaultValuteTemplate" onchange="onChangeShopName()"></select>
					<a class="btn" onclick="referenceTemplate()">确认引用</a>
					<a class="btn" onclick="viewTemplate()">查看该模板</a>
					<a class="btn" onclick="setAsDefaultsTemplate()">设置该模板为默认模板</a>
					<a onclick="showSystemPromptDialog(1)"><img src="/design/frame/style/img/question_mark.png"/></a>
	 			</td> 		
	 		</tr>
	 		<tr>
	 			<td class="title">SKU<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_200px" id="sku" name="sku" onchange="onChangeSku()"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">品牌名<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_200px" id="brandName" name="brandName"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">Ean<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_200px" id="ean" name="ean"/>
	 				<a class="btn" onclick="randomSelectEan()"><@s.message "random.select.ean"/></a>
	 				<a class="btn" onclick="selfSelectEan()"><@s.message "self.select.ean"/></a>
	 				（可用EAN数据量：<span id="eanStockQty">0</span>）
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">多属性<i class="star">*</i></td>
	 			<td>
	 				<select class="sel width_100px" id="productKind" name="productKind">
						<option value="Standard">否</option>
						<option value="Variant">是</option>
					</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">短描述<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_96" id="shortLabel" name="shortLabel"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">长描述<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_96" id="longLabel" name="longLabel"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">产品简介<i class="star">*</i></td>
	 			<td><textarea class="txt width_96 remark" id="description" name="description"></textarea></td>
	 		</tr>
	 		<tr>
	 			<td class="title">简要描述<i class="star">*</i></td>
	 			<td><textarea id="marketingDescription" name="marketingDescription"></textarea></td>
	 		</tr>
	 		<tr>
	 			<td class="title">图片<i class="star">*</i></td>
	 			<td>
	 				<div class="image_box">
	 					<div class="image_title">Cdiscount选择图片</div>
	 					<div class="image_count_info">
	 					<table class="image_info_table">
	 						<tr>
	 							<td style="text-align:left;width:70%">
	 								<input type="text" class="txt" style="width:400px;" id="imageUrlAddress" placeholder="图片地址/Url"/>
	 								<a class="btn" onclick="addImageUrlAddress();">添加图片地址</a>
	 								<!--<a class="btn" onclick="clearImageUrlAddress();">清空</a>-->
	 							</td>
	 							<td style="text-align:right;">已选择图片：<span id="selectImageCount">0</span> 张 | 最多 4 张图片</div></td>
	 						</tr>
	 					</table>
	 					<div id="image_area">
	 						<ul id="sortable"></ul>
	 					</div>
	 				</div>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">商品类别<i class="star">*</i></td>
	 			<td>
	 				<div>
	 					<a class="btn" onclick="getFirstCdiscountCategory()">展开类别列表</a>
	 					&nbsp;&nbsp;已选类别 &nbsp;&nbsp;:&nbsp;&nbsp;
	 					<span id="navigation"></span>
	 					<input type="hidden" name="categoryName" class="txt width_100px"/>
	 				</div>
	 				<div class="category_area" id="categoryArea"></div>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">平台类别编号<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="categoryCode" name="categoryCode" onchange="changeCategoryCode();"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">数量<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="stockQty" name="stockQty" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">VAT(%)<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="vat"  name="vat" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">DEA(€)<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="dea"  name="dea" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">Eco part(€)<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="ecoPart"  name="ecoPart" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">备货时间（工作日/天为单位）<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="preparationTime"  name="preparationTime" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">产品新旧<i class="star">*</i></td>
	 			<td>
	 				<select class="sel" id="productCondition" name="productCondition">
	 					<option value="6">New</option>
	 					<option value="1">Used - Like new</option>
	 					<option value="2">Used - Very Good</option>
	 					<option value="3">Used - Good</option>
	 					<option value="4">Used - Acceptable</option>
	 				</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">运费信息<i class="star">*</i></td>
	 			<td>
	 				<div id="deliveryModeArea"></div>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">价格(€)<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px" id="price" name="price" onkeyup="inputNumOnly(this)"/>
	 			</td>
	 		</tr>
	 	</table>
	 	<div class="validateTip"></div>
	</div>
	
	<div id="templateDialog" style="display:none;">
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
	</div>
	
  </body>
</html>
