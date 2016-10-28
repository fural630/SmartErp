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
	    	<a href="/SmartErp/home" class="logo">Smart Erp</a>
	    </li> 
	    <li class="first_level_li">
	    	<span class="first_level_span"><a href="javaScript:void(0);">系统</a></span>
	    	<div class="nav_content">
	    		<ul>
	    			<li class="second_li">
	    				<span class="second_level_span">系统管理</span>
	    				<ul class="second_ul">
	    					<li><a href="/system/userManage">用户管理</a></li>
	    					<li><a href="/system/scriptManage">脚本管理</a></li>
	    					<!--
	    					<li><a href="/system/getDemoList">Demo</a></li>
	    					<li><a href="/system/dictionaryManageList" target="_blank">字典配置</a></li>
	    					-->
	    				</ul>
	    			</li>
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
	    					<li><a href="/cdiscount/cdiscountPublishManage">Cdiscount 商品刊登管理</a></li>
	    					<li><a href="/cdiscount/cdiscountOnlineProductManage">Cdiscount 已上架商品管理</a></li>
	    					<li><a href="/cdiscount/cdiscountEanManage">Cdiscount EAN管理</a></li>
	    					<li><a href="/cdiscount/cdiscountApiConfigManage">Cdiscount API配置</a></li>
	    				</ul>
	    			</li>
	    			<!--
	    			<li class="second_li">
	    				<span class="second_level_span">Amazon 专区</span>
	    				<ul class="second_ul">
	    					<li><a href="javaScript:void(0);">Cdiscount API配置</a></li>
	    					<li><a href="javaScript:void(0);">Cdiscount 商品刊登</a></li>
	    				</ul>
	    			</li>
	    			-->
	    		</ul>
	    	</div>
	    </li> 
	    <li class="nav_logout">
	    	<form id="loginOutForm" action="/SmartErp/loginOut" method="post"></form>
	    	<a onclick="modifyPersonInfo();" title="点击修改个人信息"><img src="/design/frame/style/img/default.gif">&nbsp;${loginUserName!""} </a>&nbsp;&nbsp;
	    	<a onclick="loginOut();"><img src="/design/frame/style/img/exit.png">&nbsp;退出 </a>
	    </li>
	</ul>
</div>

<div id="personDialog" style="display:none;">
	<form id="personDialogFrom">
	<table class="popup_tb">
		<tr>
			<td class="title width_100px">账号<i class="star">*</i></td>
			<td><input type="text" class="txt width_50" name="username" required/></td>
		</tr>
		<tr>
			<td class="title width_100px">密码<i class="star">*</i></td>
			<td><input type="password" class="txt width_50" name="password" required/></td>
		</tr>
		<tr>
			<td class="title width_100px">昵称<i class="star">*</i></td>
			<td><input type="text" class="txt width_50" name="name" required/></td>
		</tr>
		<tr>
			<td class="title width_100px">邮箱<i class="star">*</i></td>
			<td><input type="email" class="txt width_50" name="email" required email/></td>
		</tr>
		<tr>
			<td class="title width_100px">电话<i class="star">*</i></td>
			<td><input type="text" class="txt width_50" name="phone" required/></td>
		</tr>
	</table>
	</form>
	<div class="modifyNoted">
		注意：修改个人信息成功后系统将自动退出登录！
	</div>
	
</div>