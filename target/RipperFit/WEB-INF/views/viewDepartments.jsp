<div class="container" style="margin-top: 100px;">
	<h2>Departments</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Department</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="dep in departments">
				<td>{{dep.departmentName}}</td>
				<td><a href="#/editDepartment/{{dep.departmentId}}"><span
						class="label label-primary">edit</span></a></td>
			</tr>
		</tbody>
	</table>
	 <div class=" btn btn-group btn-primary">
        <a style="color: white;" href="#/addDepartment">Add New </a>
      </div>
</div>