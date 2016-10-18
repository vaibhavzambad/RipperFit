<div class="container" style="margin-top: 100px;">
	<h2>
		<strong>Approve Request Pool</strong>
	</h2>
	<br>
	<table class="table table-bordered table-responsive" ng-controller="forwardRequestCtrl">
		<thead>
			<tr>
				<th>#</th>
				<th>Requestor</th>
				<th>Resource</th>
				<th>Request Date</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="request in requestsToApprove">
				<td>{{$index+1}}</td>
				<td>{{request.employee.firstName +"
					"+request.employee.lastName}}</td>
				<td>{{request.resourceRequest.resource.resourceName}}</td>
				<td>{{request.resourceRequest.requestDate}}</td>
				<td><span class="label label-{{request.color}}">{{request.resourceRequest.status}}</span></td>
				<td><input type=button ng-click="forwardRequest(request)" value="Approve"/>
			</tr>
		</tbody>
	</table>
	<br>
</div>