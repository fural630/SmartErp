$(function() {
	init();
	initDialog();
});

function init () {
	CKEDITOR.replace('description');
}

function initDialog() {
	$("#cdiscountPublishDialog").dialog({
		autoOpen: false,
		modal: true,
		width: document.body.scrollWidth * 0.8,
		height: document.body.scrollHeight * 0.9,
		resizable: false,
		buttons : [ {
			text : "保存",
			icons : {
				primary : "ui-icon-heart"
			},
			click : function() {
				$(this).dialog("close");
			}
		} ],
		close: function( event, ui ) {
		}
	});
}

function createCdiscountPublish () {
	createShopNameSelect();
	showCreatePublishDialog("Cdiscount 刊登");
}

function createShopNameSelect() {
	$.ajax({
		url : "/cdiscount/getShopNameByCreator",
		type: 'POST',
		dataType : "json",
		success : function (data) {
			$.unblockUI();
			var options = "";
			$.each(data, function (key, value) {
				options += "<option value='" + key + "'>" + value + "</option>";
			});
			$("#cdiscountPublishDialog select[name='shopName']").append(options);
		}
	});
}



function showCreatePublishDialog (title) {
	$("#cdiscountPublishDialog").dialog("option", "title", title);
	$("#cdiscountPublishDialog").dialog("open");
}

function getFirstCdiscountCategory() {
	var apiId = $("#cdiscountPublishDialog select[name='shopName']").val();
	if (apiId == "") {
		var param = {
			status : 2,
			message : "请选择店铺"
		};
		$.message.showMessage(param);
		return;
	}
	$.ajax({
		url : "/cdiscount/getFirstCdiscountCategory",
		type: 'POST',
		dataType : "json",
		data : {
			apiId : apiId
		}
		success : function (data) {
			$.message.showMessage(data);
		}
	});
}

function getCdiscountCategory (parentId, categoryLevel) {
	
}


