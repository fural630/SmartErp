$(function () {
	initDialog();
});
function initDialog () {
	$("#userDialog").dialog({
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
					if (validate()) {
						saveUser();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#userDialog");
			var validateFrom = $("#userDialogFrom").validate();
			validateFrom.resetForm();
		}
	});
}

function showUserDialog (title) {
	$("#userDialog").dialog("option", "title", title);
	$("#userDialog").dialog("open");
}

function validate() {
	return $("#userDialogFrom").valid();
}

function saveUser () {
	var dialog = $("#userDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var username = $.trim(dialog.find("input[name='username']").val());
	var password = $.trim(dialog.find("input[name='password']").val());
	var name = $.trim(dialog.find("input[name='name']").val());
	var email = $.trim(dialog.find("input[name='email']").val());
	var phone = $.trim(dialog.find("input[name='phone']").val());
	var status = $.trim(dialog.find("select[name='status']").val());
	
	var url = "/system/saveUser";
	
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
			username : username,
			password : password,
			name : name,
			email : email,
			phone : phone,
			status : status
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == 1) {
				$("#userDialog").dialog("close");
				$.myformPlugins.cleanForm("#userDialog");
				refresh(1000);
			}
		}
	});
}

function editUserInfo (id) {
	var url = "/system/getUserInfo";
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		data : {
			id : id
		},
		success : function (data) {
			$.unblockUI();
			if (null != data) {
				fillingData(data, "#userDialog");
			} else {
				var param = {
					status : -1
				};
				$.message.showMessage(param);
			}
		}
	});
}

function fillingData (obj, selector) {
	showUserDialog("修改用户信息");
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

function deleteUser (id) {
	if(confirm("确定删除？")){
		var url = "/system/deleteUser";
		$.ajax({
			url : url,
			type: 'POST',
			dataType : "json",
			data : {
				id : id
			},
			success : function (data) {
				$.message.showMessage(data);
				refresh(1000);
			}
		});
	}
} 