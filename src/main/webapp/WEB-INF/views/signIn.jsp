<!DOCTYPE html>
<html lang="en" itemtype="http://schema.org/Article">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>RipperFit</title>

<!-- CSS -->
<link rel="stylesheet" href="resources/css/signUpStyle.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/form-elements.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- scripts -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://apis.google.com/js/client:platform.js?onload=start"
	async defer></script>
</head>
<body ng-app="RipperFit">

	<!-- Header -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid ">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><b>RipperFit</b></a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="signUp"><i class="fa fa-user-plus"
							aria-hidden="true"></i> SignUp</a></li>

					<li>&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</div>

	<!-- Body -->
	<div class="container">
		<div class="blank">
			<div class="row">
				<div class="col-xs-3"></div>
				<div class="col-xs-6">
					<h3 class="text-center">LogIn to Ripperfit</h3>
					<hr />
					<div class="signInBlank" ng-controller="signUpCtrl">
						<form role="form" action="" method="post" class="login-form">
							<div class="form-group">
								<label class="sr-only" for="form-email">Username</label> <input
									type="text" name="form-email" placeholder="Email-id"
									class="form-username form-control" id="form-email"
									ng-model="user.email">
							</div>
							<div class="form-group">
								<label class="sr-only" for="form-password">Password</label> <input
									type="password" name="form-password" placeholder="Password"
									class="form-password form-control" id="form-password"
									ng-model="user.password">
							</div>
							<div class="col-xs-4"></div>
							<input type="button" class="col-xs-4 btn btn-info text-center"
								ng-click="login(user)" value="Log-in" />
						</form>
					</div>
					<div class="social-login text-center">
						<h3>OR</h3>
						<div class="social-login-buttons">
							<button id="signinButton" class="btn btn-danger"
								ng-controller="socialCtrl">
								<i class="fa fa-google"></i> Google
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="footer text-center"
		style="position: absolute; bottom: 0; width: 100%; background-color: #f8f8f8; border-top: 1.5px solid #e7e7e7; padding: 17px">
		<p style="margin: 0;">
			&copy;2016 RipperFit. All Rights Reserved| Designed by <b>VARAAS</b>
		</p>
	</footer>
	<!-- Javascript -->
	<script src="resources/js/googleApi.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- <script src="resources/js/jquery.backstretch.min.js"></script> -->
	<script src="resources/js/scripts.js"></script>
	<script src="resources/js/controllers.js"></script>

</body>
</html>
