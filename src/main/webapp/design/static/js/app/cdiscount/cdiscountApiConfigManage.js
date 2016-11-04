$(function () {
	initDialog();
});

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
					if (validate()) {
						testConnectApi();
					}
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
						}
					}
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
	var closeStatus = dialog.find("select[name='closeStatus']").val();
	var receivablesEmail = $.trim(dialog.find("input[name='receivablesEmail']").val());
	
	param.id = id;
	param.shopName = shopName;
	param.email = email;
	param.apiAccount = apiAccount;
	param.apiPassword = apiPassword;
	param.receivablesEmail = receivablesEmail;
	param.closeStatus = closeStatus;
	return param;
}

function saveCdiscountApiConfig () {
	
	var dialog = $("#cdiscountApiConfigDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var shopName = $.trim(dialog.find("input[name='shopName']").val());
	var email = $.trim(dialog.find("input[name='email']").val());
	var apiAccount = $.trim(dialog.find("input[name='apiAccount']").val());
	var apiPassword = $.trim(dialog.find("input[name='apiPassword']").val());
//	var receivablesEmail = $.trim(dialog.find("input[name='receivablesEmail']").val());
	var receivablesEmail = "";
	var closeStatus = dialog.find("select[name='closeStatus']").val();
	
	var url = "/cdiscount/saveCdiscountApiConfig";
	
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
			shopName : shopName,
			email : email,
			apiAccount : apiAccount,
			apiPassword : apiPassword,
			receivablesEmail : receivablesEmail,
			closeStatus : closeStatus
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == "1") {
				$("#cdiscountApiConfigDialog").dialog("close");
				$.myformPlugins.cleanForm("#cdiscountApiConfigDialog");
				refresh(1000);
			}
		},
		beforeSend : function (xhr) {
			if (id == "") {
				$.blockUI({
					message: "<div style='height:30px;line-height:30px;'>初次授权店铺需要初始化相关配置，请耐心等待...</div>",
					timeout: 30000
				});
			} else {
				$.blockUI({
					message: '<img src="/design/static/images/common/progressbar10.gif">',
					timeout: 10000,
					css:{
						backgroundColor: "",
						border:"0"
					}
				});
			}
		},
		error: function (xhr, status, e) {
			var param = {
				status : 0,
				message : e
			};
			$.message.showMessage(param);
		}
	});
}

function editCdiscountApiConfig(id) {
	var url = "/cdiscount/getCdiscountApiConfigById";
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
				fillingData(data, "#cdiscountApiConfigDialog");
			} else {
				var param = {
					status : -1
				};
				$.message.showMessage(param);
			}
			
		}
	});
	
}

function fillingData(obj, selector) {
	showCreateApiConfigDialog("修改店铺信息");
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
	$("#cdiscountApiConfigDialog input[name=mastRead]").attr("checked", true);
}

function deleteCdiscountApiConfig (id) {
	if(confirm("确定删除？")){
		var url = "/cdiscount/deleteCdiscountApiConfigById";
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

function updateShopConfig (id) {
	if(confirm("确定重新获取平台配置的店铺信息？")){
		$.ajax({
			url : "/cdiscount/updateShopConfig",
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