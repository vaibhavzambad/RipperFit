<div class="container" style="margin-top: 100px;">
	<h2>Edit Department</h2>
	<form class="form-horizontal" role="form">
				
				<div class="form-group">
					<label class="col-md-3 control-label">Department Name:</label>
					<div class="col-md-8">
						<input class="form-control" type="text"
							ng-model="department.departmentName">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-8">
						<input type="button" class="btn btn-primary" value="Save Changes"
							ng-click="UpdateData(department)"> <span></span> <input
							type="reset" class="btn btn-default" value="Cancel">
					</div>
				</div>
			</form>
</div>