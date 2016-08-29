<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!""}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/frame/jquery/layout/jquery.layout.js"></script>
	<script src="/design/static/js/app/system/dictionary.js"></script>
	<link rel="stylesheet" type="text/css" href="/design/frame/jquery/zTree_v3-master/css/zTreeStyle/zTreeStyle.css"/>
	<script src="/design/frame/jquery/zTree_v3-master/js/jquery.ztree.core.js"></script>
	
  </head>
  <body>
		<div class="ui-layout-west">
			<div class="width_100 text_align_right">
				<a class="btn" onclick="addRootDictionaryType()"><img src="/design/frame/style/img/add.png"/>新增根节点</a>
				<a class="btn" onclick="addDictionaryType()"><img src="/design/frame/style/img/add.png"/>新增字典模块</a>
				<a class="btn" onclick="editDictionaryType()"><img src="/design/frame/style/img/edite.png"/>编辑</a>
				<a class="btn" onclick="deleteDictionaryType()"><img src="/design/frame/style/img/del.png"/>删除</a>
			</div>
			<hr/>
			<div id="dictionaryTree" class="ztree"></div>
		</div>
		<div class="ui-layout-center">
		</div>
		
		<div style="display:none" id="dictionaryTypeDialog">
			<input type="text" value="0" name="parentId">
			<table class="popup_tb">
				<tr>
	 				<td class="title width_100px">模块名称<i class="star">*</i></td>
	 				<td><input type="text" class="txt width_50" name="moduleName"/></td>
	 			</tr>
			</table>
		</div>
  </body>
</html>
  	