$(function () {
	initDialog();
});

function initDialog () {
	$("#scriptConfigDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 620,
		height: 540,
		resizable: false,
		buttons : [ {
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					if (validate()) {
						saveScriptConfig();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#scriptConfigDialog");
			var validateFrom = $("#scriptConfigDialogForm").validate();
			validateFrom.resetForm();
		}
	});
}

function showScriptConfigDialog (title) {
	$("#scriptConfigDialog").dialog("option", "title", title);
	$("#scriptConfigDialog").dialog("open");
} 

function validate () {
	return $("#scriptConfigDialogForm").valid();
}

function saveScriptConfig () {
	var dialog = $("#scriptConfigDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var crontab = $.trim(dialog.find("input[name='crontab']").val());
	var scriptName = $.trim(dialog.find("input[name='scriptName']").val());
	var scriptType = dialog.find("select[name='scriptType']").val();
	var randomRange = "";
	if (scriptType != 5) {
		randomRange = dialog.find("select[name='randomRange']").val();
	}
	var isOpened = dialog.find("select[name='isOpened']").val();
	var scriptUrl = $.trim(dialog.find("textarea[name='scriptUrl']").val());
	var remark = $.trim(dialog.find("textarea[name='remark']").val());
	
	$.ajax({
		url : "/system/saveScriptConfig",
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
			crontab : crontab,
			scriptType : scriptType,
			randomRange : randomRange,
			isOpened : isOpened,
			scriptUrl : scriptUrl,
			scriptName : scriptName,
			remark : remark
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == 1) {
				$("#scriptConfigDialog").dialog("close");
				$.myformPlugins.cleanForm("#scriptConfigDialog");
				refresh(1000);
			}
		}
	});
}

function editScriptConfig (id) {
	var dialog = $("#scriptConfigDialog");
	$.ajax({
		url : "/system/editScriptConfig",
		type: 'POST',
		dataType : "json",
		data : {
			id : id
		},
		success : function (data) {
			$.unblockUI();
			if (null != data) {
				dialog.find("input[name='id']").val(data.id);
				dialog.find("input[name='crontab']").val(data.crontab);
				dialog.find("input[name='scriptName']").val(data.scriptName);
				dialog.find("select[name='scriptType']").val(data.scriptType);
				dialog.find("select[name='randomRange']").val(data.randomRange);
				dialog.find("select[name='isOpened']").val(data.isOpened);
				dialog.find("textarea[name='scriptUrl']").val(data.scriptUrl);
				dialog.find("textarea[name='remark']").val(data.remark);
				showScriptConfigDialog("编辑脚本配置");
			}
		}
	});
}

function deleteScriptConfig (id) {
	if(confirm("确定删除？")){
		var url = "/system/deleteScriptConfig";
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

function createCrontab () {
	if(confirm("确定生成脚本？")){
		var url = "/system/createCrontab";
		$.ajax({
			url : url,
			type: 'POST',
			dataType : "json",
			success : function (data) {
				$.message.showMessage(data);
				refresh(1000);
			}
		});
	}
}