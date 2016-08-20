(function($) {
	// 插件的定义
	$.fn.jqPage = function(options) {
		// build main options before element iteration
		var opts = $.extend({}, $.fn.jqPage.defaults, options);
		$this = $(this);
//		var test = opts.showAddBtn;
//		$.each(test, function() {
//			alert(this.btnName);
//		});
		
		
		$this.html(init(opts));
	};
	
	
	function init(opts) {
		var html = "";
		html +=	"<input type='hidden' id='moduleUrl' value=''/>";
		html += "<div class='current_nav_name clearfix'>{title}";
		html += "<div class='fr small_size'>";
		$.each(opts.optionBtn, function() {
			html += "<a class='btn' onclick='" + this.clickEvent + "()'><img src='" + this.btnImage + "'/>" + this.btnName + "</a>&nbsp;";
		});
		html += "</div>";
		html += "</div>";
		return html;
		
	};
	
	// 定义暴露format函数
	$.fn.jqPage.format = function(txt) {
		return '<strong>' + txt + '</strong>';
	};
	// 插件的defaults
	$.fn.jqPage.defaults = {
		optionBtn : [ {
			btnName : "新增",
			clickEvent : "",
			btnImage : "/design/frame/style/img/add.png"
		}],
		background : 'yellow'
	};
	// 闭包结束
})(jQuery);
