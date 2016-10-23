//When the DOM is ready, run this function
$(document).ready(function() {
	//Set the carousel options
	$('#quote-carousel').carousel({
		pauseOnHover: true,
		interval: 1700,
	});
	
	$('#quote-carousel .left, #quote-carousel .right').css("cursor", "pointer");
});
