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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" />
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
<script src="resources/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="resources/js/angular.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$('document').ready(init);
	function init() {

		$("#form-dob").datepicker();
		//

		$('#form-designation')
				.on(
						'change',
						function() {
							if ($('#form-designation').value == "admin") {
								$('#form-organization').remove();
								$('#organizationDv')
										.append(
												'<input type="text" name="form-organization" placeholder="Organization Name..." class="form-organization form-control" id="form-organization" ng-model="user.organization"/>');
							}
						});

		$('#i_file').change(
				function(event) {
					$("#image1").fadeIn("fast").attr('src',
							URL.createObjectURL(event.target.files[0]));
				});
	}
</script>
</head>

<body ng-app="signUp">


	<div class="form-box">
		<div class="form-top">
			<div class="form-top-left">
				<h3>Sign up now</h3>

			</div>
			<div class="form-top-right">
				<i class="fa fa-pencil"></i>
			</div>
		</div>
		<div class="form-bottom">
			<form role="form" action="" method="post" class="registration-form"
				ng-controller="signUpCtrl">
				<div class="row">
					<div class="col-md-8">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="sr-only" for="form-email">Email</label> <input
									type="text" name="form-email" placeholder="Email..."
									class="form-email form-control" id="form-email"
									ng-model="user.email">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-first-name">First name</label>
								<input type="text" name="form-first-name"
									placeholder="First name..."
									class="form-first-name form-control" id="form-first-name"
									ng-model="user.firstName">
							</div>
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-last-name">Last name</label> <input
									type="text" name="form-last-name" placeholder="Last name..."
									class="form-last-name form-control" id="form-last-name"
									ng-model="user.lastName">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-gender">Gender:</label> <select
									class="form-control" id="form-gender" ng-model="user.gender">
									<option>Male</option>
									<option>Female</option>
								</select>

							</div>
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-contact">Contact:</label> <input
									type="text" class="input-medium bfh-phone"
									data-format="+1 (ddd) ddd-dddd" id="form-contact"
									placeholder="Contact Number..." ng-model="user.contactNumber">
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="sr-only" for="form-designation">Designation:</label>
								
								<select class="form-control" id="form-designation"
									ng-init="getDesignations()" ng-model="user.designation"
									ng-options="designation as designation.designationName for designation in designationDetails ">
								</select>
								<!-- <select class="form-control" id="form-designation"
									ng-init="getDesignations()" ng-model="user.designation">
									<option>Select Designation</option>
									<option ng-repeat="deg in designationDetails">{{deg.designationName}}</option>

								</select> -->
							</div>
							<div class="form-group col-md-6" id="organizationDv">
								<label class="sr-only" for="form-organization">Organization:</label>

								<select class="form-control" id="form-organization" ng-init="getAllOrganizations()"
									ng-model="user.organization"
									ng-options="organization as organization.organizationName for organization in organizationDetails ">
								</select>
							</div>
						</div>
					</div>

					<div class="form-group col-md-4 text-center">
						<p>Upload Profile Picture</p>
						<img class="img img-rounded" width="132" id="image1"
							ng-src="{{user.profilePicture}}" /><br>
						<button id="upload-picture" class="btn">
							<label for="i_file" class="custom-file-upload">upload...</label>
						</button>
						<input type="file" class="text-center" id="i_file" value=""
							style="display: none;" ng-model="user.profilePicture">
					</div>

				</div>



				<div class="form-group">
					<label class="control-label" for="form-dob">Date Of Birth:</label>
					<input class="form-control" id="form-do" name="form-dob"
						placeholder="YYYY-MM-DD" type="text" ng-model="user.dateOfBirth" />
				</div>

				<div class="form-group">
					<label class="sr-only" for="Address">Address</label>
					<textarea name="Address" placeholder="Address..."
						class="Address form-control" id="Address" ng-model="user.address"></textarea>
				</div>
				<div class="form-group">
					<label class="sr-only" for="form-password">Password:</label> <input
						type="password" id="form-password" name="password"
						placeholder="Password.." class="form-control input-lg"
						ng-model="user.password">
					<p class="help-block">Password should be at least 6 characters</p>
				</div>
				<div class="form-group">
					<label class="sr-only" for="form-password_confirm">Password
						(Confirm)</label>
					<div class="controls">
						<input type="password" id="form-password_confirm"
							name="password_confirm" placeholder="Password (Confirm).."
							class="form-control input-lg">
						<p class="help-block">Please confirm password</p>
					</div>
				</div>
				<input type="button" class="col-xs-12 btnSubmit"
					ng-click="getFormDetails(user)" onclick="" value="Sign me up!">
			</form>
		</div>
	</div>



	<!-- Javascript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.backstretch.min.js"></script>
	<script src="resources/js/scripts.js"></script>
	<script src="resources/js/controllers.js"></script>
	
</body>

</html>
