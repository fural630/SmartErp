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
});

function queryMainPage() {
	var moduleUrl = $("#moduleUrl").val();
	var modulePagePageNo = $("#modulePagePageNo").val();
	var modulePagePageSize = $("#modulePagePageSize").val();
	
	alert(moduleUrl + " ; " + modulePagePageNo + " ; " + modulePagePageSize);
	
}


