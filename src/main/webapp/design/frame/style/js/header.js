$(function () {
	initDialog();
});

function initDialog () {
	$("#personDialog").dialog({
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
					if (validate()) {
						saveUser();
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
function loginOut () {
	$("#loginOutForm").submit();
}

function modifyPersonInfo () {
	$("#personDialog").dialog("open");
	$("#personDialog").dialog("option", "title", "修改个人信息");
}