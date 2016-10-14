

<div class="container">
  <h2>Employees</h2>
           
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Employee Name</th>
          <th>Employee Email</th>
        
        <th>Designation</th>
        <th>Resource Request</th>
         <th>Delete Employee</th>
      </tr>
    </thead>
    <tbody>
      <tr ng-repeat="emp in employee">
       
      	<td>{{emp.firstName}}</td>
        <td>{{emp.email}}</td>
	
		<td>{{emp.designation.designationName}}</td>
		 <td>
		
		 <a href="#/admin/viewRequest/{{emp.employeeId}}"><span class="label label-primary">Resource request</span></a>
		 </td>
		 <td><button type="button" class="btn btn-danger">Delete</button></td>
 
      </tr>
      
    
    </tbody>
  </table>
</div>


