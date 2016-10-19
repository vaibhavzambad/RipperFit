<div class="container" style="margin-top: 100px;">
	<h2>
		<strong>Request Pool</strong>
	</h2>
	<br>
	</br>
	<input class="inputBox" type="text" name="name" value="" placeholder="Serach Request By Status" ng-model="search" />
	</br>
	<table class="table table-bordered table-responsive" ng-controller="getLocationCtrl" id="table">
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
			<tr ng-click="clickable(request.requestId)"  ng-repeat="request in requests | filter:search">
				<td>{{$index+1}}</td>
				<td>{{request.employee.firstName +"
					"+request.employee.lastName}}</td>
				<td>{{request.resource.resourceName}}</td>
				<td>{{request.requestDate}}</td>
				<td><span class="label label-{{request.color}}">{{request.status}}</span></td>
				<td><a class="btn" href="#/editRequest/{{request.requestId}}">
 <i class="fa fa-pencil"></i></a>&nbsp&nbsp<a href=""
					data-toggle="tooltip" data-placement="bottom"
					title="Delete Request!" style="color: black"><i
						class="fa fa-trash" aria-hidden="true"></i></a></i>&nbsp&nbsp</td>
			</tr>
		</tbody>
	</table>
	<div id="msg">
	<hr>
	<h4>No Request Found </h4>
	</div>
	<br>
</div>