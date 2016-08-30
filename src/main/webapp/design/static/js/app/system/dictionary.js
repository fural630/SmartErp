$(function () {
	$('body').layout({ 
		applyDefaultStyles: true,
		west__size:400 
	});
	initDictionaryTree();
	initDialog();
	
	$.blockUI.defaults.overlayCSS.opacity=0.2;
	$.ajaxSetup({
		error: function (xhr, status, e) {
			var param = {
				status : 0,
				message : e
			};
			$.message.showMessage(param);
		}
	});

});

function initDialog() {
	$("#dictionaryTypeDialog").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		height: 400,
		resizable: false,
		buttons : [ {
			text : "保存",
			icons : {
				primary : "ui-icon-heart"
			},
			click : function() {
				saveDictionaryType();
				$.myformPlugins.cleanForm("#dictionaryTypeDialog");
				$(this).dialog("close");
			}
		} ],
		close: function( event, ui ) {
			$.myformPlugins.cleanForm("#dictionaryTypeDialog");
		}
	});
}

function saveDictionaryType () {
	var url =$("#dictionaryTypeDialog input[name=action]").val();
	var id = $("#dictionaryTypeDialog input[name=id]").val();
	var moduleName = $("#dictionaryTypeDialog input[name=moduleName]").val();
	
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		data : {
			id : id,
			moduleName : moduleName
		},
		success : function (data) {
			var status = $.message.showMessage(data);
			if (status == 1) {
				initDictionaryTree();
			}
		},
		beforeSend : function (xhr) {
			$.blockUI({
				message: '<img src="/design/static/images/common/progressbar10.gif">',
				timeout: 10000,
				css:{
					backgroundColor: "",
					border:"0"
				}
			});
		}
	});
	
}

function initDictionaryTree() {
	var setting = {
		data: {
			simpleData: {
				enable: true
			},
		},
		callback : {
			onClick : loadDictionary
		},
		async: {
			enable: true,
			url:"/system/getAllDictionaryTree",
			dataType : "json",
			autoParam:["id=id"],
			type: "post",
		}
	};
	
	$.fn.zTree.init($("#dictionaryTree"), setting);
}

function loadDictionary() {
//	var treeObj = $.fn.zTree.getZTreeObj("tree");
//	var sNodes = treeObj.getSelectedNodes();
//	if (sNodes.length > 0) {
//		var isParent = sNodes[0].isParent;
//	}
	alert(444);
}

function addDictionaryType() {
	var id = getTreeSelectedNodeId();
	if (id == undefined) {
		alert("请选中一个节点！");
		return;
	}
	var action = "/system/addDictionaryType";
	$("#dictionaryTypeDialog input[name=action]").val(action);
	$("#dictionaryTypeDialog input[name=id]").val(id);
	$("#dictionaryTypeDialog").dialog("option", "title", "新增字典模块");
	$("#dictionaryTypeDialog").dialog('open');
		
}

function addRootDictionaryType() {
	var action = "/system/addRootDictionaryType";
	$("#dictionaryTypeDialog input[name=action]").val(action);
	$("#dictionaryTypeDialog input[name=id]").val(0);
	$("#dictionaryTypeDialog").dialog("option", "title", "新增根节点");
	$("#dictionaryTypeDialog").dialog("open");
}

function editDictionaryType() {
	var id = getTreeSelectedNodeId();
	if (id == undefined) {
		alert("请选中一个节点！");
		return;
	}
	$.ajax({
		url : "/system/getDictionaryById",
		type: 'POST',
		dataType : "json",
		data : {
			id : id
		},
		success : function (data) {
			var action = "/system/editDictionaryType";
			$("#dictionaryTypeDialog input[name=action]").val(action);
			$("#dictionaryTypeDialog input[name=id]").val(id);
			$("#dictionaryTypeDialog input[name=moduleName]").val(data.name);
			$("#dictionaryTypeDialog").dialog("option", "title", "修改节点");
			$("#dictionaryTypeDialog").dialog("open");
		}
	});
	
}

function deleteDictionaryType() {
	$.ajax({
		url : "/system/deleteDictionaryType",
		success : function (data) {
			var status = $.message.showMessage(data);
		}
	});
	
	
}

function getTreeSelectedNodeId () {
	var id;
	var treeObj = $.fn.zTree.getZTreeObj("dictionaryTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length > 0) {
		id = nodes[0].id;
	}
	return id;
}


