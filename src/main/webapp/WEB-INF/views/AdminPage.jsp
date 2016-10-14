<!DOCTYPE html>
<html ng-app="admin">
<head>
<meta charset="utf-8">
<title>MyActions</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
<script src="resources/js/adminRoute.js"></script>
<!-- Font Awesome Javascript -->
<script src="https://use.fontawesome.com/8cbee58684.js"></script>
</head>
<body>
	<div>
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
						<li><a href="#"><i class="fa fa-tachometer"
								aria-hidden="true">&nbsp</i> DashBoard</a></li>
						<li><a href="#"><i class="fa fa-play" aria-hidden="true">&nbsp</i>
								Getting Started</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><span class="glyphicon glyphicon-tasks"
								style="top: 3px; right: -7px;">&nbsp</span> My Actions<span
								class="sr-only">(current)</span> <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#/admin/viewRequest">Requests</a></li>
								<li><a href="#">Resources</a></li>
								<li><a href="#/admin/viewEmployee">Crew</a></li>
								<li><a href="#/admin/viewRole">Positions</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><i class="fa fa-user"
								aria-hidden="true">&nbsp</i> Admin <i class="fa fa-caret-down"
								aria-hidden="true"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#">My Profile</a></li>
								<li><a href="#">My Requests</a></li>
								<li><a href="/RipperFit/employee/logout">Logout</a></li>
							</ul></li>
						<li>&nbsp;&nbsp;&nbsp;</li>
					</ul>

				</div>
				<!-- /.navbar-collapse -->
			</div>
		</div>

		<div ng-view="">Welcome to RipperFit</div>


		<footer class="footer text-center"
			style="position: absolute; bottom: 0; width: 100%; background-color: #f8f8f8; border-top: 1.5px solid #e7e7e7; padding: 17px">
			<p style="margin: 0;">
				&copy;2016 RipperFit. All Rights Reserved| Designed by <b>VARAAS</b>
			</p>
		</footer>

		
</body>
</html>
