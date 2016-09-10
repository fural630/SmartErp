jQuery.message = {
	errorImg : "<img src='/design/static/images/common/error_72px.png'/>",
	okImg : "<img src='/design/static/images/common/ok_tick_valider_72px.png'/>",
	warringImg : "<img src='/design/static/images/common/warning_72px.png'/>",
	
	error : function (param) {
		var message = "";
		if (param.message == '' || param.message == null || param.message == undefined){
			message = "未知错误！";
		} else {
			message = param.message;
		}
		$.growlUI('', this.errorImg + message);
		
	},
	success : function (param) {
		var message = "";
		if (param.message == '' || param.message == null || param.message == undefined) {
			message = "操作成功!";
		} else {
			message = param.message  + "!";
		}
		$.growlUI("", this.okImg + message);
	},
	
	warring : function (param) {
		var message = "";
		if (param.message == '' || param.message == null || param.message == undefined) {
			message = "未知警告";
		} else {
			message = param.message  + "!";
		}
		$.growlUI("", this.warringImg + message);
	},
	showMessage : function (param) {
		$.unblockUI();
		if (param.status == -1) {
			this.error(param);
		} else if (param.status == 1) {
			this.success(param);
		} else if (param.status == 0) {
			this.warring(param);
		}
		return param.status;
	}
};