<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/static/js/app/system/userManagerList.js"></script>
  </head>
  <body>
  	<#include "../frame/header.ftl"/>
	<div class="current_nav_name clearfix">${title}
		<div class="fr small_size"> <a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
		</div>
	</div> 
	
	<#include "../frame/page.ftl"/>
	<table id="userManagerTable">
		<tr><td>test</td></tr>
	</table>
	
  </body>
</html>
