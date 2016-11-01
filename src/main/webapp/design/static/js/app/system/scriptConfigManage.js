$(function () {
	initDialog();
});

function initDialog () {
	$("#scriptConfigDialog").dialog({
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
			$.myformPlugins.cleanForm("#scriptConfigDialog");
			var validateFrom = $("#scriptConfigDialogForm").validate();
			validateFrom.resetForm();
		}
	});
}