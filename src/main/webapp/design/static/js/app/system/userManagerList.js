$(function() {
	var mainPageTable = $("#userManagerTable");
	mainPageTable.jqPage({
		
		
	});
	
	$( "#datepicker" ).datepicker({
      showOn: "button",
      buttonImage: "/design/static/images/common/calendar_16px.png",
      buttonImageOnly: true,
      buttonText: "Select date",
      changeMonth: true,
      changeYear: true,
      dateFormat:"yy-mm-dd" 
    });
});