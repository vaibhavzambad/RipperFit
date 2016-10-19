<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SignUp RipperFit</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/signUpStyle.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<!-- Font Awesome Javascript -->
	<script src="https://use.fontawesome.com/8cbee58684.js"></script>
	
</head>

<body ng-app="RipperFit">
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
					<li><a href="login"><i class="fa fa-sign-in"
							aria-hidden="true"></i> LogIn</a></li>

					<li>&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</div>
	<div class="container" style="margin-bottom: 10% !important;">
		<div class="blank">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<h3 class="text-center">Sign up now</h3>
				<hr />
				<form role="form" action="" method="post" class="signUp-form"
					ng-controller="signUpCtrl">
					<div class="row">
						<div class="form-group col-xs-6">
							<label class="sr-only" for="form-first-name">First name</label> <input
								type="text" name="form-first-name" placeholder="First name..."
								class="form-first-name form-control" id="form-first-name"
								ng-model="user.firstName" onfocusout="validateFname()"  required>
							<p class="help-block Fname"></p>
						</div>
						<div class="form-group col-xs-6">
							<label class="sr-only" for="form-last-name">Last name</label> <input
								type="text" name="form-last-name" placeholder="Last name..."
								class="form-last-name form-control" id="form-last-name"
								ng-model="user.lastName" onfocusout="validateLname()" required>
							<p class="help-block Lname"></p>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-xs-8">
							<div class="row">
								<div class="col-xs-12">
									<label class="sr-only" for="form-gender">Gender:</label> <select
										class="form-control" id="form-gender" ng-model="user.gender" onfocusout="validateGender()" required="required">
										<option value="" disabled selected hidden>Gender</option>
										<option>Male</option>
										<option>Female</option>
									</select>
									<p class="help-block gender"></p>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label class="sr-only" for="form-contact">Contact:</label> <input
										type="text" class="form-control" id="form-contact" 
										placeholder="Contact Number..." ng-model="user.contactNumber" onfocusout="validateContact()" required="required">
									<p class="help-block contact"></p>
								</div>
							</div>
							<div class="row">
							<div class="form-group col-xs-12">
								<label class="sr-only" for="form-email">Email</label> <input
									type="text" name="form-email" placeholder="Email..."
									class="form-email form-control" id="form-email"
									ng-model="user.email" onfocusout="validateEmail()" required>
								<p class="help-block email"></p>
							</div>
						</div>
						</div>
						<div class="form-group col-xs-4 text-center">
							<img class="img img-circle" width="110" height="110" id="image1"
								ng-src="{{user.profilePicture}}" src="resources/img/profile.png"
								style="background-color: white;" /><br> <label>
								<h4>Upload </h4><input type="file" class="text-center" id="i_file"
								name="img" style="display: none;" ng-model="user.profilePicture" />
							</label>
						</div>
						
					</div>
					<div class="row">
						<div class="form-group col-xs-6">
							<label class="sr-only" for="form-password">Password:</label> <input
								type="password" id="form-password" name="password"
								placeholder="Password.." class="form-control input-lg"
								ng-model="user.password" onfocusout="validatePassword()" required>
							<p class="help-block password"></p>
						</div>
						<div class="form-group col-xs-6">
							<label class="sr-only" for="form-password_confirm">Password
								(Confirm)</label>
							<div class="controls">
								<input type="password" id="form-password_confirm"
									name="password_confirm" placeholder="Password (Confirm).."
									class="form-control input-lg" onfocusout="confirmPassword()" required>
								<p class="help-block confirmPassword"></p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label class="sr-only" for="form-designation">Designation:</label>
							<select class="form-control" id="form-designation"
								ng-init="getDesignations()" ng-model="user.designation"
								ng-options="designation as designation.designationName for designation in designationDetails ">
								<option value="" disabled selected hidden>Designation</option>
							</select>
							<p class="help-block designation"></p>
						</div>
						<div class="form-group col-md-6" id="organizationDv">
							<label class="sr-only" for="form-organization">Organization:</label>

							<select class="form-control" id="form-organization"
								ng-init="getAllOrganizations()" ng-model="user.organization"
								ng-options="organization as organization.organizationName for organization in organizationDetails ">
								<option value="" disabled selected hidden>Organization</option>
							</select>
							<p class="help-block organization"></p>
						</div>
					</div>
					<div class="col-xs-4"></div>
					<input type="button" class="col-xs-4 btn btn-info text-center"
							ng-click="getFormDetails(user)" value="Sign me up!" />
				</form>
			</div>
		</div>
	</div>
	<footer class="footer text-center"
		style="position: absolute !important; bottom: 0 !important; width: 100%; background-color: #f8f8f8; border-top: 1.5px solid #e7e7e7; padding: 17px">
		<p style="margin: 0;">
			&copy;2016 RipperFit. All Rights Reserved| Designed by <b>VARAAS</b>
		</p>
	</footer>
	<!-- Javascript -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- <script src="resources/js/jquery.backstretch.min.js"></script> -->
	<script src="resources/js/controllers.js"></script>
	<script src="resources/js/scripts.js"></script>
</body>
</html>
