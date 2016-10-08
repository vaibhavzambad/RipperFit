<!DOCTYPE html>
<html lang="en">

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
<script src="resources/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="resources/js/angular.js"></script>

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
								<form action="signin/google" method="POST">
									<button type="submit" class="btn btn-large btn-primary">Sign
										in with Google</button>
									<input type="hidden" name="scope"
										value="email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/latitude.all.best" />
									<input type="hidden" name="request_visible_actions"
										value="http://schemas.google.com/AddActivity http://schemas.google.com/BuyActivity http://schemas.google.com/CheckInActivity http://schemas.google.com/CommentActivity http://schemas.google.com/CreateActivity http://schemas.google.com/DiscoverActivity http://schemas.google.com/ListenActivity http://schemas.google.com/ReserveActivity http://schemas.google.com/ReviewActivity http://schemas.google.com/WantActivity" />
									<input type="hidden" name="access_type" value="offline" />
								</form>
							</div>
						</div>

					</div>



					<!-- Javascript -->
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