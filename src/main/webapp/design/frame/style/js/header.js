$(function () {
	initPersonDialog();
});

function initPersonDialog () {
	$("#personDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		height: 400,
		resizable: false,
		buttons : [ {
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					if (validatePersonForm()) {
						savePersonInfo();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#personDialog");
			var validateFrom = $("#personDialogFrom").validate();
			validateFrom.resetForm();
		}
	});
}

function savePersonInfo () {
	var dialog = $("#personDialog");
	var username = $.trim(dialog.find("input[name='username']").val());
	var password = $.trim(dialog.find("input[name='password']").val());
	var name = $.trim(dialog.find("input[name='name']").val());
	var email = $.trim(dialog.find("input[name='email']").val());
	var phone = $.trim(dialog.find("input[name='phone']").val());
	
	var url = "/system/savePersonInfo";
	
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		data : {
			username : username,
			password : password,
			name : name,
			email : email,
			phone : phone
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == 1) {
				setTimeout('loginOut()',1000);
//				$("#personDialog").dialog("close");
//				$.myformPlugins.cleanForm("#personDialog");
//				refresh(1000);
			}
		}
	});
}

function validatePersonForm () {
	return $("#personDialogFrom").valid();
}

function loginOut () {
	$("#loginOutForm").submit();
}

function modifyPersonInfo () {
	var url = "/system/getPersonInfo";
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		success : function (data) {
			$.unblockUI();
			if (null != data) {
				fillingPersonData(data, "#personDialog");
			} else {
				var param = {
					status : -1
				};
				$.message.showMessage(param);
			}
		}
	});
}

function showPersionDialog(title) {
	$("#personDialog").dialog("open");
	$("#personDialog").dialog("option", "title", title);
}

function fillingPersonData (obj, selector) {
	showPersionDialog("修改个人信息");
	for ( var name in obj ){ 
		var input = $(selector).find("input[name=" + name +"]");
		if (input.length > 0) {
			input.val(obj[name]);
		}
		var select = $(selector).find("select[name=" + name +"]");
		if (select.length > 0) {
			select.val(obj[name]);
		}
	}
}