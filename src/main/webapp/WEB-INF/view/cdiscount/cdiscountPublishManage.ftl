<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/cdiscount/cdiscountPublishManage.js"></script>
	<script src="/design/frame/ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/static/css/cdiscount/cdiscountPublishManage.css"/>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
  	<input type="hidden" id="moduleAjaxTableUrl" value="/cdiscount/getCdiscountPublishCollection"/>
  	<input type="hidden" id="moduleUrl" value="${requestUrl}"/>
	<div class="current_nav_name clearfix">${title}
		<div class="fr small_size"> <a class="btn" onclick="createCdiscountPublish()"><img src="/design/frame/style/img/add.png"/>新增</a>
		</div>
	</div>  
	<#include "../frame/page.ftl"/>
	<div class="mainbody clearfix"> 
	  <div class="tableview clearfix">
	    <div class="content">
	    
	      <table class="tb_border tb_full stripe" id="cdiscountPublishManageTable">
	          <tr>
	          	<th width="50px;"></th>
	          	<th width="80px;">ID</th>
	            <th>店铺名称</th>
	            <th>SKU</th>
	            <th>类别名称</th>
	            <th>刊登状态</th>
	            <th>创建人</th>
	            <th>创建时间</th>
	            <th>日志</th>
	            <th>操作</th>
	          </tr>
	          <tr class="conditionTr">
	          	<td></td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="txt width_40px" name="id" /></li>
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
	          			<li><input type="text" class="txt width_100px" name="sku" /></li>
	          			<li>*&nbsp;<input type="checkbox" title="勾选启用模糊查找" name="skuLike"></li>
	          		</ul>
	          	</td>
	          	<td>
	          		<ul>
	          			<li>
		          			<select class="sel width_100px" >
								<option>请选择</option>
								<option>男</option>
								<option>女</option>
							</select>
						</li>
						<li></li>
					</ul>	
	          	</td>
	          	<td>
	          		<ul>
	          			<li><input type="text" class="width_100px main_input_search select_filter" name="" value="输入过滤" /></li>
	          			<li>
		          			<select class="sel width_100px">
								<option>请选择</option>
								<option>男</option>
								<option>女</option>
							</select>
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
	      		 <tr>
		            <td style="text-align:center"><input name="main_page_checkbox" type="checkbox" value="" onclick="countCheckbox()" /></td>
		            <td></td>
		            <td></td>
		            <td></td>
		            <td></td>
		            <td></td>
		            <td></td>
		            <td></td>
		            <td>
		            	<a href="javascript:void(0)" onclick="showLog(this)"><img src="/design/static/images/common/system-log.png"/></a>
		            	<div class="log_content">
		            		【1、于2016-08-20 00:24 由超级管理员创建信息】<br/>
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
	
	
	<div id="cdiscountPublishDialog" style="display:none;">
	 	<table class="popup_tb">
	 		<tr>
	 			<td class="title width_100px">店铺名<i class="star">*</i></td>
	 			<td>
	 				<select class="sel width_100px" id="shopName" name="shopName">
					</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">站点<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">SKU<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">品牌名<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">Ean<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">Model<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">多属性<i class="star">*</i></td>
	 			<td>
	 				<select class="sel">
						<option id="batchDelete">是</option>
						<option id="batchUpdateStatus">否</option>
					</select>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">短标题<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">长标题<i class="star">*</i></td>
	 			<td><input type="text" class="txt width_100px"/></td>
	 		</tr>
	 		<tr>
	 			<td class="title">商品标题<i class="star">*</i></td>
	 			<td><textarea class="txt width_96 remark" ></textarea></td>
	 		</tr>
	 		<tr>
	 			<td class="title">市场描述<i class="star">*</i></td>
	 			<td><textarea id="description"></textarea></td>
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
	 								<input type="text" class="txt" style="width:400px;" id="imageUrlAddress"/>
	 								<a class="btn" onclick="addImageUrlAddress();">添加图片地址</a>
	 								<!--<a class="btn" onclick="clearImageUrlAddress();">清空</a>-->
	 							</td>
	 							<td style="text-align:right;">已选择图片：<span id="selectImageCount">0</span> 张 | 最多 4 张图片</div></td>
	 						</tr>
	 					</table>
	 					<div class="image_area">
	 					</div>
	 				</div>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">商品类别<i class="star">*</i></td>
	 			<td>
	 				<div>
	 					<a class="btn" onclick="getFirstCdiscountCategory()">类别选择</a>&nbsp;&nbsp;已选类别 &nbsp;&nbsp;:&nbsp;&nbsp;<span id="selectCategoryPath"></span>
	 					<input type="text" name="categoryId" class="txt width_100px"/><input type="text" name="categoryName" class="txt width_100px"/>
	 				<div class="category_area" id="categoryArea"></div>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">数量<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px">
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">价格<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px">
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">VAT<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px">
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">DEA<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px">
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">Eco part<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px">
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">产品新旧<i class="star">*</i></td>
	 			<td>
	 				<input type="text" class="txt width_100px">
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">运费信息<i class="star">*</i></td>
	 			<td>
	 				<table class="shippingChargesInfoTable">
	 					<tr>
							<td class="title">物流方式</td>
							<td class="title">最短送达时间</td>
							<td class="title">最长送达时间</td>
							<td class="title">运费</td>
							<td class="title">额外运费</td>
						</tr>
						<tr>
							<td>At home : Standard</td>
							<td rowspan="3"><input onkeyup="inputNumOnly(this)" type="text" id="minDeliveryTime" class="txt width_50px" /><i class="star">*</i></td>
							<td rowspan="3"><input onkeyup="inputNumOnly(this)" type="text" id="maxDeliveryTime" class="txt width_50px" /><i class="star">*</i></td>
							<td><input type="text" onkeyup="inputNumOnly(this)" id="standardShippingCharges" class="txt width_50px" /><i class="star">*</i></td>
							<td><input type="text" onkeyup="inputNumOnly(this)" id="standardAdditionalShippingCharges" class="txt width_50px" /></td>
						</tr>
						<tr>
							<td>At home Tracked</td>
							<td><input type="text" onkeyup="inputNumOnly(this)" id="trackedShippingCharges" class="txt width_50px" /><i class="star">*</i></td>
							<td><input type="text" onkeyup="inputNumOnly(this)" id="trackedAdditionalShippingCharges" class="txt width_50px" /></td>
						</tr>
						<tr>
							<td>At home Registered</td>
							<td><input type="text" onkeyup="inputNumOnly(this)" id="registeredShippingCharges" class="txt width_50px" /><i class="star">*</i></td>
							<td><input type="text" onkeyup="inputNumOnly(this)" id="registeredAdditionalShippingCharges" class="txt width_50px" /></td>
						</tr>
	 				</table>
	 			</td>
	 		</tr>
	 	</table>
	</div>
  </body>
</html>
