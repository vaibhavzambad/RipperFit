<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SignUp RipperFit</title>

<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/signUpStyle.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
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

		<div class="blank">

			<h3>Sign up now</h3>
			<hr />


			<form role="form" action="" method="post" class="signUp-form"
				ng-controller="signUpCtrl">
				<div class="row">
					<div class="col-md-9">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="sr-only" for="form-email">Email</label> <input
									type="text" name="form-email" placeholder="Email..."
									class="form-email form-control" id="form-email"
									ng-model="user.email" required>
								<p class="help-block email"></p>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-first-name">First name</label>
								<input type="text" name="form-first-name"
									placeholder="First name..."
									class="form-first-name form-control" id="form-first-name"
									ng-model="user.firstName">
								<p class="help-block Fname"></p>
							</div>
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-last-name">Last name</label> <input
									type="text" name="form-last-name" placeholder="Last name..."
									class="form-last-name form-control" id="form-last-name"
									ng-model="user.lastName">
								<p class="help-block Lname"></p>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-gender">Gender:</label> <select
									class="form-control" id="form-gender" ng-model="user.gender">
									<option>Male</option>
									<option>Female</option>
								</select>
								<p class="help-block gender"></p>

							</div>
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-contact">Contact:</label> <input
									type="text" class="form-control" id="form-contact"
									placeholder="Contact Number..." ng-model="user.contactNumber">
								<p class="help-block contact"></p>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-designation">Designation:</label>

								<select class="form-control" id="form-designation"
									ng-init="getDesignations()" ng-model="user.designation"
									ng-options="designation as designation.designationName for designation in designationDetails ">
								</select>
								<p class="help-block designation"></p>
							</div>
							<div class="form-group col-md-6" id="organizationDv">
								<label class="sr-only" for="form-organization">Organization:</label>

								<select class="form-control" id="form-organization"
									ng-init="getAllOrganizations()" ng-model="user.organization"
									ng-options="organization as organization.organizationName for organization in organizationDetails ">
								</select>
								<p class="help-block organization"></p>
							</div>
						</div>
					</div>

					<div class="form-group col-md-3 text-center">
						<p>Upload Profile Picture</p>
						<img class="img img-rounded" width="132" id="image1"
							ng-src="{{user.profilePicture}}" /><br>
						<button id="upload-picture" class="btn">
							<label for="i_file" class="custom-file-upload">upload...</label>
						</button>
						<input type="file" class="text-center" id="i_file" value=""
							style="display: none;" ng-model="user.profilePicture">
						<p class="help-block profilePicture"></p>
					</div>

				</div>



				<div class="form-group">
					<label class="sr-only" for="form-dob">Date Of Birth:</label> <input
						class="form-control" id="form-do" name="form-dob"
						placeholder="Date-Of-Birth(YYYY-MM-DD)" type="text"
						ng-model="user.dateOfBirth" />
					<p class="help-block DOB"></p>
				</div>

				<div class="form-group">
					<label class="sr-only" for="Address">Address</label>
					<textarea name="Address" placeholder="Address..."
						class="Address form-control" id="Address" ng-model="user.address"></textarea>
					<p class="help-block address"></p>
				</div>
				<div class="form-group">
					<label class="sr-only" for="form-password">Password:</label> <input
						type="password" id="form-password" name="password"
						placeholder="Password.." class="form-control input-lg"
						ng-model="user.password">
					<p class="help-block password"></p>
				</div>
				<div class="form-group">
					<label class="sr-only" for="form-password_confirm">Password
						(Confirm)</label>
					<div class="controls">
						<input type="password" id="form-password_confirm"
							name="password_confirm" placeholder="Password (Confirm).."
							class="form-control input-lg">
						<p class="help-block confirmPassword"></p>
					</div>

					<input type="button" class="col-xs-12 btnSubmit btn btn-info"
						ng-click="getFormDetails(user)" onclick="validate()"
						value="Sign me up!">
			</form>
		</div>
	</div>



	<!-- Javascript -->
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!--<script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>-->
	<script src="resources/js/jquery.backstretch.min.js"></script>
	<script src="resources/js/scripts.js"></script>
	<script src="resources/js/controllers.js"></script>
	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
			
        <![endif]-->

</body>

</html>
