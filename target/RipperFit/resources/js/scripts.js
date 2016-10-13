jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
	$.backstretch("resources/img/header-bg.jpg");
    $('form').backstretch("resources/img/header-bg.jpg");
    
    
    /*
        Login form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
    /*
        Registration form validation
    */
    $('.registration-form input[type="text"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.registration-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"],input[type="password"],input[type="number"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
			
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    });
});

$(document).ready(function(){
	$('input[type="text"], input[type="password"], textarea').each(function() {
		$(this).val( $(this).attr('placeholder') );
    });	
});