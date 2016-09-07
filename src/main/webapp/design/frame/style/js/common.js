$(function () {
	$(".select_filter").focus(function() {
		var value = $.trim($(this).val());
		if (value == "输入过滤"){
			$(this).val("");
			$(this).removeClass("main_input_search");
			$(this).addClass("input_normal");
		}
	}).blur(function(){
		var value = $.trim($(this).val());
		if (value == ""){
			$(this).val("输入过滤");
			$(this).removeClass("input_normal");
			$(this).addClass("main_input_search");
			var option = $(this).parent().parent().find("select option");
			option.each(function(e){
				$(this).show();
			});
		}
	}).bind("input propertychange", function() {
		var value = $.trim($(this).val());
		var option = $(this).parent().parent().find("select option");
		if (value != "") {
			var re = new RegExp(value, "gi");
			var firstShow = true;
			var count = 0;
			option.each(function () {
				var text = $(this).text();
				if (re.test(text)) {
					$(this).show();
					if (firstShow) {
						$(this).attr("selected", true);
						firstShow = false;
					}
				} else {
					count++;
//					$(this).removeAttr("selected");
					$(this).hide();
//					if ($(this).text() == "") {
//						$(this).attr("selected", true);
//						$(this).show();
//					} else {
//						$(this).hide();
//					}
				}
			});
			if (count == option.size()) {
				option.each(function () {
					$(this).removeAttr("selected");
				});
			}
		} else {
			option.each(function () {
				if ($(this).text() == "") {
					$(this).attr("selected", true);
				} else {
					$(this).removeAttr("selected");
				}
				$(this).show();
			});
		}
	});
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
			location.replace(location.href);
		}, time);
	} else {
		location.replace(location.href);
	}
}
