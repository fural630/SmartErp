$(function() {
	init();
	initDialog();
});

function init () {
	CKEDITOR.replace('marketingDescription');
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
				saveCdiscountPublish();
//				cleanCdiscountPublishDialog();
//				$(this).dialog("close");
			}
		} ],
		close: function( event, ui ) {
			cleanCdiscountPublishDialog();
		}
	});
}

function cleanCdiscountPublishDialog () {
	$.myformPlugins.cleanForm("#cdiscountPublishDialog");
	$("#navigation").html("");
}

function createCdiscountPublish () {
	updateShopNameSelect("");
	showCreatePublishDialog("Cdiscount 刊登");
	CKEDITOR.instances["description"].setData("");
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
			categoryHtml += 	"<a title='{categoryName}' href='javascript:getCdiscountCategoryByParentId({parentId}, {isParent}, {categoryLevel}, " +'"{categoryCode}"'+ ");'>";
			categoryHtml +=			"<div id='categoryDiv_{parentId}' value='{categoryName}'>";
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
			categoryHtml = categoryHtml.replace(/{categoryCode}/g, category.categoryCode == undefined ? "" : category.categoryCode);
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
		var navigation = "";
		$("#categoryArea").hide();
		categoryLevelDiv.each(function () {
			var categoryList = $(this).find("div[id^='categoryDiv_']");
			var categorySize = categoryList.size();
			categoryList.each(function (i) {
				if($(this).hasClass("categorySeleted")) {
					navigation += $(this).attr("value");
					if (i != categorySize -1) {
						navigation += " > ";
					}
					return false;
				}
			});
		});
		$("#navigation").html(navigation);
		$("#cdiscountPublishDialog input[name=categoryCode]").val(categoryCode);
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
	var sku = $.trim($("#cdiscountPublishDialog input[name='sku']").val());
	if (sku == "") {
		var param = {
			status : 0,
			message : "请先填写SKU"
		};
		$.message.showMessage(param);
		return;
	}
	var imageUrlAddress = $.trim($("#imageUrlAddress").val());
	if (imageUrlAddress == "") {
		var param = {
			status : 0,
			message : "请填写图片地址"
		};
		$.message.showMessage(param);
		return;
	} 
	var imageId = getMaxImageId();
	createSelectImageHtml(imageId, imageUrlAddress);
}

function createSelectImageHtml (imageId, imageUrlAddress) {
	var imageHtml = "";
	imageHtml += "<li>";
	imageHtml += 	"<div class='iamge_div' id='iamge_{imageId}'>";
	imageHtml +=		"<div class='image_border'><a onclick='onclickImage({imageId})'><img id='image_id_{imageId}' class='imageSize'  src='{imageUrlAddress}' title='{imageUrlAddress}'/></a></div>";
	imageHtml +=		"<div>";
	imageHtml +=			"<table class='image_operating_table'>";
	imageHtml +=				"<tr>";
	imageHtml +=					"<td><input type='checkbox' id='image_checkbox_{imageId}' onclick='setImageSelect({imageId})' value='${imageUrlAddress}'/></td>";
	imageHtml +=					"<td><a onclick='deleteImage({imageId})'><img src='/design/frame/style/img/del.png'/></a></td>";
	imageHtml +=				"<tr>";
	imageHtml +=			"</table>";
	imageHtml +=		"</div>";
	imageHtml +=	"</div>";
	imageHtml += "</li>";
	imageHtml = imageHtml.replace(/{imageUrlAddress}/g, imageUrlAddress);
	imageHtml = imageHtml.replace(/{imageId}/g, imageId);
	$("#sortable").append(imageHtml);
	clearImageUrlAddress();
	initSortable();
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


function initSortable() {
	$("#sortable").sortable();
} 

function saveCdiscountPublish(){
	var apiId = $("#cdiscountPublishDialog select[name='shopName']").val();
	var sku = $.trim($("#cdiscountPublishDialog input[name='sku']").val());
	var brandName = $.trim($("#cdiscountPublishDialog input[name='brandName']").val());
	var ean = $.trim($("#cdiscountPublishDialog input[name='ean']").val());
	var model = $.trim($("#cdiscountPublishDialog input[name='model']").val());
	var productKind = $("#cdiscountPublishDialog select[name='productKind']").val();
	var shortLabel = $.trim($("#cdiscountPublishDialog input[name='shortLabel']").val());
	var longLabel = $.trim($("#cdiscountPublishDialog input[name='longLabel']").val());
	var description = $.trim($("#cdiscountPublishDialog textarea[name='description']").val());
	var marketingDescription = CKEDITOR.instances["marketingDescription"].getData();
	var categoryCode = $.trim($("#cdiscountPublishDialog input[name='categoryCode']").val());
	var categoryName = $.trim($("#cdiscountPublishDialog input[name='categoryName']").val());
	var navigation = $("#navigation").text();
	var stockQty = $.trim($("#cdiscountPublishDialog input[name='stockQty']").val());
	var price = $.trim($("#cdiscountPublishDialog input[name='price']").val());
	var vat = $.trim($("#cdiscountPublishDialog input[name='vat']").val());
	var dea = $.trim($("#cdiscountPublishDialog input[name='dea']").val());
	var ecoPart = $.trim($("#cdiscountPublishDialog input[name='ecoPart']").val());
	var preparationTime = $.trim($("#cdiscountPublishDialog input[name='preparationTime']").val());
	var productCondition = $("#cdiscountPublishDialog select[name='productCondition']").val();
	
	var selectCheckbox = $("input[id^='image_checkbox_']");
	selectCheckbox.each(function () {
		if ($(this).is(":checked")) {
			
		}
	});
	
	var standardShippingCharges = $.trim($("#cdiscountPublishDialog input[name='standardShippingCharges']").val());
	var standardAdditionalShippingCharges = $.trim($("#cdiscountPublishDialog input[name='standardAdditionalShippingCharges']").val());
	var trackedShippingCharges = $.trim($("#cdiscountPublishDialog input[name='trackedShippingCharges']").val());
	var trackedAdditionalShippingCharges = $.trim($("#cdiscountPublishDialog input[name='trackedAdditionalShippingCharges']").val());
	var registeredShippingCharges = $.trim($("#cdiscountPublishDialog input[name='registeredShippingCharges']").val());
	var registeredAdditionalShippingCharges = $.trim($("#cdiscountPublishDialog input[name='registeredAdditionalShippingCharges']").val());
	
	$.ajax({
		url : "/cdiscount/insertCdiscountPublish",
		type: 'POST',
		dataType : "json",
		data : {
			apiId : apiId,
			sku : sku,
			brandName : brandName,
			ean : ean,
			model : model,
			productKind : productKind,
			shortLabel : shortLabel,
			longLabel : longLabel,
			navigation : navigation,
			description : description,
			marketingDescription : marketingDescription,
			categoryCode : categoryCode,
			categoryName : categoryName,
			stockQty : stockQty,
			price : price,
			vat : vat,
			dea : dea,
			ecoPart : ecoPart,
			preparationTime : preparationTime,
			productCondition : productCondition,
			standardShippingCharges : standardShippingCharges,
			standardAdditionalShippingCharges : standardAdditionalShippingCharges,
			trackedShippingCharges : trackedShippingCharges,
			trackedAdditionalShippingCharges : trackedAdditionalShippingCharges,
			registeredShippingCharges : registeredShippingCharges,
			registeredAdditionalShippingCharges : registeredAdditionalShippingCharges
		},
		success : function (data) {
			$.unblockUI();
		}
	});
	
	
}




