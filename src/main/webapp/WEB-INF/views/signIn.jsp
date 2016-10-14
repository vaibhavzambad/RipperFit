<!DOCTYPE html>
<html lang="en" itemtype="http://schema.org/Article">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Login &amp; Register Templates</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/form-elements.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
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
								<button id="signinButton" ng-controller="socialCtrl">Sign in with Google</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- Javascript -->
		<script src="resources/js/googleApi.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="resources/js/jquery.backstretch.min.js"></script>
		<script src="resources/js/scripts.js"></script>
		<script src="resources/js/controllers.js"></script>
		<script src="resources/js/socialLoginController.js"></script>
</body>
</html>