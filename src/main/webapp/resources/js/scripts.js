jQuery(document).ready(function() {
	//upload profile picture
	$('#i_file').change( function(event) {
		$("#image1").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));
	});
});	

function start() {
	gapi
	.load(
			'auth2',
			function() {
				auth2 = gapi.auth2
				.init({
					client_id : '412974983923-jg7l8j84308pughfqt5o8q0gc0pslh83.apps.googleusercontent.com',
					// Scopes to request in addition to 'profile' and 'email'
					//scope: 'additional_scope'
				});
			});
}
function validateEmail() {
	var email=document.getElementById("form-email").value;
	var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
	if (pattern.test(email)==false) {
		$(".email").text('Please enter a valid e-mail address.');
		$("#form-email").removeClass('valid');
		$("#form-email").addClass('invalid');
	} else {
		$(".email").text('');
		$("#form-email").removeClass('invalid');
		$("#form-email").addClass('valid');
	}
}
function validateFname() {
	var pattern = new RegExp(/^[+a-zA-Z]/g);
	var Fname=document.getElementById("form-first-name").value;
	if(pattern.test(Fname)==false) {
		$(".Fname").text('Please enter a valid name');
		$("#form-first-name").removeClass('valid');
		$("#form-first-name").addClass('invalid');
	} else if(Fname.length<2) {
		$(".Fname").text('last name must have atleast 2 characters');
		$("#form-first-name").removeClass('valid');
		$("#form-first-name").addClass('invalid');
	} else {
		$(".Fname").text('');
		$("#form-first-name").removeClass('invalid');
		$("#form-first-name").addClass('valid');
	}
}
function validateLname() {
	var pattern = new RegExp(/^[+a-zA-Z]/g);
	var Lname=document.getElementById("form-last-name").value;
	if(pattern.test(Lname)==false) {
		$(".Lname").text('Please enter a valid name');
		$("#form-last-name").removeClass('valid');
		$("#form-last-name").addClass('invalid');
	} else if(Lname.length<2) {
		$(".Lname").text('last name must have atleast 2 characters');
		$("#form-last-name").removeClass('valid');
		$("#form-last-name").addClass('invalid');
	} else {
		$(".Lname").text('');
		$("#form-last-name").removeClass('invalid');
		$("#form-last-name").addClass('valid');
	}
}
function validateGender() {
	var gender=document.getElementById("form-gender").value;
	if (gender == "") {
		$(".gender").text('Please select gender');
		$("#form-gender").removeClass('valid');
		$("#form-gender").addClass('invalid');
	} else {
		$(".gender").text('');
		$("#form-gender").removeClass('invalid');
		$("#form-gender").addClass('valid');
	}   
}
function validateContact() {	
	var pattern = new RegExp(/^[+0-9]/g);
	var contact=document.getElementById("form-contact").value;
	if(contact.length!=10) {
		$(".contact").text('Please enter valid contact 10 digit number');
		$("#form-contact").addClass('invalid');
		$("#form-gender").removeClass('valid');
	} else {
		$(".contact").text('');
		$("#form-contact").removeClass('invalid');
		$("#form-gender").addClass('valid');
	}   
}
function validatePassword() {
	var password=document.getElementById("form-password").value;
	if(password.length<6) {
		$(".password").text('Weak Password. Password must be atleast 6 digits long');
		$("#form-password").removeClass('valid');  
		$("#form-password").addClass('invalid');  
	} else {
		$(".password").text('');
		$("#form-password").removeClass('invalid');  
		$("#form-password").addClass('valid');
	}
}
function confirmPassword() {
	var password=document.getElementById("form-password").value;
	var confirmPassword=document.getElementById("form-password_confirm").value;
	if(password.length<6) {
		$(".confirmPassword").text('Weak Password');
		$("#form-password_confirm").removeClass('valid'); 
		$("#form-password_confirm").addClass('invalid');
	} else if(password!=confirmPassword) {
		$(".confirmPassword").text('password does not match.please enter again.');
		$("#form-password_confirm").removeClass('valid'); 
		$("#form-password_confirm").addClass('invalid');  
	} else {
		$(".confirmPassword").text('');
		$("#form-password_confirm").removeClass('invalid'); 
		$("#form-password_confirm").addClass('valid');
	}
}