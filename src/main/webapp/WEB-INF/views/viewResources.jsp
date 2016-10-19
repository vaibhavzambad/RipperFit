<div class="container" style="margin-top: 100px;">
	<h2>
		<strong>Resource Pool</strong>
	</h2>
	<br>
	<table class="table table-bordered table-responsive">
		<thead>
			<tr>
				<th>#</th>
				<th>Resource</th>
				<th>Final Approval Level</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="resource in resources">
				<td>{{$index+1}}</td>
				<td>{{resource.resourceName}}</td>
				<td>{{resource.finalApprovalLevel}}</td>
				<td><a href="#/editResource/{{resource.resourceId}}"><span
						class="label label-primary">edit</span></a></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div class=" btn btn-group btn-primary">
        <a style="color: white;" href="#/addResources">Add Resources</a>
      </div>
</div>