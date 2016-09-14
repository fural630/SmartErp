$(function() {
	init();
	initDialog();
});

function init () {
	CKEDITOR.replace('description');
}

function initDialog() {
	$("#cdiscountPublishDialog").dialog({
		autoOpen: false,
		modal: true,
		width: document.body.scrollWidth * 0.8,
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

function createCdiscountPublish () {
	updateShopNameSelect("");
	showCreatePublishDialog("Cdiscount 刊登");
}

function updateShopNameSelect(value) {
	$.ajax({
		url : "/cdiscount/getShopNameByCreator",
		type: 'POST',
		dataType : "json",
		success : function (data) {
			$.unblockUI();
			var options = "<option value=''>-- 请选择 --</option>";
			$.each(data, function (key, value) {
				options += "<option value='" + key + "'>" + value + "</option>";
			});
			$("#cdiscountPublishDialog select[name='shopName']").html(options);
		}
	});
}



function showCreatePublishDialog (title) {
	$("#cdiscountPublishDialog").dialog("option", "title", title);
	$("#cdiscountPublishDialog").dialog("open");
}

function getFirstCdiscountCategory() {
	var apiId = $("#cdiscountPublishDialog select[name='shopName']").val();
	if (apiId == "") {
		var param = {
			status : 0,
			message : "请选择店铺"
		};
		$.message.showMessage(param);
		return;
	}
	$.ajax({
		url : "/cdiscount/getFirstCdiscountCategory",
		type: 'POST',
		dataType : "json",
		data : {
			apiId : apiId
		},
		success : function (data) {
			$.unblockUI();
			if (data.length > 0) {
				showCdiscountCategory(data, 1);
			}
		}
	});
}

function showCdiscountCategory (categoryList, level) {
	$("#categoryArea").show();
	var categoryHtml = "";
	categoryHtml += "<div class='category_select_box' id='categoryLevel_{level}'>";
	categoryHtml += 	"<ul>";
		$.each(categoryList, function (i, category) {
			categoryHtml +=	"<li>";
			categoryHtml += 	"<a title='{categoryName}' href='javascript:getCdiscountCategoryByParentId({parentId}, {isParent}, {categoryLevel}, {categoryCode});'>";
			categoryHtml +=			"<div>";
			categoryHtml +=				"{categoryName}{isParentMark}";
			categoryHtml +=			"</div>";
			categoryHtml +=		"</a>";
			categoryHtml +=	"</li>";
			
			var isParentMark = "";
			if (category.isParent == 1) {
				isParentMark = "&nbsp;&nbsp;>>";
			}
			categoryHtml = categoryHtml.replace(/{parentId}/g, category.id);
			categoryHtml = categoryHtml.replace(/{isParent}/g, category.isParent);
			categoryHtml = categoryHtml.replace(/{categoryLevel}/g, category.categoryLevel);
			categoryHtml = categoryHtml.replace(/{categoryCode}/g, category.categoryCode);
			categoryHtml = categoryHtml.replace(/{categoryName}/g, category.name);
			categoryHtml = categoryHtml.replace(/{isParentMark}/g, isParentMark);
		});
	categoryHtml += 	"</ul>";
	categoryHtml += "</div>";
	categoryHtml.replace(/{level}/g, level);
	$("#categoryArea").append(categoryHtml);
	
}

function getCdiscountCategoryByParentId() {
	alert(1);
}

function getCdiscountCategory (parentId, categoryLevel) {
	
}


