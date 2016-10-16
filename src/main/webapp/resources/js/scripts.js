
jQuery(document).ready(function() {
	
	//background
   $.backstretch("resources/img/form-bg.jpg");
   
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
if (email.indexOf("@", 0) < 0)
{
    
   $(".email").text('Please enter a valid e-mail address.');
   $("#form-email").addClass('invalid');
}
if (email.indexOf(".", 0) < 0)
{
    
   $(".email").text('Please enter a valid e-mail address.');
   $("#form-email").addClass('invalid');
   
}

if(Fname.length<2){

$(".Fname").text('name must have atleast 2 characters');
   $("#form-first-name").addClass('invalid');

}
if(Lname.length<2){
$(".Lname").text('name must have atleast 2 characters');
   $("#form-last-name").addClass('invalid');

}
if ($("#form-gender").selectedIndex < 1)
    {
        
        $(".gender").text('Please select gender');
   $("#form-gender").addClass('invalid');

        
    }	
if(contact.length!=10)	{

     $(".contact").text('Please enter valid contact number');
   $("#form-contact").addClass('invalid');   
        
}
if(password!=confirmPassword){
$(".password,.confirmPassword").text('password does not match.please enter again.');
   $("#form-password,#form-password_confirm").addClass('invalid');  
}
    
   } 
  