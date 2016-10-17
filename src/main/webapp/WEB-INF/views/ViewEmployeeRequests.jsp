<div class="container" style="margin-top: 100px;">
	<h2>Resource request of {{request[0].employee.firstName}}</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Resource</th>
				<th>Requestor</th>
				<th>Request Priority</th>
				<th>Request Status</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>{{request[0].resource.resourceName}}</td>
				<td>{{request[0].employee.firstName +" "+
					request[0].employee.lastName}}</td>
				<td>{{request[0].priority}}</td>
				<td><span class="label label-success">{{request[0].status}}</span></td>
				<td>{{request[0].comments}}</td>
			</tr>
		</tbody>
	</table>
</div>