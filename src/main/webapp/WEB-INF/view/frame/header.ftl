<!-- header start-->
<script type="text/javascript">
	$(function() {
		$(".first_level_li").hover(function() {
			$(this).find(".nav_content").show();
		}, function() {
			$(this).find(".nav_content").hide();
		});
	});
</script>
<div class="header">
	<ul class="header_nav"> 
	 	<li class="logo_li">
	    	<a href="/" class="logo">Smart Erp</a>
	    </li> 
	    <li class="first_level_li">
	    	<span class="first_level_span"><a href="javaScript:void(0);">系统管理</a></span>
	    	<div class="nav_content">
	    		<ul>
	    			<li class="nav_li"><a href="/system/getUserManagerList">用户管理</a></li>
	    			<li class="nav_li"><a href="/system/getDemoList">Demo</a></li>
	    			<li class="nav_li"><a href="#">权限管理</a></li>
	    			<li class="nav_li"><a href="#">权限管理</a></li>
	    			<li class="nav_li"><a href="#">权限管理</a></li>
	    			<li class="nav_li"><a href="#">权限管理</a></li>
	    		</ul>
	    	</div>
	    </li> 
	    <li class="nav_logout">
	    	<a href="#"><img src="/design/frame/style/img/exit.png">&nbsp;退出 </a>
	    </li>
	</ul>
</div>