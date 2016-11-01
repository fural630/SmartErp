$(function() {
	init();
	initDialog();
});

function init () {
	CKEDITOR.replace('marketingDescription',  { height: '360px' });
}

function initDialog() {
	$("#cdiscountPublishDialog").dialog({
		autoOpen: false,
		modal: true,
		width: document.body.scrollWidth * 0.9,
//		height: document.body.scrollHeight * 0.9,
		resizable: false,
		buttons : [ {
			text : "保存",
			icons : {
				primary : "ui-icon-heart"
			},
			click : function() {
				if (validate()) {
					saveCdiscountPublish();
				}
			}
		} ],
		close: function( event, ui ) {
			cleanCdiscountPublishDialog();
		}
	});
}

function onChangeShopName() {
	showDeliveryMode();
}

function showDeliveryMode () {
	var apiId = $("#cdiscountPublishDialog select[name='shopName']").val();
	$.ajax({
		url : "/cdiscount/getDeliveryModeInfoByApiId",
		type: 'POST',
		dataType : "json",
		async: false,
		data : {
			apiId : apiId
		},
		success : function (data) {
			$.unblockUI();
			var deliveryModeInfoList = data;
			$("#deliveryModeArea").html("");
			createSmallParcelHtml(deliveryModeInfoList);
			createBigParcelHtml(deliveryModeInfoList);
		}
	});
}

function createSmallParcelHtml (deliveryModeInfoList) {
	var haveSmallParcel = false;
	$.each(deliveryModeInfoList, function (i, obj) {
		if (obj.deliveryModeType == 5) {
			haveSmallParcel = true;
		}
	});
	if (!haveSmallParcel) {
		return;
	}
	var smallParcelHtml = "";
	smallParcelHtml += "<div class='deliveryModeTitle'>Home delivery - Small parcel (Less than 30 kg)</div>";
	smallParcelHtml += 		"<table class='shippingChargesInfoTable'>";
	smallParcelHtml += 			"<tr>";
	smallParcelHtml += 				"<td class='title' style='width:33%'>物流方式</td>";
	smallParcelHtml += 				"<td class='title'>运费(€)(含税)</td>";
	smallParcelHtml += 				"<td class='title'>额外运费(€)(含税)</td>";
	smallParcelHtml += 			"</tr>";
	$.each(deliveryModeInfoList, function (i, obj) {
		if (obj.deliveryModeType == 5) {		//com.smartErp.application.libraries.constentEnum.DeliveryModeTypeEnum.java
			smallParcelHtml += 			"<tr class='deliveryModeTr'>";
			smallParcelHtml += 				"<td>"+ obj.modeNameEN +"<input type='hidden' name='deliveryMode' value='" + obj.modeNameEN + "'/></td>";
			smallParcelHtml += 				"<td><input type='text' onkeyup='inputNumOnly(this)' name='shippingCharges' class='txt width_50px' /></td>";
			smallParcelHtml += 				"<td><input type='text' onkeyup='inputNumOnly(this)' name='addShippingCharges' class='txt width_50px' /></td>";
			smallParcelHtml += 			"</tr>";
		}
	});
	smallParcelHtml += "</table>";
	$("#deliveryModeArea").append(smallParcelHtml);
}

function createBigParcelHtml (deliveryModeInfoList) {
	var haveBigParcel = false;
	$.each(deliveryModeInfoList, function (i, obj) {
		if (obj.deliveryModeType == 10) {
			haveBigParcel = true;
		}
	});
	if (!haveBigParcel) {
		return;
	}
	
	var bigParcelHtml = "";
	bigParcelHtml += "<div class='deliveryModeTitle'>Home delivery - Big parcel (More than 30 kg)</div>";
	bigParcelHtml += 		"<table class='shippingChargesInfoTable'>";
	bigParcelHtml += 			"<tr>";
	bigParcelHtml += 				"<td class='title' style='width:33%'>物流方式</td>";
	bigParcelHtml += 				"<td class='title'>运费(€)(含税)</td>";
	bigParcelHtml += 				"<td class='title'>额外运费(€)(含税)</td>";
	bigParcelHtml += 			"</tr>";
	$.each(deliveryModeInfoList, function (i, obj) {
		if (obj.deliveryModeType == 10) {	//com.smartErp.application.libraries.constentEnum.DeliveryModeTypeEnum.java
			bigParcelHtml += 			"<tr class='deliveryModeTr'>";
			bigParcelHtml += 				"<td>"+ obj.modeNameEN +"<input type='hidden' name='deliveryMode' value='" + obj.modeNameEN + "'/></td>";
			bigParcelHtml += 				"<td><input type='text' onkeyup='inputNumOnly(this)' name='shippingCharges' class='txt width_50px' /></td>";
			bigParcelHtml += 				"<td><input type='text' onkeyup='inputNumOnly(this)' name='addShippingCharges' class='txt width_50px' /></td>";
			bigParcelHtml += 			"</tr>";
		}
	});
	bigParcelHtml += "</table>";
	$("#deliveryModeArea").append(bigParcelHtml);
}

function cleanCdiscountPublishDialog () {
	$.myformPlugins.cleanForm("#cdiscountPublishDialog");
	$("#navigation").html("");
	$("#sortable").html("");
	CKEDITOR.instances["marketingDescription"].setData("");
	$(".validateTip").html("");
	$(".validateTip").hide();
}

function createCdiscountPublish () {
	updateShopNameSelect("");
	showCreatePublishDialog("Cdiscount 创建商品刊登");
}

function updateShopNameSelect(apiId) {
	$.ajax({
		url : "/cdiscount/getShopNameByCreator",
		type: 'POST',
		dataType : "json",
		async: false,
		success : function (data) {
			$.unblockUI();
			var options = "<option value=''>-- 请选择 --</option>";
			$.each(data, function (key, value) {
				if (apiId != undefined && apiId == key) {
					options += "<option value='" + key + "' selected >" + value + "</option>";
				} else {
					options += "<option value='" + key + "'>" + value + "</option>";
				}
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
	$("#categoryArea").html("");
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
					navigation += $(this).attr("value") + " > ";
					return false;
				}
			});
		});
		navigation = navigation.substring(0, navigation.length - 3);//去掉最后一个 >
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
	imageHtml +=					"<td><input type='checkbox' id='image_checkbox_{imageId}' onclick='setImageSelect({imageId})' value='{imageUrlAddress}'/></td>";
	imageHtml +=					"<td><a onclick={showBigImage} title='点击放大图片'><img src='/design/frame/style/img/query.png'/></a></td>"
	imageHtml +=					"<td><a onclick='deleteImage({imageId})' title='点击删除图片'><img src='/design/frame/style/img/del.png'/></a></td>";
	imageHtml +=				"<tr>";
	imageHtml +=			"</table>";
	imageHtml +=		"</div>";
	imageHtml +=	"</div>";
	imageHtml += "</li>";
	imageHtml = imageHtml.replace(/{imageUrlAddress}/g, imageUrlAddress);
	imageHtml = imageHtml.replace(/{imageId}/g, imageId);
	var showBigImage = "showBigImage('" + imageUrlAddress + "')";
	imageHtml = imageHtml.replace(/{showBigImage}/g, showBigImage);
	$("#sortable").append(imageHtml);
	clearImageUrlAddress();
	initSortable();
}

function showBigImage (src) {
	var width = 106;
	var height = 110;
	var largeMultiple = 5;
	if ($("#bigImageDiv").length  > 0) {
		$("#bigImageDiv").remove();
	}
	var bigImageDivHtml = "<div id='bigImageDiv'></div>";
	$("body").append(bigImageDivHtml);
	$("#bigImageDiv").css({
		position: "absolute",
		display : "none",
		top : ($(window).height() - width * largeMultiple)/2 + $(window).scrollTop(),
		left : ($(window).width() - height * largeMultiple)/2 + $(window).scrollLeft()
	});
	
	var bigImage = $("<img>").css({
		width: width * largeMultiple,
		height: height * largeMultiple,
		border: "7px solid #ddd",
		padding: 7,
		backgroundColor: "#fff"
	});
	
	bigImage.attr("src", src);
	bigImage.attr("onclick", "removeBigImage(this)");
	bigImage.attr("title", "点击关闭图片");
	$("#bigImageDiv").append(bigImage);
	$("#bigImageDiv").show(500);
}

function removeBigImage (obj) {
	$(obj).hide(100);
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

function clearImageArea() {
	$("#sortable").html("");
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

function onChangeSku () {
	reloadSkuImage();
}

function reloadSkuImage(publishImageList) {
	var sku = $.trim($("#cdiscountPublishDialog input[name='sku']").val());
	$.ajax({
		url : "/cdiscount/reloadSkuImage",
		type: 'POST',
		dataType : "json",
		data : {
			sku : sku,
		},
		success : function (data) {
			$.unblockUI();
			var allImageList = data;
			clearImageArea();
			if (publishImageList != undefined && publishImageList.length > 0) {
				var mergeList = mergeArray(publishImageList, allImageList);
				$.each(mergeList, function (i, imageUrl) {
					var imageId = getMaxImageId();
					createSelectImageHtml(imageId, imageUrl);
					if (publishImageList.indexOf(imageUrl) > -1) {
						onclickImage(imageId);
					} 
				});
			} else {
				$.each(allImageList, function (i, imageUrl) {
					var imageId = getMaxImageId();
					createSelectImageHtml(imageId, imageUrl);
				});
			}
		}
	});
}

function mergeArray(arr1, arr2) {
	var arr = [];
	for ( var i = 0; i < arr1.length; i++) {
		arr.push(arr1[i]);
	}
	var dup;
	for ( var i = 0; i < arr2.length; i++) {
		dup = false;
		for ( var j = 0; j < arr1.length; j++) {
			if (arr2[i] == arr1[j]) {
				dup = true;
				break;
			}
		}
		if (!dup) {
			arr.push(arr2[i]);
		}
	}
	return arr;
}  

function validate () {
	var errorMassage = "";
	var apiId = $("#cdiscountPublishDialog select[name='shopName']").val();
	if (apiId == "") {
		errorMassage += "&nbsp;*&nbsp;请选择刊登店铺";
	}
	var sku = $.trim($("#cdiscountPublishDialog input[name='sku']").val());
	if (sku == "") {
		errorMassage += "&nbsp;*&nbsp;请填写SKU";
	}
	var brandName = $.trim($("#cdiscountPublishDialog input[name='brandName']").val());
	if (brandName == "") {
		errorMassage += "&nbsp;*&nbsp;请填写品牌名";
	}
	var ean = $.trim($("#cdiscountPublishDialog input[name='ean']").val());
	if (ean == "") {
		errorMassage += "&nbsp;*&nbsp;请填写EAN";
	}
	var shortLabel = $.trim($("#cdiscountPublishDialog input[name='shortLabel']").val());
	if (shortLabel == "") {
		errorMassage += "&nbsp;*&nbsp;请填写短描述";
	}
	var longLabel = $.trim($("#cdiscountPublishDialog input[name='longLabel']").val());
	if (longLabel == "") {
		errorMassage += "&nbsp;*&nbsp;请填写长描述";
	}
	var description = $.trim($("#cdiscountPublishDialog textarea[name='description']").val());
	if (description == "") {
		errorMassage += "&nbsp;*&nbsp;请填写简要描述";
	}
	var marketingDescription = CKEDITOR.instances["marketingDescription"].getData();
	if (marketingDescription == "") {
		errorMassage += "&nbsp;*&nbsp;请填写市场描述";
	}
	var categoryCode = $.trim($("#cdiscountPublishDialog input[name='categoryCode']").val());
	if (categoryCode == "") {
		errorMassage += "&nbsp;*&nbsp;请选择产品类别或填写平台类别编号";
	}
	var stockQty = $.trim($("#cdiscountPublishDialog input[name='stockQty']").val());
	if (stockQty == "") {
		errorMassage += "&nbsp;*&nbsp;请填写数量";
	}
	var price = $.trim($("#cdiscountPublishDialog input[name='price']").val());
	if (price == "") {
		errorMassage += "&nbsp;*&nbsp;请填写价格";
	}
	var vat = $.trim($("#cdiscountPublishDialog input[name='vat']").val());
	if (vat == "") {
		errorMassage += "&nbsp;*&nbsp;请填写VAT";
	}
	var dea = $.trim($("#cdiscountPublishDialog input[name='dea']").val());
	if (dea == "") {
		errorMassage += "&nbsp;*&nbsp;请填写DEA";
	}
	var ecoPart = $.trim($("#cdiscountPublishDialog input[name='ecoPart']").val());
	if (ecoPart == "") {
		errorMassage += "&nbsp;*&nbsp;请填写Eco Part";
	}
	var preparationTime = $.trim($("#cdiscountPublishDialog input[name='preparationTime']").val());
	if (preparationTime == "") {
		errorMassage += "&nbsp;*&nbsp;请填写备货时间";
	}
	
	var imageCount = 0;
	$("input[id^='image_checkbox_']").each(function () {
		if ($(this).is(":checked")) {
			imageCount++;
		}
	});
	
	if (imageCount == 0 || imageCount > 4) {
		errorMassage += "&nbsp;*&nbsp;刊登图片至少选择一张且小于等于四张";
	}
	
	var deliveryModeTr = $(".deliveryModeTr");
	deliveryModeTr.each(function () {
		var deliveryMode = $(this).find("input[name='deliveryMode']").val();
		var shippingCharges = $.trim($(this).find("input[name='shippingCharges']").val());
		var addShippingCharges = $.trim($(this).find("input[name='addShippingCharges']").val());
		if ((shippingCharges == "" && addShippingCharges != "") || (shippingCharges != "" && addShippingCharges == "")) {
			errorMassage += "&nbsp;*&nbsp;" + deliveryMode + "物流方式的运费与额外运费要么都填，要么都不填 ";
		}
	});
	
	if (errorMassage != "") {
		$(".validateTip").html(errorMassage);
		$(".validateTip").show();
		return false;
	} else {
		$(".validateTip").hide();
	}
	return true; 
}

function saveCdiscountPublish(){
	var id = $("#cdiscountPublishDialog input[name='publishId']").val();
	var apiId = $("#cdiscountPublishDialog select[name='shopName']").val();
	var sku = $.trim($("#cdiscountPublishDialog input[name='sku']").val());
	var brandName = $.trim($("#cdiscountPublishDialog input[name='brandName']").val());
	var ean = $.trim($("#cdiscountPublishDialog input[name='ean']").val());
//	var model = $.trim($("#cdiscountPublishDialog input[name='model']").val());
	var model = "SOUMISSION CREATION PRODUITS_MK";
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
	
	var selectedImageList = [];		//选中的刊登图片
	var allImageList = [];		//SKU的图片
	var mainImage = "";
	$("input[id^='image_checkbox_']").each(function () {
		allImageList.push($(this).val());
		if ($(this).is(":checked")) {
			selectedImageList.push($(this).val());
			if (mainImage == "") {
				mainImage = $(this).val();
			}
		}
	});
	
	var deliveryModeTr = $(".deliveryModeTr");
	var publishDeliveryModeList = new Array();  
	deliveryModeTr.each(function () {
		var deliveryMode = $(this).find("input[name='deliveryMode']").val();
		var shippingCharges = $.trim($(this).find("input[name='shippingCharges']").val());
		var addShippingCharges = $.trim($(this).find("input[name='addShippingCharges']").val());
		if (shippingCharges != "" && addShippingCharges != "") {
			publishDeliveryModeList.push({
				deliveryMode: deliveryMode,
				shippingCharges: shippingCharges,
				addShippingCharges : addShippingCharges
			}); 
		}
	});
	
	$.ajax({
		url : "/cdiscount/saveCdiscountPublish",
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
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
			publishDeliveryModeList : JSON.stringify(publishDeliveryModeList),
			selectedImageList : selectedImageList,
			allImageList : allImageList,
			mainImage : mainImage
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == 1) {
				$("#cdiscountPublishDialog").dialog("close");
				cleanCdiscountPublishDialog();
				refresh(1000);
			}
		}
	});
}

function editCdiscountPublish (id) {
	showCreatePublishDialog("Cdiscount 修改商品刊登");
	$("input[name='publishId']").val(id);
	$.ajax({
		url : "/cdiscount/editCdiscountPublish",
		type: 'POST',
		dataType : "json",
		data : {
			publishId : id
		},
		success : function (data) {
			$.unblockUI();
			var publishImageList = data.publishImageList;
			var publishDeliveryModeList = data.publishDeliveryModeList;
			var sku = data.sku;
			$("#cdiscountPublishDialog input[name='sku']").val(sku);//填充SKU
			var cdiscountPublish = data.cdiscountPublish;
			updateShopNameSelect(cdiscountPublish.apiId);		//设置店铺选中
			showDeliveryMode();		//显示运费模板信息
			fillingDeliveryModeData(publishDeliveryModeList);		//填充运费信息
			reloadSkuImage(publishImageList);		//重新加载SKU对应的图片，并将刊登图片设置选中状态
			
			$("#cdiscountPublishDialog input[name='brandName']").val(cdiscountPublish.brandName);
			$("#cdiscountPublishDialog input[name='ean']").val(cdiscountPublish.ean);
			$("#cdiscountPublishDialog select[name='productKind']").val(cdiscountPublish.productKind);
			$("#cdiscountPublishDialog input[name='shortLabel']").val(cdiscountPublish.shortLabel);
			$("#cdiscountPublishDialog input[name='longLabel']").val(cdiscountPublish.longLabel);
			$("#cdiscountPublishDialog textarea[name='description']").val(cdiscountPublish.description);
			CKEDITOR.instances["marketingDescription"].setData(cdiscountPublish.marketingDescription);
//			CKEDITOR.instances['marketingDescription'].document.$.body.innerHTML = cdiscountPublish.marketingDescription;
			$("#cdiscountPublishDialog input[name='categoryCode']").val(cdiscountPublish.categoryCode);
			$("#cdiscountPublishDialog input[name='categoryName']").val(cdiscountPublish.categoryName);
			$("#navigation").html(cdiscountPublish.navigation);
			$("#cdiscountPublishDialog input[name='stockQty']").val(cdiscountPublish.stockQty);
			$("#cdiscountPublishDialog input[name='price']").val(cdiscountPublish.price);
			$("#cdiscountPublishDialog input[name='vat']").val(cdiscountPublish.vat);
			$("#cdiscountPublishDialog input[name='dea']").val(cdiscountPublish.dea);
			$("#cdiscountPublishDialog input[name='ecoPart']").val(cdiscountPublish.ecoPart);
			$("#cdiscountPublishDialog input[name='preparationTime']").val(cdiscountPublish.preparationTime);
			$("#cdiscountPublishDialog select[name='productCondition']").val(cdiscountPublish.productCondition);
		}
	});
	
}


/**
 * 填充运费信息
 * @param publishDeliveryModeList
 */
function fillingDeliveryModeData (publishDeliveryModeList) {
	var deliveryModeTr = $(".deliveryModeTr");
	if (publishDeliveryModeList.length > 0 ) {
		$.each(publishDeliveryModeList, function (i, obj) {
			deliveryModeTr.each(function () {
				var deliveryMode = $(this).find("input[name='deliveryMode']").val();
				if (deliveryMode == obj.deliveryMode) {
					$(this).find("input[name='shippingCharges']").val(obj.shippingCharges);
					$(this).find("input[name='addShippingCharges']").val(obj.addShippingCharges);
					return false;		//break;
				}
			});
		});
	}
}

/**
 * 点击批量操作的提交按钮
 */
function batchOptionSubmit () {
	var batchOption = $("#batchOptionSelect").val();
	if (batchOption == "") {
		return;
	} 
	
	var idList = getBatchOptionIds();//获取勾选的数据Id
	if (idList.length == 0) {
		var param = {
			status : 0,
			message : "请勾选需要操作的数据"
		};
		$.message.showMessage(param);
		return;
	}
	
	if (batchOption == "batchShelvesProduct") {
		batchShelvesProduct(idList);
	} else if (batchOption == "batchUploadOffers") {
		batchUploadOffers(idList);
	} else if (batchOption == "batchDelete") {
		batchDelete(idList);
	} else if (batchOption == "batchUpdateToWaitPendding") {
		batchUpdateToWaitPendding(idList);
	}
}

function changeCategoryCode () {
	var categoryCode = $.trim($("#categoryCode").val());
	if (categoryCode == "") {
		return;
	}
	$.ajax({
		url : "/cdiscount/getCategoryPathByCategoryCode",
		type: 'POST',
		dataType : "json",
		data : {
			categoryCode : categoryCode
		},
		success : function (data) {
			if (data.errorMassage != undefined) {
				var param = {
					status : 0,
					message : data.errorMassage
				};
				$.message.showMessage(param);
				$("#categoryCode").val("");
				$("#navigation").text("");
				$("#categoryName").val("");
			} else {
				var categoryName = data.categoryName;
				var categoryPathList = data.categoryPathList;
				var navigation = "";
				for (var i = categoryPathList.length -1 ; i > -1; i--) {
					if (i == 0) {
						navigation += categoryPathList[i];
					} else {
						navigation += categoryPathList[i] + " > ";
					}
				}
				$("#navigation").text(navigation);
				$("#categoryName").val(categoryName);
			}
		}
	});
	
}

/**
 * 批量上传商品基本信息
 * @param idList
 */
function batchShelvesProduct(idList) {
	if(confirm("确定执行该操作？")){
		$.ajax({
			url : "/cdiscount/batchShelvesProduct",
			type: 'POST',
			dataType : "json",
			data : {
				idList : idList
			},
			success : function (data) {
				$.message.showMessage(data);
				if (data.status == 1){
					refresh(1000);
				}
			}
		});
	}
}

/**
 * 批量上传Offers
 * @param idList
 */
function batchUploadOffers(idList) {
	if(confirm("确定执行该操作？")){
		$.ajax({
			url : "/cdiscount/batchUploadOffers",
			type: 'POST',
			dataType : "json",
			data : {
				idList : idList
			},
			success : function (data) {
				$.message.showMessage(data);
				if (data.status == 1){
					refresh(1000);
				}
			}
		});
	}
}

function batchUpdateToWaitPendding (idList) {
	if(confirm("确定执行该操作？")){
		$.ajax({
			url : "/cdiscount/batchUpdateToWaitPendding",
			type: 'POST',
			dataType : "json",
			data : {
				idList : idList
			},
			success : function (data) {
				$.message.showMessage(data);
				if (data.status == 1){
					refresh(1000);
				}
			}
		});
	}
}

function deleteCdiscountPublish (id) {
	if(confirm("确定删除？")){
		$.ajax({
			url : "/cdiscount/deleteCdiscountPublishById",
			type: 'POST',
			dataType : "json",
			data : {
				id : id
			},
			success : function (data) {
				$.message.showMessage(data);
				if (data.status == 1){
					refresh(1000);
				}
			}
		});
	}
}

///**
// * 批量删除刊登信息
// * @param idList
// */
//function batchDelete(idList) {
//	if(confirm("确定删除？")){
//		$.ajax({
//			url : "/cdiscount/batchDeleteCdiscountPublish",
//			type: 'POST',
//			dataType : "json",
//			data : {
//				idList : idList
//			},
//			success : function (data) {
//				$.message.showMessage(data);
//				refresh(1000);
//			}
//		});
//	}
//}




