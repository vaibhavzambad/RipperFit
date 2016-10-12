<div class="container">
	<h2>View Resource request</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Resource Request Id</th>
				<th>Resource Name</th>
				<th>Requestor Name</th>
				<th>Request Priority</th>
				<th>Request Status</th>
				<th>Comments</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="request in requests">
				<td>{{request.request_id}}</td>
				<td>{{request.resource.resourceName}}</td>
				<td>{{request.employee.firstName +" "+
					request.employee.lastName}}</td>
				<td>{{request.priority}}</td>
				<td>{{request.status}}</td>
				<td>{{request.comments}}</td>
			</tr>
		</tbody>
	</table>
</div>