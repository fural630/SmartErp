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
						<option value="">-- 请选择 --</option>
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
	 					<div class="image_count_info">已选择图片： 0 张 | 最多 4 张图片</div>
	 				</div>
	 			</td>
	 		</tr>
	 		<tr>
	 			<td class="title">商品类别<i class="star">*</i></td>
	 			<td>
	 				<div>
	 					<a class="btn" onclick="getFirstCdiscountCategory()">类别选择</a>&nbsp;&nbsp;已选类别：<input type="text" name="categoryId" class="txt width_100px"/>
	 				</div>
	 				<div class="category_area">
	 					<table class="category_table" id="cdCategoryTable">
	 						<tr>
	 							<td>
	 								<div class="category_select_box" id="categoryLevel_1">
				 						<ul>
				 							<li><a title="test" href="#"><div>塑料袋减肥了圣诞节福利是可敬的福利是可敬的分离式快递劫匪</div></a></li>
				 							<li><a title="test" href="#"><div>12312312&nbsp;>></div></a></li>
				 						</ul>
				 					</div>
	 							</td>
	 							<td>
	 								<div class="category_select_box">
	 								<ul>
	 									<li>
				 							<a href="#"><span>asdfasdfasdfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfa</span></a>
				 						</li>
				 					</ul>
				 					</div>
	 							</td>
	 							<td>
	 								<div class="category_select_box">
				 						<ul>
				 							<li>
				 								<a href="#">
				 									<span>电子产品》》</span>
				 								</a>
				 							</li>
				 						</ul>
				 					</div>
	 							</td>
	 							<td>
	 								<div class="category_select_box">
				 						<ul>
				 							<li>
				 								<a href="#">
				 									<span>电子产品》》</span>
				 								</a>
				 							</li>
				 						</ul>
				 					</div>
	 							</td>
	 						</tr>
	 					</table>
	 				</div>
	 			</td>
	 		</tr>
	 	</table>
	</div>
  </body>
</html>
