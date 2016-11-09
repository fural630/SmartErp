//jQuery选择器写法（选择table的行，隔一行，选择一行） 

function initCssStyle() {
	$('.stripe tr:nth-child(even)').not(".normal").addClass("trOdd");
	$('.stripe tr:eq(1)').not(".normal").addClass("selected");
	
	$(".stripe tr:gt(1)").not(".normal").mouseover(function() {
		//如果鼠标移到class为stripe的表格的tr上时，执行函数   
		$(this).addClass("over");
	}).mouseout(function() {
		//给这行添加class值为over，并且当鼠标一出该行时执行函数   
		$(this).removeClass("over");
	}); //移除该行的class  	
	
	//选中表格的行
//	$('.stripe tr:gt(1)').click(function() {
//		$(this).find('input[type="radio"]').attr('checked', 'checked');
//		if ($(this).hasClass('selected')) {
//			$(this).removeClass('selected');
//			$(this).find('input[type="checkbox"]').removeAttr('checked');
//
//		} else {
//			$(this).addClass('selected');
//			$(this).find('input[type="checkbox"]').attr('checked', 'checked');
//
//		}
//	});
}

$(function() {
	initCssStyle();
});
