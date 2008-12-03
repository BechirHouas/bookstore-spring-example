$(document).ready(function() {
	$("#login-button").click(function(event) {
		$("#login").fadeOut("slow", function() {
			$("<div id='login'>Welcome</div>").hide().appendTo("#menu").fadeIn("slow");
		});
	});
});