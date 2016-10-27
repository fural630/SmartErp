$(function () {
	$.blockUI.defaults.overlayCSS.opacity=0.2;
	$.ajaxSetup({
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
	copyFreezeTable();
});

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

function previousPage () {
	var pageNo = parseInt($("#pageNo").val());
	var previousPageNo = pageNo - 1;
	if (previousPageNo <= 0) {
		return;
	}
	$("#pageNo").val(previousPageNo);
	submitMainPageForm();
}

function nextPage () {
	var pageNo = parseInt($("#pageNo").val());
	var nextPageNo = pageNo + 1;
	var totalPage = parseInt($("#totalPage").text());
	if (nextPageNo > totalPage) {
		return;
	}
	$("#pageNo").val(nextPageNo);
	submitMainPageForm();
}

function changePageSize() {
	submitMainPageForm();
}

function queryMainPage () {
	submitMainPageForm();
}

function submitMainPageForm() {
	var condition = $(".conditionTr");
	var inputLike = condition.find("input[name$='Like]']");
	inputLike.each(function () {
		if ($(this).is(":checked")) {
			var name = $(this).attr("name");
			var inputName = name.split("Like]")[0];
			inputName += "]";
			var queryValue = condition.find("input[name='" + inputName + "']").val();
			$(this).val(queryValue);
		}
	});
	$("#mainPageForm").submit();
}

function resetAll() {
	location.replace(location.href);
}

function countCheckbox() {
	var mainPageCheckbox = $("input[name=main_page_checkbox]");
	var count = 0;
	mainPageCheckbox.each(function() {
		if ($(this).attr("checked")) {
			count++;
		}
	});
	$("#pageCheckCount").html(count);
}

function pageSelectAll() {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	mainPageCheckBox.each(function(){
		$(this).attr("checked", true);
	});
	countCheckbox();
}

function pageNoSelectAll() {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	mainPageCheckBox.each(function(){
		$(this).attr("checked", false);
	});
	countCheckbox();
}

function pageUnselected() {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	mainPageCheckBox.each(function(){
		$(this).attr("checked", !this.checked); 
	});
	countCheckbox();
}

function showLog(obj) {
	var notepadleft = $(obj).offset().left;
	var notepadtop = $(obj).offset().top;
	var log_content = $(obj).next();
	var bodyWidth = $("body").width();
	log_content.css({
		"z-index" : 1,
		"position" : "absolute",
		"left" : bodyWidth - log_content.width() - 40
	});
	log_content.toggle();
}

function optionMouserover(obj) {
	$(obj).find(".menu_ul").show();
}

function optionMouseout(obj) {
	$(obj).find(".menu_ul").hide();
}

function refresh(time) {
	if (time != undefined) {
		setTimeout(function () {
			queryMainPage();
		}, time);
	} else {
		queryMainPage();
	}
}

function refreshSearch(time) {
	if (time != undefined) {
		setTimeout(function () {
			queryMainPage();
		}, time);
	} else {
		queryMainPage();
	}
}

function inputNumOnly (e) {
	e.value = e.value.replace(/[^\d.]/g,'');
}

function getBatchOptionIds () {
	var mainPageCheckBox = $("input[name=main_page_checkbox]");
	var ids = [];
	mainPageCheckBox.each(function(){
		if ($(this).attr("checked")) {
			ids.push($(this).val());
		}
	});
	return ids;
}

function copyFreezeTable () {
	if ($("table[name='freezeTable']").length > 0) {
		return;
	}
	var pageTable = $("table[name='pageTable']");
	if (pageTable.length > 0) {
		var freezeTable = pageTable.clone();
		freezeTable.removeAttr("name");
		freezeTable.removeAttr("class");
		freezeTable.removeAttr("id");
		freezeTable.attr("name", "freezeTable");
		freezeTable.addClass("tb_border");
		freezeTable.addClass("intro-tab-fix");
		freezeTable.html("<tr></tr>");
		freezeTable.hide();
		freezeTable.width(pageTable.width());
		pageTable.find("tr:first th").each(function () {
			var pageTableTh = $(this).clone();
			pageTableTh.removeAttr("width");
			pageTableTh.width($(this).width() + 1);
			console.log($(this).width() + 1);
			freezeTable.append(pageTableTh);
		});
		$(".content").append(freezeTable);
	}
}

$(window).scroll(function () {
	if($(window).scrollTop() > 110) {
		$("table[name='freezeTable']").show();
	} else {
		$("table[name='freezeTable']").hide();
	}
});

