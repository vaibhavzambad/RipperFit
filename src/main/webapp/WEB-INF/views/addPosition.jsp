<div class="container" style="margin-top: 100px;">
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h2>Add Position</h2>
			<br>
			<form>
				<div class="form-group">
					<label for="position">Position</label> <input type="text"
						class="form-control" id="newPosition"
						placeholder="Enter New Position">
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label for="form-department">Department:</label> <select
							class="form-control" id="form-department"
							ng-init="getDepartments()" ng-model="designation.department"
							ng-options="department as department.departmentName for department in departmentDetails ">
						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="form-designation">Upper Level:</label> <select
							class="form-control" id="form-designation"
							ng-init="getDesignations()" ng-model="designation.parentDesignation"
							ng-options="designation as designation.designationName for designation in designationDetails ">
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="form-designation">Lower Level:</label> <select
							class="form-control" id="form-designation"
							ng-init="getDesignations()" ng-model="designation.childDesignation"
							ng-options="designation as designation.designationName for designation in designationDetails ">
						</select>
					</div>
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-default btn-primary">Add</button>
				</div>
			</form>
		</div>
	</div>
	<div class="col-sm-4"></div>
</div>