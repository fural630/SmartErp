function commonHover(obj, cla) {
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
//	   var notepadleft = $(this).offset().left;
//	   var notepadtop = $(this).offset().top;
//	   var log_content = $(this).parent().next();
//	   var left = 35;
//	   var top = 15;
//	   log_content.offset({top:(notepadtop + top),left:(notepadleft + left)});
	   var log_content = $(this).parent().next();
	   if (log_content.is(":hidden")){
		   log_content.show(100);
	   } else {
		   log_content.hide(100);
	   }

   });
   
   
});

function queryMainPage() {
	var moduleUrl = $("#moduleUrl").val();
	var pageNo = $("#pageNo").val();
	var pageSize = $("#pageSize").val();
	createFormAndSubmit(moduleUrl, pageNo, pageSize);
}

function previousPage (totalPage) {
	var pageNo = parseInt($("#pageNo").val());
	if ((pageNo-1) <= 0 || (pageNo-1) > totalPage) {
		return;
	}
	var moduleUrl = $("#moduleUrl").val();
	var pageSize = $("#pageSize").val();
	pageNo = pageNo -1;
	createFormAndSubmit(moduleUrl, pageNo, pageSize);
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


function resetAll() {
	$("input[name='params.phone']").jqPage({
		url : "",
		method : "",
		colNames : ["姓名","邮箱","电话"],
		widths :[90, 200, 50],
		
		
	});
}


