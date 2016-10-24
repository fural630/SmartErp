$(function () {
	initDialog();
});
function initDialog () {
	$("#cdiscountEanDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		height: 400,
		resizable: false,
		buttons : [{
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					if (validate()) {
						batchSaveCdiscountEan();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#cdiscountEanDialog");
		}
	});
}

function validate () {
	var eanTextarea = $("#cdiscountEanDialog textarea[name='eanTextArea']").val().trim();
	if (eanTextarea == "") {
		alert("请填写EAN");
		return false;
	}
	return true;
}

function batchSaveCdiscountEan () {
	var eanTextarea = $("#cdiscountEanDialog textarea[name='eanTextArea']").val().trim();
	var eanArray = eanTextarea.split("\n");
	var eanList = [];
	for (var i = 0; i < eanArray.length; i++) {
		var ean = eanArray[i].trim();
		if (ean != "") {
			eanList.push(ean);
		}
	}
	
	if (eanList.length > 0) {
		$.ajax({
			url : "/cdiscount/batchSaveCdiscountEan",
			type: 'POST',
			dataType : "json",
			data : {
				eanList : eanList
			},
			success : function (data) {
				$.unblockUI();
				alert(data);
//				$.message.showMessage(param);
			}
		});
	}
}

function showCdiscountEanDialog (title) {
	$("#cdiscountEanDialog").dialog("option", "title", title);
	$("#cdiscountEanDialog").dialog("open");
}