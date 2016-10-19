<div class="container" style="margin-top: 100px;">
	<h2>Designations</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Designation</th>
				<th>Department</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="des in designation">
				<td>{{des.designationName}}</td>
				<td>{{des.department.departmentName}}</td>
			</tr>
		</tbody>
	</table>
	 <div class=" btn btn-group btn-primary">
        <a style="color: white;" href="#/addPosition">Add Designation</a>
      </div>
</div>