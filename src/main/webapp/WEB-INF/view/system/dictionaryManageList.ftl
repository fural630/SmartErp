<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${title!""}</title>
	<#include "../frame/common.ftl"/>
	<script src="/design/frame/jquery/layout/jquery.layout.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('body').layout({ 
				applyDefaultStyles: true,
				west__size:400 
			 });
		});
	</script>
	
  </head>
  <body>
		<div class="ui-layout-west">
			<div class="width_100 text_align_right">
				<a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
				<a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
				<a class="btn"><img src="/design/frame/style/img/add.png"/>新增</a>
			</div>
			<hr/>
		</div>
		<div class="ui-layout-center">
		</div>
  </body>
</html>
  	