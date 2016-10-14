<!DOCTYPE html>
<html lang="en" itemtype="http://schema.org/Article">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SignIn to RipperFit</title>

<!-- CSS -->
<link rel="stylesheet" href="resources/css/signUpStyle.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">



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
<body ng-app="ripperfit">

	<nav class="navbar navbar-inverse">
								<div class="container-fluid">
									<div class="navbar-header">
										<a class="navbar-brand" href="#">RipperFit</a>
									</div>
									<ul class="nav navbar-nav">
										<li class="active"><a href="#">Home</a></li>
      
									</ul>
								</div>
							</nav>
                            <div class="container">
							<div class="blank">
							  
				              <h3>SignIn to Ripperfit</h3><hr />
                                    <div class="signInBlank">
			                         <form role="form" action="" method="post" class="login-form" ng-controller="signUpctrl">
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
									<input type="button" class="col-xs-12 btn btn-info"
										ng-click="login(user)" value="Log-in" onclick="validate()"/>
								</form>
	                            <div class="social-login text-center">
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
