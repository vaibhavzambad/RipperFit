

jQuery(document).ready(function() {
	
	
   
   //upload profile picture
   $('#i_file').change( function(event) {
    $("#image1").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));
});
	
	
});	
//
    
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


    //signUp validation
	function validate(){
	var email=document.getElementById("form-email").value;;
	
	var Fname=document.getElementById("form-first-name").value;
	var Lname=document.getElementById("form-last-name").value;
	var gender=document.getElementById("form-gender").value;
	var contact=document.getElementById("form-contact").value;
	var designation=document.getElementById("form-designation").value;
	var profilePhoto=document.getElementById("i_file").value;
	var address=document.getElementById("Address").value;
	var password=document.getElementById("form-password").value;
	var confirmPassword=document.getElementById("form-password_confirm").value;
	
	if(email==(null||"")||Fname==(null||"")||Lname==(null||"")||gender==(null||"")||contact==(null||"")||designation==(null||"")||profilePhoto==(null||"")||address==(null||"")||password==(null||"")||confirmPassword==(null||"")){
 window.alert("all fields are mandatory.");
} 
}
function validateEmail(){
var email=document.getElementById("form-email").value;
var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
if (pattern.test(email)==false)
{
    
   $(".email").text('Please enter a valid e-mail address.');
   $("#form-email").removeClass('valid');
   $("#form-email").addClass('invalid');
}else{
 $(".email").text('');
  $("#form-email").removeClass('invalid');
   $("#form-email").addClass('valid');
}
}
 function validateFname(){
 var pattern = new RegExp(/^[+a-zA-Z]/i);
 
 var Fname=document.getElementById("form-first-name").value;
if(Fname.length<2||pattern.test(Fname)==false){

$(".Fname").text("name must have at least 2 characters");
$("#form-first-name").removeClass('valid');
   $("#form-first-name").addClass('invalid');

}else{
$(".Fname").text('');
$("#form-first-name").removeClass('invalid');
   $("#form-first-name").addClass('valid');
}
}
function validateLname(){
var pattern = new RegExp(/^[+a-zA-Z]/i);
var Lname=document.getElementById("form-last-name").value;
if(Lname.length<2||pattern.test(Lname)==false){
$(".Lname").text('name must have atleast 2 characters');
 $("#form-last-name").removeClass('valid');
   $("#form-last-name").addClass('invalid');
}else{
$(".Lname").text('');
 $("#form-last-name").removeClass('invalid');
   $("#form-last-name").addClass('valid');
}
}
function validateGender(){
var gender=document.getElementById("form-gender").value;
if (gender == "")
    {
        
        $(".gender").text('Please select gender');
		 $("#form-gender").removeClass('valid');
   $("#form-gender").addClass('invalid');

    }else{
 $(".gender").text('');
		 $("#form-gender").removeClass('invalid');
   $("#form-gender").addClass('valid');
}    
    }
function validateContact(){	
var pattern = new RegExp(/^[+0-9]/i);
var contact=document.getElementById("form-contact").value;
if(contact.length!=10||pattern.test(contact)==false)	{

     $(".contact").text('Please enter valid contact number');
   $("#form-contact").addClass('invalid');   
        
}
}
function validatePassword(){
var password=document.getElementById("form-password").value;
if(password.length<6){
$(".password").text('password must have length atleast 6 ');
$("#form-password").removeClass('valid');  
   $("#form-password").addClass('invalid');  
}
else{
$(".password").text('');
$("#form-password").removeClass('invalid');  
   $("#form-password").addClass('valid');
}
}
function confirmPassword(){
var password=document.getElementById("form-password").value;
	var confirmPassword=document.getElementById("form-password_confirm").value;
	
if(password!=confirmPassword){
$(".password,.confirmPassword").text('password does not match.please enter again.');
$("#form-password,#form-password_confirm").removeClass('valid'); 

   $("#form-password,#form-password_confirm").addClass('invalid');  
}else{
$(".password,.confirmPassword").text('');
$("#form-password,#form-password_confirm").removeClass('invalid'); 
   $("#form-password,#form-password_confirm").addClass('valid');
}
}
    
  
  
  
