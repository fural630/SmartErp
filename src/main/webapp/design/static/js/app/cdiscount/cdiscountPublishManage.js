$(function() {
	$("#datepicker").datepicker({
		showOn : "button",
		buttonImage : "/design/static/images/common/calendar_16px.png",
		buttonImageOnly : true,
		buttonText : "Select date"
	});
	CKEDITOR.replace('description');
	showCreatePublishDialog();
});

function showCreatePublishDialog() {
	$("#dialog").dialog({
		autoOpen: true,
		modal: true,
		width: 1000,
		height: 600,
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

function getFirstCdiscountCategory() {
	$("#cdCategoryTable").show();
	if ($("#categoryLevel_1").length == 0) {
		var parentId = 0;
		var categoryLevel = 1;
		getCdiscountCategory(parentId, categoryLevel);
	}
}

function getCdiscountCategory (parentId, categoryLevel) {
	
}