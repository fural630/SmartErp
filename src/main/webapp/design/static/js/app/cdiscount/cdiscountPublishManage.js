$(function() {
	$("#datepicker").datepicker({
		showOn : "button",
		buttonImage : "/design/static/images/common/calendar_16px.png",
		buttonImageOnly : true,
		buttonText : "Select date"
	});
	
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
		} ]
	});
	
}