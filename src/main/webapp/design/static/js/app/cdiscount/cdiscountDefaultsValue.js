$(function () {
	initDialog();
});

function initDialog () {
	$("#cdiscountDefaultsValueDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 800,
		height: 600,
		resizable: false,
		buttons : [{
				text : "保存",
				icons : {
					primary : "ui-icon-heart"
				},
				click : function() {
					if (validate()) {
						saveCdiscountDefaultsValue();
					}
				}
			}
		],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#cdiscountDefaultsValueDialog");
		}
	});
}

function showDefaultsValueDialog (title) {
	$("#cdiscountDefaultsValueDialog").dialog("option", "title", title);
	$("#cdiscountDefaultsValueDialog").dialog("open");
}

function validate () {
	return $("#cdiscountDefaultsValueDialogForm").valid();
}

function saveCdiscountDefaultsValue () {
	var dialog = $("#cdiscountDefaultsValueDialog");
	var id = $.trim(dialog.find("input[name='id']").val());
	var templateName = $.trim(dialog.find("input[name='templateName']").val());
	var brandName = $.trim(dialog.find("input[name='brandName']").val());
	var quantity = $.trim(dialog.find("input[name='quantity']").val());
	var dea = $.trim(dialog.find("input[name='dea']").val());
	var vat = $.trim(dialog.find("input[name='vat']").val());
	var ecoPart = $.trim(dialog.find("input[name='ecoPart']").val());
	var stockingTime = $.trim(dialog.find("input[name='stockingTime']").val());
	
	var deliveryModeTr = $(".deliveryModeTr");
	var defaultsValueDeliveryModeList = new Array();  
	deliveryModeTr.each(function () {
		var deliveryMode = $(this).find("input[name='deliveryMode']").val();
		var shippingCharges = $.trim($(this).find("input[name='shippingCharges']").val());
		var addShippingCharges = $.trim($(this).find("input[name='addShippingCharges']").val());
		if (shippingCharges != "" && addShippingCharges != "") {
			defaultsValueDeliveryModeList.push({
				deliveryMode: deliveryMode,
				shippingCharges: shippingCharges,
				addShippingCharges : addShippingCharges
			}); 
		}
	});
	

	$.ajax({
		url : "/cdiscount/saveCdiscountDefaultsValue",
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
			templateName : templateName,
			brandName : brandName,
			quantity : quantity,
			dea : dea,
			vat : vat,
			ecoPart : ecoPart,
			stockingTime : stockingTime,
			defaultsValueDeliveryModeList : JSON.stringify(defaultsValueDeliveryModeList)
		},
		success : function (data) {
			$.message.showMessage(data);
			if (data.status == 1) {
				$("#cdiscountDefaultsValueDialog").dialog("close");
				refresh(1000);
			}
		}
	});
}

function editCdiscountDefaultsValue (id) {
	$.ajax({
		url : "/cdiscount/getCdiscountDefaultsValueById",
		type: 'POST',
		dataType : "json",
		data : {
			id : id
		},
		success : function (data) {
			$.unblockUI();
			if (null != data) {
				var e = data.cdiscountDefaultsValue;
				var dialog = $("#cdiscountDefaultsValueDialog");
				dialog.find("input[name='id']").val(e.id);
				dialog.find("input[name='templateName']").val(e.templateName);
				dialog.find("input[name='brandName']").val(e.brandName);
				dialog.find("input[name='quantity']").val(e.quantity);
				dialog.find("input[name='dea']").val(e.dea);
				dialog.find("input[name='vat']").val(e.vat);
				dialog.find("input[name='ecoPart']").val(e.ecoPart);
				dialog.find("input[name='stockingTime']").val(e.stockingTime);
				var defaultsDeliveryModeList = data.defaultsDeliveryModeList;
				fillingDeliveryModeData(defaultsDeliveryModeList);
				showDefaultsValueDialog("修改模板");
			} else {
				var param = {
					status : -1
				};
				$.message.showMessage(param);
			}
		}
	});
}

function fillingDeliveryModeData (defaultsDeliveryModeList) {
	var deliveryModeTr = $("#cdiscountDefaultsValueDialog").find(".deliveryModeTr");
	if (defaultsDeliveryModeList.length > 0 ) {
		$.each(defaultsDeliveryModeList, function (i, obj) {
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

function deleteCdiscountDefaultsValue (id) {
	if(confirm("确定删除？")){
		$.ajax({
			url : "/cdiscount/deleteCdiscountDefaultsValue",
			type: 'POST',
			dataType : "json",
			data : {
				id : id
			},
			success : function (data) {
				$.message.showMessage(data);
				refresh(1000);
			}
		});
	}
}

function setAsDefaultsTemplate (id) {
	if(confirm("确定将此模板设置为默认模板吗，其他模板将被设置成非默认模板？")){
		$.ajax({
			url : "/cdiscount/setAsDefaultsTemplate",
			type: 'POST',
			dataType : "json",
			data : {
				id : id
			},
			success : function (data) {
				$.message.showMessage(data);
				refresh(1000);
			}
		});
	}
}

