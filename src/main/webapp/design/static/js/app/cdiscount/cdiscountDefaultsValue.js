$(function () {
	initDialog();
});

function initDialog () {
	$("#cdiscountDefaultsValueDialog").dialog({
		autoOpen: true,
		modal: true,
		width: 800,
		height: 560,
		resizable: false,
		buttons : [{
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					if (validate()) {
						saveCdiscountApiConfig();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#cdiscountDefaultsValueDialog");
		}
	});
}