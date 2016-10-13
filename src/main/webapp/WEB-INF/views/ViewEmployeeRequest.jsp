

<div class="container">
	<h2>View Resource request</h2>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Organization Name</th>
				<th>Resource Request Id</th>
				<th>Resource Name</th>
				<th>Requestor Name</th>
				<th>Request Priority</th>
				<th>Request Status</th>
				<th>Comments</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>{{request[0].organization.organizationName}}</td>
				<td>{{request[0].request_id}}</td>
				<td>{{request[0].resource.resourceName}}</td>
				<td>{{request[0].employee.firstName +" "+
					request[0].employee.lastName}}</td>
				<td>{{request[0].priority}}</td>
				<td>{{request[0].status}}</td>
				<td>{{request[0].comments}}</td>

			</tr>


		</tbody>
	</table>
</div>


