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
				<th>Resource Quanity</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="resource in resources">
				<td>{{$index+1}}</td>
				<td>{{resource.resourceName}}</td>
				<td>{{resource.finalApprovalTierLevel}}</td>
				<td>{{resource.quantity}}</td>
				<td>&nbsp&nbsp&nbsp<i class="fa fa-pencil" aria-hidden="true"></i>&nbsp&nbsp
					<a href="#/deleteResource/{{resource.resourceId}}"
					style="text-decoration: none; color: red;"> <i class="fa fa-trash-o"
						aria-hidden="true"></i>
				</a>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
</div>