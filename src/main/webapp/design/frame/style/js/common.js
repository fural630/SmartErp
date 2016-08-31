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
		if (value != "") {
			setSelectOption($(this), value);
		} else {
			var option = $(this).parent().parent().find("select option");
			option.each(function (){
				if ($(this).text() == "") {
					$(this).attr("selected", true);
				}
				$(this).show();
			});
		}
	});
	
});

function setSelectOption (input, value) {
	var option = $(input).parent().parent().find("select option");
	var re = new RegExp(value, "gi");
	var firstShow = true;
	option.each(function () {
		var text = $(this).text();
		if (re.test(text)) {
			if (firstShow) {
				$(this).attr("selected", true);
				firstShow = false;
			}
		} else {
			if (text != "") {
				$(this).hide();
			}
		}
	});
	
	
}

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
