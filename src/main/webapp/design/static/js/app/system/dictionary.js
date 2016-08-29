$(function () {
	$(function() {
		$('body').layout({ 
			applyDefaultStyles: true,
			west__size:400 
		 });
	});
	initDictionaryTree();
	initDialog();
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
		$("#dictionaryTypeDialog input[name=parentId]").val(id);
		$("#dictionaryTypeDialog").dialog("option", "title", "新增字典模块");
		$("#dictionaryTypeDialog").dialog('open');
		
	} else {
		alert("请选中一个节点！");
	}
}

function addRootDictionaryType() {
	$("#dictionaryTypeDialog input[name=parentId]").val(0);
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
		type: 'POST',
		dataType : "json",
		data : {
			id : id
		},
		success : function (data) {
			$("#dictionaryTypeDialog input[name=parentId]").val(id);
			$("#dictionaryTypeDialog input[name=moduleName]").val(data.name);
			$("#dictionaryTypeDialog").dialog("option", "title", "修改节点");
			$("#dictionaryTypeDialog").dialog("open");
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


