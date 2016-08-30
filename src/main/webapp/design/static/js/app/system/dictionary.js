$(function () {
	$('body').layout({ 
		applyDefaultStyles: true,
		west__size:400 
	});
	initDictionaryTree();
	initDialog();
	
	$.blockUI.defaults.overlayCSS.opacity=0.2;
	$.ajaxSetup({
		type: 'POST',
		dataType : "json",
		beforeSend : function (xhr) {
			$.blockUI({
				message: '<img src="/design/static/images/common/progressbar10.gif">',
				timeout: 10000,
				css:{
					backgroundColor: "",
					border:"0"
				}
			});
		},
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
				$(this).dialog("close");
			}
		} ],
		close: function( event, ui ) {
			$(':input','#dictionaryTypeDialog')  
			.not(':button, :submit, :reset, :hidden')  
			.val('')  
			.removeAttr('checked')  
			.removeAttr('selected');  
		}, 
		open: function( event, ui ) {
		}
	});
}

function saveDictionaryType () {
	var url =$("#dictionaryTypeDialog input[name=action]").val();
	var id = $("#dictionaryTypeDialog input[name=id]").val();
	alert("url = " + url + "; id = " + id);
	
	$.ajax({
		url : url,
		data : {
			id : id
		},
		success : function (data) {
			alert(data.success);
		}
		
	});
	
}

function initDictionaryTree() {
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:"/system/getDictionaryAll",
				dataType : "json",
				autoParam:["id=id"],
				type: "post",
			}
		};
	
	$.fn.zTree.init($("#dictionaryTree"), setting);
}

function addDictionaryType() {
	var treeObj = $.fn.zTree.getZTreeObj("dictionaryTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length > 0) {
		var id = nodes[0].id;
		var action = "/system/addDictionaryType";
		$("#dictionaryTypeDialog input[name=action]").val(action);
		$("#dictionaryTypeDialog input[name=id]").val(id);
		$("#dictionaryTypeDialog").dialog("option", "title", "新增字典模块");
		$("#dictionaryTypeDialog").dialog('open');
		
	} else {
		alert("请选中一个节点！");
	}
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
	}
	$.ajax({
		url : "/system/getDictionaryById",
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


