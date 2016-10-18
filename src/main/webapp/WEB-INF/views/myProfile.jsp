<div class="container" style="margin-top: 100px;">
	<div class="row">
		<!-- left column -->
		<div class="col-md-3"></div>

		<!-- edit form column -->
		<div class="col-md-9 personal-info">
			<h1>My Profile</h1>
			<hr>
			<div class="alert alert-info alert-dismissable">
				<a class="panel-close close" data-dismiss="alert">×</a> <i
					class="fa fa-flag" aria-hidden="true"></i>

			</div>
			<h3>Personal info</h3>

			<form class="form-horizontal" role="form">
			
			<div class="form-group">
					<label class="col-lg-3 control-label">profile Picture:</label>
					<div class="col-lg-8">
						<img alt="picture" ng-src={{employeeDetails.profilePicture}}>
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-lg-3 control-label">First Name:</label>
					<div class="col-lg-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.firstName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">Last Name:</label>
					<div class="col-lg-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.lastName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">Email:</label>
					<div class="col-lg-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.email" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">Gender:</label>
					<div class="col-lg-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.gender" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">Contact :</label>
					<div class="col-lg-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.contactNumber">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Designation:</label>
					<div class="col-md-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.designation.designationName" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Organization:</label>
					<div class="col-md-8">
						<input class="form-control" type="text"
							ng-model="employeeDetails.organization.organizationName" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Password:</label>
					<div class="col-md-8">
						<input class="form-control" type="password"
							ng-model="employeeDetails.password" readonly>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-8">
						<input type="button" class="btn btn-primary" value="Save Changes"
							ng-click="UpdateData(employeeDetails)"> <span></span> <input
							type="reset" class="btn btn-default" value="Cancel">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<hr>