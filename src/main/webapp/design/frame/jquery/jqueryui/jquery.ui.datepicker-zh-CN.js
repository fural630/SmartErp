jQuery(function($){  
    $.datepicker.regional['zh-CN'] = {
		showOn : "button",
		buttonImage : "/design/static/images/common/calendar_16px.png",
		buttonImageOnly : true,
		buttonText : "日期选择",
		changeMonth : true,
		changeYear : true,
        closeText: '关闭',  
        prevText: '上一月',  
        nextText: '下一月',  
        currentText: '今天',  
        monthNames: ['一月','二月','三月','四月','五月','六月',  
        '七月','八月','九月','十月','十一月','十二月'],  
        monthNamesShort: ['一月','二月','三月','四月','五月','六月',  
        '七月','八月','九月','十月','十一月','十二月'], 
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
        dayNamesMin: ['日','一','二','三','四','五','六'],  
        weekHeader: '周',  
        dateFormat: 'yy-mm-dd 00:00:00',  
        showMonthAfterYear: true,  
        yearSuffix: '年'
    };  
    
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
    $(".datepicker").datepicker();
});