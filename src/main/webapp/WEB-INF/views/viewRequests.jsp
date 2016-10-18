<div class="container" style="margin-top: 100px;">
	<h2>
		<strong>Request Pool</strong>
	</h2>
	<br>
	<table class="table table-bordered table-responsive" ng-controller="getLocationCtrl">
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
			<tr ng-click="clickable(request.requestId)"  ng-repeat="request in requests">
				<td>{{$index+1}}</td>
				<td>{{request.employee.firstName +"
					"+request.employee.lastName}}</td>
				<td>{{request.resource.resourceName}}</td>
				<td>{{request.requestDate}}</td>
				<td><span class="label label-{{request.color}}">{{request.status}}</span></td>
				<td><a href="" data-toggle="tooltip" data-placement="top"
					title="Edit Request!" style="color: black"><i
						class="fa fa-pencil" aria-hidden="true"></i></a>&nbsp&nbsp<a href=""
					data-toggle="tooltip" data-placement="bottom"
					title="Delete Request!" style="color: black"><i
						class="fa fa-trash" aria-hidden="true"></i></a></i>&nbsp&nbsp</td>
			</tr>
		</tbody>
	</table>
	<br>
</div>