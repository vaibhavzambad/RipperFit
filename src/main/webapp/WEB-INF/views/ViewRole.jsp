<div class="container" style="margin-top: 100px;">
	<h2>Designations</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Designation</th>
				<th>Parent Designation</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="des in designation">
				<td>{{des.designationName}}</td>
				<td>{{des.designation.designationName}}</td>
			</tr>
		</tbody>
	</table>
	 <div class=" btn btn-group btn-primary">
        <a style="color: white;" href="#/admin/addPosition">Add Designation</a>
      </div>
</div>


