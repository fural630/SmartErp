$(function () {
	showCreateApiConfigDialog();
	
});

function showCreateApiConfigDialog () {
	var title = "添加";
	$("#cdiscountApiConfigDialog").dialog({
		title : title,
		autoOpen: true,
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
				saveCdiscountApiConfig();
				$(this).dialog("close");
			}
		} ],
		close: function( event, ui ) {
		}
	});
}

function saveCdiscountApiConfig () {
	
	var dialog = $("#cdiscountApiConfigDialog");
	var id = $.trim(dialog.find("input[name='apiId']").val());
	var shopName = $.trim(dialog.find("input[name='shopName']").val());
	var email = $.trim(dialog.find("input[name='email']").val());
	var apiAccount = $.trim(dialog.find("input[name='apiAccount']").val());
	var apiPassword = $.trim(dialog.find("input[name='apiPassword']").val());
	var receivablesEmail = $.trim(dialog.find("input[name='receivablesEmail']").val());
	
	var url = "/cdiscount/saveCdiscountApiConfig";
	
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		async: false,
		data : {
			id : id,
			shopName : shopName,
			email : email,
			apiAccount : apiAccount,
			apiPassword : apiPassword,
			receivablesEmail : receivablesEmail
		},
		success : function (data) {
			alert(data);
		}
	});
}