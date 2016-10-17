<div class="container" style="margin-top: 100px;">
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h2>Add Request</h2>
			<br>
			<form>
				<div class="form-group">
					<label for="">Resource</label> <select class="form-control" id="form-designation" ng-init="getResources()" ng-model="request.resource"
						ng-options="resource as resource.resourceName for resource in resourceDetails ">
					</select>

				<div class="form-group">
					<label for="">Resource priority</label>
					<div class="form-check">
						<label class="form-radio-label"> </label> <input type="radio"
							class="form-check-input" value="high" ng-model="request.priority">
						High Priority</label> <label><input type="radio"
							class="form-check-input" value="medium"
							ng-model="request.priority"> Medium Priority</label> <label><input
							type="radio" class="form-check-input" value="low"
							ng-model="request.priority"> Low Priority</label> </label>
					</div>
				</div>
				<div class="form-group">
					<label for="">Detail Description</label>
					<textarea class="form-control" ng-model="request.des">                     
       </textarea>
				</div>
				<input type="button" class=" btn btn-info text-center" ng-click="getFormDetails(request)" value="Add Request!" />
			</form>
		</div>
	</div>
	
</div>
