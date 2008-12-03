$(document).ready(function() {
	$("#login-form").submit(function(event) {
		$("#login").fadeOut("slow", function() {
			$("<div id='login'>Welcome</div>").hide().appendTo("#menu").fadeIn("slow");
		});
		return false;
	});
});