$(function() {
	$("#datepicker").datepicker({
		showOn : "button",
		buttonImage : "/design/static/images/common/calendar_16px.png",
		buttonImageOnly : true,
		buttonText : "Select date"
	});
});


function getPageParam() {
	var page = new Object();
	page.pageNo = parseInt($("#pageNo").val());
	page.pageSize = parseInt($("#pageSize").val());
	page.totalPage = parseInt($("#totalPage").html());
	page.totalRecord = parseInt($("#totalRecord").html());
	return page;
}

function previousPage () {
	var page = getPageParam();
	if ((page.pageNo-1) <= 0 || (page.pageNo-1) > page.totalPage) {
		return;
	}
	page.pageNo = page.pageNo - 1;
	getPageCollection(page);
}

function nextPage() {
	var page = getPageParam();
	if (page.pageNo >= page.totalPage) {
		return;
	}
	page.pageNo = page.pageNo + 1;
	getPageCollection(page);
}

function changePageSize() {
	var page = getPageParam();
	getPageCollection(page);
}

function queryMainPage() {
	var page = getPageParam();
	var params = createSearchCondition();
	getPageCollection(page, params);
}

function createSearchCondition() {
	var condition = $(".conditionTr");
	var inputText = condition.find("input[type=text]:not(.select_filter)");
	var params = {};
	inputText.each(function () {
		var key = $(this).attr("name");
		var value = $(this).val();
		params[key] = value;
	});
	return params;
}

function getPageCollection(page, params) {
	var url = $("#moduleAjaxTableUrl").val();
	$.ajax({
		url : url,
		type: 'POST',
		dataType : "json",
		async: false,
		data : {
			pageNo : page.pageNo,
			pageSize : page.pageSize,
			params : params
		},
		success : function (data) {
			var page = data.page;
			updateMainPage(page);
			updatePageCollection(data.userList);
			initCssStyle();
			countCheckbox();
		}
	});
}

function updatePageCollection(data) {
	var appendNode = $("#userManageTable tr:gt(1)");
	appendNode.remove();
	var collection = "";
	$.each(data, function () {
		var tableData = "<tr>";
		tableData += "<td><input name='main_page_checkbox' type='checkbox' value='" + this.id + "' onclick='countCheckbox()' /></td>";
		tableData += "<td>" + this.id + "</td>";
		tableData += "<td>" + this.name +"</td>";
		tableData += "<td>" + this.username + "</td>";
		tableData += "<td></td>";
		tableData += "<td></td>";
		tableData += "<td>" + this.phone + "</td>";
		tableData += "<td><a href='javascript:void(0)' onclick='showLog(this)'><img src='/design/static/images/common/system-log.png'/></a>";
		tableData += "<div class='log_content'>";
		tableData += "【1、于2016-08-20 00:24 由超级管理员创建信息】<br/>";
		tableData += "</div>";
		tableData += "</td>";
		tableData += "<td style='width:60px;'>";
		tableData += "<ul>";
		tableData += "<li class='option_btn' onmouseover='optionMouserover(this)' onmouseout='optionMouseout(this)'><a class='btn' href='javascript:void(0)'>操作</a>";
		tableData += "<ul class='menu_ul'>";
		tableData += "<li><a href='javascript:void(0)' onclick='' >编辑 </a></li>";
		tableData += "<li><a href='javascript:void(0)' onclick='' >删除 </a></li>";
		tableData += "</ul>";
		tableData += "</li>";
		tableData += "</ul>";
		tableData += "</div>";
		tableData += "</td>";
		tableData += "</tr>";
		collection += tableData;
	});
	$("#userManageTable").append(collection);
}

function updateMainPage(page) {
	$("#pageNo").val(page.pageNo);
	$("#pageSize").val(page.pageSize);
	$("#totalPage").html(page.totalPage);
	$("#totalRecord").html(page.totalRecord);
	$("#txtPageNo").html(page.pageNo);
}

