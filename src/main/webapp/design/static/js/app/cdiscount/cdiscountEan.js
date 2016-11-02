$(function () {
	initDialog();
});
function initDialog () {
	$("#cdiscountEanDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		height: 600,
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
			$("#resultMassage").hide();
			$("#importEanResult").text("");
			$.myformPlugins.cleanForm("#cdiscountEanDialog");
			refresh();
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
	var eanTextarea = $.trim($("#cdiscountEanDialog textarea[name='eanTextArea']").val());
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
				$("#failCount").text(data.failCount);
				$("#successCount").text(data.successCount);
				$("#resultMassage").show();
				var resultHtml = "";
				if(data.eanImportResultList.length > 0) {
					for(var i = 0; i < data.eanImportResultList.length; i++) {
						resultHtml += "<div>" + data.eanImportResultList[i].ean + " --- " + data.eanImportResultList[i].reason + "</div>";
					}
				}
				$("#importEanResult").html(resultHtml);
			}
		});
	}
}

function showCdiscountEanDialog (title) {
	$("#cdiscountEanDialog").dialog("option", "title", title);
	$("#cdiscountEanDialog").dialog("open");
}