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
	$("#categoryArea").html("");
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
			categoryHtml +=			"<div id='categoryDiv_{parentId}'>";
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
	categoryHtml = categoryHtml.replace(/{level}/g, level);
	$("#categoryArea").append(categoryHtml);
	
}

function getCdiscountCategoryByParentId(parentId, isParent, categoryLevel, categoryCode) {
	var categoryDivList = $("#categoryLevel_" + categoryLevel).find("div[id^='categoryDiv_']");
	categoryDivList.each(function () {
		$(this).removeClass("categorySeleted");
	});
	$("#categoryLevel_" + categoryLevel).find("div[id=categoryDiv_"+ parentId +"]").addClass("categorySeleted");
	
	var categoryLevelDiv = $("div[id^=categoryLevel_]");
	categoryLevelDiv.each(function () {
		var categoryLevelIdStr = $(this).attr("id");
		var categoryLevelId = parseInt(categoryLevelIdStr.split("_")[1]);
		if (categoryLevelId > categoryLevel) {
			$("#categoryLevel_" + categoryLevelId).remove();	
		}
	});
	
	if (isParent == 1) {
		var nextCategoryLevel = categoryLevel + 1;
		getCdiscountCategory(parentId, nextCategoryLevel);
	}
	
	if (isParent == 0 ) {
		var selectCategoryPath = "";
		$("#categoryArea").hide();
		categoryLevelDiv.each(function () {
			var categoryList = $(this).find("div[id^='categoryDiv_']");
			categoryList.each(function () {
				if($(this).hasClass("categorySeleted")) {
					selectCategoryPath += $(this).html(); + "&nbsp;";
					return false;
				}
			});
		});
		$("#selectCategoryPath").html(selectCategoryPath);
		$("#cdiscountPublishDialog input[name=categoryId]").val(categoryCode);
		$("#cdiscountPublishDialog input[name=categoryName]").val($("#categoryDiv_" + parentId).html());
	}
}

function getCdiscountCategory (parentId, categoryLevel) {
	$.ajax({
		url : "/cdiscount/getCdiscountCategoryByParentId",
		type: 'POST',
		dataType : "json",
		data : {
			parentId : parentId
		},
		success : function (data) {
			$.unblockUI();
			if (data.length > 0) {
				showCdiscountCategory(data, categoryLevel);
			}
		}
	});
}

function addImageUrlAddress () {
	var imageUrlAddress = $.trim($("#imageUrlAddress").val());
	if (imageUrlAddress == "") {
		var param = {
			status : 0,
			message : "请填写图片地址"
		};
		$.message.showMessage(param);
	} else {
		var imageId = getMaxImageId();
		createSelectImageHtml(imageId, imageUrlAddress);
	}
}

function createSelectImageHtml (imageId, imageUrlAddress) {
	var imageHtml = "";
	imageHtml += 	"<div class='iamge_div' id='iamge_{imageId}'>";
	imageHtml +=		"<div><a onclick='onclickImage({imageId})'><img id='image_id_{imageId}' class='imageSize'  src='{imageUrlAddress}' title='{imageUrlAddress}'/></a></div>";
	imageHtml +=		"<div>";
	imageHtml +=			"<table class='image_operating_table'>";
	imageHtml +=				"<tr>";
	imageHtml +=					"<td><input type='checkbox' id='image_checkbox_{imageId}' onclick='setImageSelect({imageId})'/></td>";
	imageHtml +=					"<td><a onclick='deleteImage({imageId})'><img src='/design/frame/style/img/del.png'/></a></td>";
	imageHtml +=				"<tr>";
	imageHtml +=			"</table>";
	imageHtml +=		"</div>";
	imageHtml +=	"</div>";
	
	imageHtml = imageHtml.replace(/{imageUrlAddress}/g, imageUrlAddress);
	imageHtml = imageHtml.replace(/{imageId}/g, imageId);
	$(".image_area").append(imageHtml);
	clearImageUrlAddress();
}

function getMaxImageId () {
	var imageboxList = $("div[id^='iamge_']");
	var maxImageBox = "";
	var maxImageId = 0;
	imageboxList.each(function () {
		if ($(this).attr("id") > maxImageBox) {
			maxImageBox = $(this).attr("id");
		}
	});
	if (maxImageBox != "") {
		maxImageId = parseInt(maxImageBox.split("_")[1]);
	} 
	return maxImageId + 1;
}

function clearImageUrlAddress () {
	$("#imageUrlAddress").val("");
}

function onclickImage (imageId) {
	var image = $("#image_id_" + imageId);
	var imageCheckBox = $("#image_checkbox_" + imageId);
	if (image.hasClass("sortingSelect")) {
		image.removeClass("sortingSelect");
		image.addClass("imageSize");
		imageCheckBox.attr("checked", false);
	} else {
		image.removeClass("imageSize");
		image.addClass("sortingSelect");
		imageCheckBox.attr("checked", true);
	}
	countSelectImage();
}

function setImageSelect (imageId) {
	var image = $("#image_id_" + imageId);
	var imageCheckBox = $("#image_checkbox_" + imageId);
	var isChecked = imageCheckBox.is(":checked");
	if (isChecked) {
		image.removeClass("imageSize");
		image.addClass("sortingSelect");
	} else {
		image.removeClass("sortingSelect");
		image.addClass("imageSize");
	}
	countSelectImage();
}

function countSelectImage() {
	var selectCheckbox = $("input[id^='image_checkbox_']");
	var count = 0;
	selectCheckbox.each(function () {
		if ($(this).is(":checked")) {
			count ++;
		}
	});
	$("#selectImageCount").html(count);
}

function deleteImage (imageId) {
	if(confirm("确定删除？")){
		$("#iamge_" + imageId).remove();
	}
}




