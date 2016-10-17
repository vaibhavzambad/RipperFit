<div class="container" style="margin-top: 100px;">
	<h2>
		<strong>Request Pool</strong>
	</h2>
	<br>
	<table class="table table-bordered table-responsive">
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
			<tr ng-repeat="request in requests">
				<td>{{$index+1}}</td>
				<td>{{request.employee.firstName +"
					"+request.employee.lastName}}</td>
				<td>{{request.resource.resourceName}}</td>
				<td>DatePending</td>
				<td><span class="label label-{{request.color}}">{{request.status}}</span></td>
				<td><i class="fa fa-pencil" aria-hidden="true"></i> &nbsp&nbsp<i
					class="fa fa-trash-o" aria-hidden="true"></i> &nbsp&nbsp<i
					class="fa fa-sort-desc" aria-hidden="true"></i></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div class=" btn btn-group btn-primary">
		<a style="color: white;" href="#/addRequest"><span class="glyphicon glyphicon-plus"> </span> Add Request</a>
	</div>
</div>
