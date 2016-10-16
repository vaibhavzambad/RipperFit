<div class="container" style="margin-top: 100px;">
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h2>Add Request</h2>
			<br>
			<form ng-controller="getResourceCtrl">
				<div class="form-group">
					<label for="">Resource</label> <select class="form-control"
						id="form-designation" ng-init="getResources()"
						ng-model="user.resource">
						<option value="">Select Resource</option>
						<option ng-repeat="resource in resourceDetails">{{resource.resourceName}}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="">Resource priority</label>
					<div class="form-check">
						<label class="form-radio-label"> <label><input
								type="radio" class="form-check-input" value="1"
								ng-model="user.priority"> High Priority</label> <label><input
								type="radio" class="form-check-input" value="2"
								ng-model="user.priority"> Medium Priority</label> <label><input
								type="radio" class="form-check-input" value="3"
								ng-model="user.priority"> Low Priority</label>
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="">Detail Description</label>
					<textarea class="form-control">                     
       </textarea>
				</div>
				<div class=" btn btn-group btn-primary form-control">
					<a style="color: white;" href="#/addRequest">Add Request</a>
				</div>
			</form>
		</div>
	</div>
	<div class="col-sm-4"></div>
</div>