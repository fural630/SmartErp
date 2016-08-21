﻿function commonHover(obj, cla) {
    obj.hover(function () {
        $(this).addClass(cla);
    }, function () {
        $(this).removeClass(cla);
    });
}

function commonClick(obj, cla) {
    obj.click(function () {
        obj.removeClass(cla);
        $(this).addClass(cla);
    });
}
// expand&collapse
$(document).ready(function(){
   $(".expand").toggle(function(){
    $(this).parent().next(".content").animate({height: 'toggle', opacity: 'toggle'}, "slow");
	$(this).removeClass("expand");
	$(this).addClass("collapse");
   },function(){
    $(this).parent().next(".content").animate({height: 'toggle', opacity: 'toggle'});
	$(this).removeClass("collapse");
	$(this).addClass("expand");
   });
   
   
   var log_notepad = $("img[name=log_notepad]");
   log_notepad.click(function (){
	   var notepadleft = $(this).offset().left;
	   var notepadtop = $(this).offset().top;
	   var log_content = $(this).parent().next();
	   log_content.css({
		   "z-index" : 1,
		   "position": "absolute",
		   "top" : notepadtop + 10,
		   "left" : notepadleft + 50
	   });
	   if (log_content.css("display") == "none") {
		   log_content.show(100);
	   } else {
		   log_content.hide();
	   }
   });
   

	var option_btn = $("div[name=option_btn]");
	$(option_btn).hover(function() {
		var btnleft = $(this).offset().left;
		var btntop = $(this).offset().top;
		var menu_content = $(this).find("div.option_menu");
		menu_content.css({
			"z-index" : 1,
			"position" : "absolute",
			"top" : btntop,
			"left" : btnleft - 43
		});
		menu_content.show(100);
	}, function() {
		var menu_content = $(this).find("div.option_menu");
		menu_content.hide();
	});
	
	var mainPageCheckbox = $("input[name=main_page_checkbox]");
	mainPageCheckbox.click(function() {
		var count = 0;
		mainPageCheckbox.each(function(){
			if($(this).attr("checked")){
				count++;
			}
		});
		$("#pageCheckCount").html(count);
	});
   
});



function showOptionMenu(obj) {
	var btnleft = $(obj).offset().left;
	var btntop = $(obj).offset().top;
	var menu_content = $(obj).next();
	menu_content.css({
		"z-index" : 1,
		"position" : "absolute",
		"top" : btntop,
		"left" : btnleft - 113
	});
	if (menu_content.css("display") == "none") {
		menu_content.css("display", "block");
	} else {
		menu_content.css("display", "none");
	}
}


function queryMainPage() {
	var moduleUrl = $("#moduleUrl").val();
	var pageNo = $("#pageNo").val();
	var pageSize = $("#pageSize").val();
	createFormAndSubmit(moduleUrl, pageNo, pageSize);
}

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
//	alert("pageNo :" + page.pageNo + "; pageSize = " + page.pageSize + "; totalPage = " + page.totalPage + "; totalRecord = " + page.totalRecord);
	if ((page.pageNo-1) <= 0 || (page.pageNo-1) > page.totalPage) {
		return;
	}
//	var moduleUrl = $("#moduleUrl").val();
//	var pageSize = $("#pageSize").val();
//	pageNo = pageNo -1;
//	createFormAndSubmit(moduleUrl, pageNo, pageSize);
}

function nextPage(totalPage) {
	var pageNo = parseInt($("#pageNo").val());
	if (pageNo >= totalPage) {
		return;
	}
	var moduleUrl = $("#moduleUrl").val();
	var pageSize = $("#pageSize").val();
	pageNo = (pageNo + 1);
	createFormAndSubmit(moduleUrl, pageNo, pageSize);
}

function changePageSize() {
	var moduleUrl = $("#moduleUrl").val();
	var pageNo = parseInt($("#pageNo").val());
	var pageSize = $("#pageSize").val();
	createFormAndSubmit(moduleUrl, pageNo, pageSize);
}


function createFormAndSubmit(moduleUrl, pageNo, pageSize) {
	var formId = moduleUrl.replace(/\//g, "_");
	var mainPageForm = document.createElement("form");
	mainPageForm.method = 'post';
	mainPageForm.id = formId;
	mainPageForm.action = moduleUrl;
	
	var pageNoInput = document.createElement("input");
	pageNoInput.setAttribute("name","pageNo");
	pageNoInput.setAttribute("type","hidden");
	pageNoInput.setAttribute("value", pageNo);
	
	var pageSizeInput = document.createElement("input");
	pageSizeInput.setAttribute("name","pageSize");
	pageSizeInput.setAttribute("type","hidden");
	pageSizeInput.setAttribute("value", pageSize);
	
	mainPageForm.appendChild(pageNoInput);
	mainPageForm.appendChild(pageSizeInput);
	
	var queryInput = $("input[name^=params]");
	queryInput.each(function() {
		var paramsElement = document.createElement("input");
		paramsElement.setAttribute("name", $(this).attr("name"));
		paramsElement.setAttribute("type","hidden");
		paramsElement.setAttribute("value", $(this).val());
		mainPageForm.appendChild(paramsElement);
	});
	
	document.body.appendChild(mainPageForm);
	mainPageForm.submit();
}

function createSearchCondition() {
}

function pageSelectAll() {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	var count = 0;
	mainPageCheckBox.each(function(){
		$(this).attr("checked", true);
		count++;
	});
	$("#pageCheckCount").html(count);
}

function pageNoSelectAll() {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	mainPageCheckBox.each(function(){
		$(this).attr("checked", false);
	});
	$("#pageCheckCount").html(0);
}

function pageUnselected() {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	var count = 0;
	mainPageCheckBox.each(function(){
		if (!this.checked) {
			count++;
		}
		$(this).attr("checked", !this.checked); 
	});
	$("#pageCheckCount").html(count);
}




