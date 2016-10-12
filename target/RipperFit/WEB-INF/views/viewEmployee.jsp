

<div class="container">
  <h2>View Designations</h2>
           
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Employee Id</th>
          <th>Employee Email</th>
        <th>Employee Name</th>
        <th>Designation</th>
        <th>View resource request</th>
      </tr>
    </thead>
    <tbody>
      <tr ng-repeat="emp in employee">
        <td>{{emp.employeeId}}</td>
        <td>{{emp.email}}</td>
		<td>{{emp.firstName}}</td>
		<td>{{emp.designation.designationName}}</td>
		 <td><a href="#/admin/viewRequest/{{emp.employeeId}}">View Resource request</a></a></td>
 
      </tr>
      
    
    </tbody>
  </table>
</div>


