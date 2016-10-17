<!DOCTYPE html>
<html ng-app="RipperFit">
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

<!-- Font Awesome Javascript -->
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top" ng-controller="headerCtrl">
		<div class="container-fluid ">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/RipperFit/"><b>RipperFit</b></a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><i class="fa fa-tachometer"
							aria-hidden="true">&nbsp</i> DashBoard</a></li>
					<li><a href="#"><i class="fa fa-play" aria-hidden="true">&nbsp</i>
							Getting Started</a></li>
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span class="glyphicon glyphicon-tasks"
							style="top: 3px; right: -7px;">&nbsp</span> My Actions<span
							class="sr-only">(current)</span> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li ng-repeat="user in loggedUser"><a href="{{user.href}}">{{user.text}}</a></li>
						</ul></li>
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-user" aria-hidden="true">&nbsp</i>
							{{loggedEmployee}} <span class="caret"></span></i></a>
						<ul class="dropdown-menu">
							<li ng-repeat="userList in loggedUserList"><a href="{{userList.href}}">{{userList.text}}</a></li>
							<li><a href="/RipperFit/employee/logout">Logout</a></li>
						</ul></li>
					<li>&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</div>
	<div ng-view=""></div>
	<footer class="footer text-center"
		style="position: absolute; bottom: 0; width: 100%; background-color: #f8f8f8; border-top: 1.5px solid #e7e7e7; padding: 17px">
		<p style="margin: 0;">
			&copy;2016 RipperFit. All Rights Reserved| Designed by <b>VARAAS</b>
		</p>
	</footer>
</body>
</html>