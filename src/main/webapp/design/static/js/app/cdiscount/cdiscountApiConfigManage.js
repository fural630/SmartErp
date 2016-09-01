$(function () {
	init();
	initDialog();
});

function init() {
	$("#test").comboSelect();
	$.blockUI.defaults.overlayCSS.opacity=0.2;
	$.ajaxSetup({
		error: function (xhr, status, e) {
			var param = {
				status : 0,
				message : e
			};
			$.message.showMessage(param);
		}
	});
}

function initDialog () {
	$("#cdiscountApiConfigDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		height: 400,
		resizable: false,
		buttons : [ {
				text : "连接测试",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					testConnectApi();
				}
			}, {
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					saveCdiscountApiConfig();
					$.myformPlugins.cleanForm("#cdiscountApiConfigDialog");
					$(this).dialog("close");
				}
			} 
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#cdiscountApiConfigDialog");
		}
	});
}


function previousPage () {
	var pageNo = parseInt($("#pageNo").val());
	var previousPageNo = pageNo - 1;
	if (previousPageNo <= 0) {
		return;
	}
	$("#pageNo").val(previousPageNo);
	$("#mainPageForm").submit();
}

function nextPage () {
	var pageNo = parseInt($("#pageNo").val());
	var nextPageNo = pageNo + 1;
	$("#pageNo").val(nextPageNo);
	$("#mainPageForm").submit();
}

function changePageSize() {
	$("#mainPageForm").submit();
}


function showCreateApiConfigDialog () {
	$("#cdiscountApiConfigDialog").dialog("option", "title", "添加测试API");
	$("#cdiscountApiConfigDialog").dialog("open");
}

function testConnectApi () {
	
	var param = getParams();
	
	$.ajax({
		url : "/cdiscount/testConnectApi",
		type: 'POST',
		dataType : "json",
		data : {
			apiAccount : param.apiAccount,
			apiPassword : param.apiPassword
		},
		success : function (data) {
			$.message.showMessage(data);
		}
	});
}

function getParams() {
	
	var param = new Object();
	var dialog = $("#cdiscountApiConfigDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var shopName = $.trim(dialog.find("input[name='shopName']").val());
	var email = $.trim(dialog.find("input[name='email']").val());
	var apiAccount = $.trim(dialog.find("input[name='apiAccount']").val());
	var apiPassword = $.trim(dialog.find("input[name='apiPassword']").val());
	var receivablesEmail = $.trim(dialog.find("input[name='receivablesEmail']").val());
	
	param.id = id;
	param.shopName = shopName;
	param.email = email;
	param.apiAccount = apiAccount;
	param.apiPassword = apiPassword;
	param.receivablesEmail = receivablesEmail;
	return param;
}

function resetAll() {
	$.blockUI({
		message: '<img src="/design/static/images/common/progressbar10.gif">',
		timeout:5000,
		css:{
			backgroundColor:"",
			border:"0"
		}
	});
}


function saveCdiscountApiConfig () {
	
	var dialog = $("#cdiscountApiConfigDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var shopName = $.trim(dialog.find("input[name='shopName']").val());
	var email = $.trim(dialog.find("input[name='email']").val());
	var apiAccount = $.trim(dialog.find("input[name='apiAccount']").val());
	var apiPassword = $.trim(dialog.find("input[name='apiPassword']").val());
	var receivablesEmail = $.trim(dialog.find("input[name='receivablesEmail']").val());
	
	var url = "/cdiscount/saveCdiscountApiConfig";
	
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "text",
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
			$.growlUI('Growl Notification', 'Have a nice day!'); 
		}
	});
}