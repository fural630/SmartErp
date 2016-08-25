<!-- header start-->
<script type="text/javascript">
	$(function() {
		$(".first_level_li").hover(function() {
			$(this).find(".nav_content").show();
			$(this).find(".first_level_span").find("a").addClass("first_level_hover");
		}, function() {
			$(this).find(".nav_content").hide();
			$(this).find(".first_level_span").find("a").removeClass("first_level_hover");
		});
	});
</script>
<div class="header">
	<ul class="header_nav"> 
	 	<li class="logo_li">
	    	<a href="/" class="logo">Smart Erp</a>
	    </li> 
	    <li class="first_level_li">
	    	<span class="first_level_span"><a href="javaScript:void(0);">系统</a></span>
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
	    <li class="first_level_li">
	    	<span class="first_level_span"><a href="javaScript:void(0);">销售</a></span>
	    	<div class="nav_content">
	    		<ul>
	    			<li class="second_li">
	    				<span class="second_level_span">Cdiscount 专区</span>
	    				<ul class="second_ul">
	    					<li><a href="/cdiscount/cdiscountApiConfigManage">Cdiscount API配置</a></li>
	    					<li><a href="/cdiscount/cdiscountPublishManage">Cdiscount 商品刊登</a></li>
	    				</ul>
	    			</li>
	    			<li class="second_li">
	    				<span class="second_level_span">Amazon 专区</span>
	    				<ul class="second_ul">
	    					<li><a href="javaScript:void(0);">Cdiscount API配置</a></li>
	    					<li><a href="javaScript:void(0);">Cdiscount 商品刊登</a></li>
	    				</ul>
	    			</li>
	    		</ul>
	    	</div>
	    </li> 
	    <li class="nav_logout">
	    	<a href="#"><img src="/design/frame/style/img/exit.png">&nbsp;退出 </a>
	    </li>
	</ul>
</div>