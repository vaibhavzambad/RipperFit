<!DOCTYPE html>
<html lang="en" itemscope itemtype="http://schema.org/Article">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Login &amp; Register Templates</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/form-elements.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">


<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->

<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="resources/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="resources/ico/apple-touch-icon-57-precomposed.png">

<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="resources/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="resources/js/angular.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/client:platform.js?onload=start"
	async defer></script>

<script>
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
</script>

</head>

<body ng-app="signUp">

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">



				<div class="row">
					<div class="col-md-12">

						<div class="form-box">
							<div class="form-top">
								<div class="form-top-left">
									<h3>Login to RipperFit</h3>
									<p>Enter Email and password to log on:</p>
								</div>
								<div class="form-top-right">
									<i class="fa fa-lock"></i>
								</div>
							</div>
							<div class="form-bottom" ng-controller="signUpCtrl">
								<form role="form" action="" method="post" class="login-form">
									<div class="form-group">
										<label class="sr-only" for="form-email">Username</label> <input
											type="text" name="form-email" placeholder="Email-id..."
											class="form-username form-control" id="form-email"
											ng-model="user.email">
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-password">Password</label> <input
											type="password" name="form-password"
											placeholder="Password..." class="form-password form-control"
											id="form-password" ng-model="user.password">
									</div>
									<input type="button" class="col-md-12 btnSubmit"
										ng-click="login(user)" value="Log-in" />
								</form>
							</div>
						</div>

						<div class="social-login">
							<h3>...or login with:</h3>
							<div class="social-login-buttons">
								<a class="btn btn-link-2 custom-login" href="#"> <i
									class="fa fa-facebook"></i> Facebook
								</a> <a class="btn btn-link-2 custom-login" href="#"> <i
									class="fa fa-twitter"></i> Twitter
								</a>
								<!-- <a class="btn btn-link-2 custom-login"
									href='https://accounts.google.com/o/oauth2/auth?access_type=offline&
								client_id=412974983923-jg7l8j84308pughfqt5o8q0gc0pslh83.apps.googleusercontent.com&scope=https://www.googleapis.com/auth/userinfo.email+
								https://www.googleapis.com/auth/plus.me+https://www.googleapis.com/auth/plus.login
								&response_type=code&redirect_uri=http://localhost:8080/RipperFit/'>
									<i class="fa fa-google"></i> Google
								</a> -->
								<!-- form action="https://accounts.google.com/o/oauth2/auth" method="POST">
									<button type="submit" class="btn btn-large btn-primary">Sign
										in with Google</button>
									<input type="hidden" name="scope"
										value="https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me" />
									<input type="hidden" name="access_type" value="offline" />
									<input type="hidden" name="response_type" value="code" /> 
									<input type="hidden" name="redirect_uri" value="http://localhost:8080/RipperFit/" />
								</form>  -->
								<!-- <div class="g-signin2" data-onsuccess="onSignIn"
									data-accesstype="offline"
									data-redirecturi="http://localhost:8080/RipperFit/"></div>
							</div> -->
								<button id="signinButton">Sign in with Google</button>
								<script>
									$('#signinButton').click(function() {
										// signInCallback defined in step 6.
										auth2.grantOfflineAccess({
											'redirect_uri' : 'postmessage'
										}).then(signInCallback);
									});
								</script>
							</div>
						</div>

						<script>
function signInCallback(authResult) {
  if (authResult['code']) {

    // Hide the sign-in button now that the user is authorized, for example:
    $('#signinButton').attr('style', 'display: none');

    // Send the code to the server
    $.ajax({
      type: 'POST',
      url: '/RipperFit/social/getDetails',
      contentType: 'application/octet-stream; charset=utf-8',
      success: function(result) {
        // Handle or verify the server response.
      },
      processData: false,
      data: authResult['code']
    });
  } else {
    // There was an error.
  }
}
</script>



						<!-- Javascript -->
						<script src="resources/js/googleApi.js"></script>
						<script src="resources/js/jquery-1.11.1.min.js"></script>
						<script src="resources/bootstrap/js/bootstrap.min.js"></script>
						<script src="resources/js/jquery.backstretch.min.js"></script>
						<script src="resources/js/scripts.js"></script>
						<script src="resources/js/applications.js"></script>
						<script src="resources/js/controllers.js"></script>

						<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>

</html>