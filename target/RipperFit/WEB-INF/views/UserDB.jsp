<!doctype html>
<html lang="en" ng-app="admin">
<head>

<meta charset="utf-8">
<title>User DashBoard</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
</head>
<body ng-app="">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">RipperFit</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="">Actions<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="">Add Request</a></li>
							<li><a href="">Update Request</a></li>
							<li><a href="">Delete Request</a></li>
						</ul></li>


					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="">Actions on requests<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="">Decline requests</a></li>
							<li><a href="">Approve requests</a></li>
							<li><a href="">Pending Requests</a></li>

						</ul></li>
					<li><a href="">View Comments</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="">Employee<span class="caret"></span></a>
						<ul class="dropdown-menu" ng-controller="logoutCtrl">
							<li><a href="">View Profile</a></li>
							<li><a href="">Log Out</a></li>

						</ul></li>

				</ul>
			</div>
		</div>
	</nav>




	<div ng-view="">Welcome to RipperFit</div>
	<div ng-app="">
		<div ng-include="" src="'footer.jsp'"></div>
	</div>
	<footer class="navbar-fixed-bottom text-center"
		style="background-color: black"> @copyright Ripperfit.com </footer>
	</div>
	
</body>

</html>
