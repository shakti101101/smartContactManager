/**
 * 
 */

console.log("hiiiiiiiiiiiiiiiiiiii");

const toggleSidebar =() =>{
	if ($('.sidebar').is(":visible")) {
		//true	
		alert("sidebar");
		$(".sidebar").css("display", "none");
		$(".content").css("margin-left", "0%");
	} else {
		//false
alert("else")
		$(".sidebar").css("display", "block");
		$(".content").css("margin-left", "20%");
	}
};

