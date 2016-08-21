(function($) {
	// 插件的定义
	$.fn.jqPage = function(options) {
		// build main options before element iteration
		var opts = $.extend({}, $.fn.jqPage.defaults, options);
		$this = $(this);
		
		
		
		var jqPageHtml = "";
		jqPageHtml += spliceTitle(opts);
		jqPageHtml += splicePage(opts);
		jqPageHtml += spliceTable(opts);
		
		
		$this.html(jqPageHtml);
		
//		jqPageAjax(opts);
	};
	
	function jqPageAjax(opts) {
		$.ajax({
			url : "/system/demoAjax",
			method : "post",
			dataType : "json",
			async: false,
			data : {
				pageNo : 1,
				pageSize : 10,
				params : [{
					name : "username",
					queryType : "eq",
					value : "zhang"
				}]
			},
			success : function (data) {
				
			}
		});
	}
	
	function spliceTable(opts) {
		var table = "";
		table += "<div class='mainbody clearfix'>";
		table += 	"<div class='tableview clearfix'>";
		table += 		"<div class='content'>";
		table += 			"<table class='tb_border tb_full stripe'>";
		
		table += "<tr>";
		if (opts.showBatchOption) {
			table += "<th></th>";
		}
		$.each(opts.colNames, function() {
			table += "<th>" + this + "</th>";
		});
		table += "</tr>";
		
		table += "<tr>";
		if (opts.showBatchOption) {
			table += "<td></td>";
		}
		$.each(opts.searchType, function() {
			var searchTd = "<td>";
			if (this == "range") {
				searchTd += "<label>从：</label><input type='text' class='txt width_20px params_margin_5px' name='params.name' /><br/>";
				searchTd += "<label>到：</label><input type='text' class='txt width_20px params_margin_5px' name='params.name' /><br/>";
			} else if (this == "eqlike") {
				searchTd += "<input type='text' class='txt width_80px params_margin_5px' name='params.name' />";
				searchTd += "<input type='checkbox'> * ";
			}
			searchTd += "</td>";
			table += searchTd;
		});
		table += "</tr>";
		
		table += 			"</table>";
		table += 		"</div>";
		table += 	"</div>";
		table += "</div>";
		return table;
	}
	
	
	function spliceTitle(opts) {
		var html = "";
		html +=	"<input type='hidden' id='moduleUrl' value=''/>";
		html += "<div class='current_nav_name clearfix'>标题";
//		if (undefined != data.title) {
//			html +=  data.title;
//		}
		html += "<div class='fr small_size'>";
		$.each(opts.optionBtn, function() {
			html += "<a class='btn' onclick='" + this.clickEvent + "()'><img src='" + this.btnImage + "'/>" + this.btnName + "</a>&nbsp;";
		});
		html += "</div>";
		html += "</div>";
		return html;
		
	};
	
	function splicePage(opts) {
		var html = "";
		html += "<div class='paging clearfix'>";
		html += 	"<div class='paging_inner'>";
		html += 		"<div class='fl page_box'>";
		html += 			"<a class='pging_btn' onclick='previousPage(" +' page.totalPage' + ")'>上一页</a> &nbsp;&nbsp;";
		html += 			"第&nbsp;<input class='txt width_20px ta_c' value='{page.pageNo}' id='pageNo'/>&nbsp;页 &nbsp; ";
		html += 			"<a class='pging_btn' onclick='nextPage({page.totalPage})'>下一页 </a>";
		html += 			"&nbsp;共&nbsp;${page.totalRecord}&nbsp;条， ${page.pageNo}/${page.totalPage} 页， 每页 ";
		html += 			"<select class='sel' id='pageSize' onchange='changePageSize()'>";
			$.each(opts.pageSize, function(index, value) {
				html += "<option value='" + value + "' >" + value + "</option>";
			});
		html += 			"</select>&nbsp;条";
		html += 		"</div>";
		html += 		"<div class='fr'>";
		html += 			"<a class='btn' onclick='resetAll()'>重置</a> <a class='btn' onclick='queryMainPage()'><img src='/design/frame/style/img/query.png' />查询</a>";
		html += 		"</div>";
		html += 	"</div>";
		html += "</div>";
		return html;
	}
	
	// 定义暴露format函数
	$.fn.jqPage.format = function(txt) {
		return '<strong>' + txt + '</strong>';
	};
	// 插件的defaults
	$.fn.jqPage.defaults = {
		url : "",
		method : "post",
		data :{},
		optionBtn : [ {
			btnName : "新增",
			clickEvent : "",
			btnImage : "/design/frame/style/img/add.png"
		}],
		pageSize : [10, 20, 30],
		colNames : ["ID","姓名","账号","性别","级别","电话","日志","操作"],
		searchType : ["range", "eqlike", "eq", "select", "", "dateTime", "", ""],
		showBatchOption : true
		
	};
	// 闭包结束
})(jQuery);
