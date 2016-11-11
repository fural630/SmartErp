$(function () {
	initDialog();
	CKEDITOR.replace('content',  { height: '200px' });
});
function initDialog () {
	$("#systemPromptConfigDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 800,
//		height: 400,
		resizable: false,
		buttons : [ {
				text : "查看效果",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					browseResults();
				}
			},{
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					if (validate()) {
						saveSystemPromptConfig();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#systemPromptConfigDialog");
			CKEDITOR.instances["content"].setData("");
		}
	});
}

function setHeightWidthDefault () {
	var dialog = $("#systemPromptConfigDialog");
	dialog.find("input[name='height']").val(310);
	dialog.find("input[name='width']").val(430);
}

function browseResults() {
	var dialog = $("#systemPromptConfigDialog");
	var title = $.trim(dialog.find("input[name='title']").val());
	var height = $.trim(dialog.find("input[name='height']").val());
	var width = $.trim(dialog.find("input[name='width']").val());
	var content = CKEDITOR.instances["content"].getData();
	$("#systemPromptDialog").find("#_systemPrompt").html(content);
	$("#systemPromptDialog").dialog("option", "title", title);
	$("#systemPromptDialog").dialog("option", "height", height);
	$("#systemPromptDialog").dialog("option", "width", width);
	$("#systemPromptDialog").dialog("open");
}

function showSystemPromptConfigDialog (title) {
	$("#systemPromptConfigDialog").dialog("option", "title", title);
	$("#systemPromptConfigDialog").dialog("open");
}

function validate () {
	return $("#systemPromptConfigDialogForm").valid();
}

function saveSystemPromptConfig () {
	var dialog = $("#systemPromptConfigDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var title = $.trim(dialog.find("input[name='title']").val());
	var height = $.trim(dialog.find("input[name='height']").val());
	var width = $.trim(dialog.find("input[name='width']").val());
	var address = dialog.find("textarea[name='address']").val();
	var content = CKEDITOR.instances["content"].getData();
	$.ajax({
		url : "/system/saveSystemPromptConfig",
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
			title : title,
			height : height,
			width : width,
			address : address,
			content : content
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == 1) {
				$("#systemPromptConfigDialog").dialog("close");
				$.myformPlugins.cleanForm("#systemPromptConfigDialog");
				refresh(1000);
			}
		}
	});
}

function editSystemPrompt (id) {
	var url = "/system/editSystemPrompt";
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
				var systemPrompt = data;
				var dialog = $("#systemPromptConfigDialog");
				dialog.find("input[name='id']").val(systemPrompt.id);
				dialog.find("input[name='title']").val(systemPrompt.title);
				dialog.find("input[name='height']").val(systemPrompt.height);
				dialog.find("input[name='width']").val(systemPrompt.width);
				dialog.find("textarea[name='address']").val(systemPrompt.address);
				CKEDITOR.instances["content"].setData(systemPrompt.content);
				showSystemPromptConfigDialog("编辑系统提示");
			} else {
				var param = {
					status : -1
				};
				$.message.showMessage(param);
			}
			
		}
	});
}