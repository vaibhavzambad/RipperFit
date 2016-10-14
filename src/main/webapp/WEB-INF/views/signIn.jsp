<!DOCTYPE html>
<html lang="en" itemtype="http://schema.org/Article">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Login &amp; Register Templates</title>

<!-- CSS -->
<link rel="stylesheet" href="resources/css/signUpStyle.css">
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

<!-- scripts -->
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

	<div class="container_fluid">
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">RipperFit</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-3"></div>
			<div class="col-xs-6">
				<h3>LogIn to Ripperfit</h3>
				<hr />
				<div class="signInBlank" ng-controller="signUpCtrl">
					<form role="form" action="" method="post" class="login-form">
						<div class="form-group">
							<label class="sr-only" for="form-email">Username</label> <input
								type="text" name="form-email" placeholder="Email-id..."
								class="form-username form-control" id="form-email"
								ng-model="user.email">
						</div>
						<div class="form-group">
							<label class="sr-only" for="form-password">Password</label> <input
								type="password" name="form-password" placeholder="Password..."
								class="form-password form-control" id="form-password"
								ng-model="user.password">
						</div>

						<div class="col-xs-4"></div>
						<input type="button" class="col-xs-4 btn btn-info text-center"
							ng-click="login(user)" value="Log-in" onclick="validate()" />

					</form>
				</div>
				<div class="social-login text-center">
					<h3>...or login with:</h3>
					<div class="social-login-buttons">
						<button id="signinButton" class="btn"
							style="background-color: red;" ng-controller="socialCtrl">
							<i class="fa fa-google"></i> Google
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Javascript -->
	<script src="resources/js/googleApi.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="resources/js/scripts.js"></script>
	<script src="resources/js/controllers.js"></script>
</body>
</html>