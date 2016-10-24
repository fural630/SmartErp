function sbumitForm() {
	var username = $("input[name='username']").val().trim();
	var password = $("input[name='password']").val().trim();
	if (username == "" || password == "") {
		$("#submitPrompt").text("请输入账号和密码");
		return;
	}
	$("#loginForm").submit();
}

document.onkeydown = keyDownEnter;  

function keyDownEnter(e) {    
    // 兼容FF和IE和Opera    
    var theEvent = e || window.event;    
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
    if (code == 13) {    
    	sbumitForm();//具体处理函数    
        return false;    
    }    
    return true;    
}  