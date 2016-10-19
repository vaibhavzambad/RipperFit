<div class="container" style="margin-top: 100px;">
	<h2>Resource request</h2>
	</br>
	<input id="input" class="inputBox" type="text" name="name" value="" placeholder="Serach Request By Status" ng-model="search" />
	</br>
	</br>
	<table class="table table-bordered" ng-controller="getLocationCtrl" id="table">
		<thead>
			<tr>

				<th>Resource</th>
				<th>Requestor</th>
				<th>Request Priority</th>
				<th>Request Status</th>
				<th>Actions</th>

			</tr>
		</thead>
		<tbody>
			
			<tr ng-click="clickable(request.requestId)"  ng-repeat="request in requests | filter:search ">
		
				<td>{{request.resource.resourceName}}</td>
				<td>{{request.employee.firstName +" "+
					request.employee.lastName}}</td>
				<td>{{request.priority}}</td>
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
	<h4>Currently No Request</h4>
	</div>
	</br>
	<div class=" btn btn-group btn-primary">
		<a class="anchorButton" style="color: white;" href="#/addRequest">Add Request</a>
	</div>
</div>