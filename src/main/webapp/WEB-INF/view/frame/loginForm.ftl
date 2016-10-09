<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Smart Erp</title>
	<link rel="stylesheet" type="text/css" href="/design/frame/style/css/login.css"/>
	<script type="text/javascript" src="/design/frame/style/js/jquery-1.7.1.min.js"></script>
	<script src="/design/static/js/app/system/login.js"></script>
</head>
<body>

<div class="loginbg">
	<div class="bg_image">
		<div class="loginFormbg">
			<div class="form_area">
				<div class="submit_massage">
					您输入的账号或密码有误
				</div>
				<form action="/SmartErp/login" method="post" id="loginForm">
				<div class="form_username">
					<input type="text" name="username" class="form_input" placeholder="账号"/>
				</div>
				<div class="form_password">
					<input type="password" name="password" class="form_input" placeholder="密码" />
				</div>
				</form>
				<div class="submit_btn_area">
					<a href="javascript:void(0)" onclick="sbumitForm();" class="submit_btn"></a>
				</div>
			</div>
		</div>
	</div>
</div>

<!--
	<div style="float:left;">
		<div style="width:200px;height:200px;background-color:#ccc;padding:10px;">
		<form action="/SmartErp/login" method="post">
			username : <input type="text" name="username" ><br/>
			password : <input type="password" name="password" ><br/>
			<br/>
			<input type="submit" value="登录"/>
		</form>
		</div>
	</div>
	
	-->

</body>
</html>