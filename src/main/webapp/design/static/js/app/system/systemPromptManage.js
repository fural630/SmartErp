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
					if (validate()) {
						saveSystemPromptConfig();
					}
				}
			},{
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#systemPromptConfigDialog");
		}
	});
}

function showSystemPromptConfigDialog (title) {
	$("#systemPromptConfigDialog").dialog("option", "title", title);
	$("#systemPromptConfigDialog").dialog("open");
}