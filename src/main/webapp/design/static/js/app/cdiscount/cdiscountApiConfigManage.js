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
					var isMastRead = $("#cdiscountApiConfigDialog input[name=mastRead]").is(':checked');
					if (!isMastRead) {
						openMastReadDialog();
					} else {
						if (validate()) {
							saveCdiscountApiConfig();
							$.myformPlugins.cleanForm("#cdiscountApiConfigDialog");
							$(this).dialog("close");
						}
					}
				}
			} , {
				text : "验证",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					validate();
				}
			} 
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#cdiscountApiConfigDialog");
		}
	});
	
	$("#mastReadDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 650,
		height: 450,
		resizable: false,
		buttons : [ {
			text : "同意",
			icons : {
				primary : "ui-icon-heart"
			},
			click : function() {
				$("#cdiscountApiConfigDialog input[name=mastRead]").attr("checked", true);
				$(this).dialog("close");
			}
		}, {
			text : "不同意",
			icons : {
				primary : "ui-icon-heart"
			},
			click : function() {
				$(this).dialog("close");
			}
		}
	]
		
	});
}

function validate () {
	return $("#cdiscountApiConfigDialogForm").valid();
}

function openMastReadDialog() {
	$("#mastReadDialog").dialog("open");
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


function showCreateApiConfigDialog (title) {
	$("#cdiscountApiConfigDialog").dialog("option", "title", title);
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
		beforeSend : function (xhr) {
			$.blockUI({
				message: '<img src="/design/static/images/common/progressbar10.gif">',
				timeout: 10000,
				css:{
					backgroundColor: "",
					border:"0"
				}
			});
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
			$.message.showMessage(data);
		}
	});
}

function editCdiscountApiConfig(id) {
	
	var url = "/cdiscount/getCdiscountApiConfigById";
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		async: false,
		data : {
			id : id
		},
		success : function (data) {
			if (null != data) {
				fillingData(data, "#cdiscountApiConfigDialog");
			} else {
				var param = {
					status : 0,
					message : e
				};
				$.message.showMessage(param);
			}
			
		}
	});
}

function fillingData(obj, selector) {
	showCreateApiConfigDialog("修改店铺信息");
	for ( var name in obj ){ 
		$(selector).find("input[name=" + name +"]").val(obj[name]);
	}
	$("#cdiscountApiConfigDialog input[name=mastRead]").attr("checked", true);
}