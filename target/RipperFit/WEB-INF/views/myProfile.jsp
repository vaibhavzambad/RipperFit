<div class="container">
	<div class="row">
		<!-- left column -->
		<div class="col-md-3"></div>

		<!-- edit form column -->
		<div class="col-md-6 personal-info">
			<div class="blank" style="margin-top: 60px;">
				<h1>My Profile</h1>
				<hr>
				<h3>Personal info</h3>

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-lg-3 control-label">Name:</label>
						<div class="col-lg-8">
							<input class="form-control" type="text"
								ng-model="employeeDetails.firstName+' '+employeeDetails.lastName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input class="form-control" type="text"
								ng-model="employeeDetails.email">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Gender:</label>
						<div class="col-lg-8">
							<input class="form-control" type="text"
								ng-model="employeeDetails.gender">
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
								ng-model="employeeDetails.designation.designationName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Organization:</label>
						<div class="col-md-8">
							<input class="form-control" type="text"
								ng-model="employeeDetails.organization.organizationName">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="button" class="btn btn-primary" value="Save Changes"
								ng-click="getUpdatedDetails(user)"> <span></span> <input
								type="reset" class="btn btn-default" value="Cancel">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<hr>