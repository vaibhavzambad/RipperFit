

<div class="container">
  <h2>Designations</h2>
           
  <table class="table table-bordered">
    <thead>
      <tr>
     
       
        <th>Designation</th>
        <th>Parent Designation</th>
         <th>Delete Designation</th>
         <th>Update Designation</th>
      </tr>
    </thead>
    <tbody>
      <tr ng-repeat="des in designation">
      
       
		<td>{{des.designationName}}</td>
		<td>{{des.designation.designationName}}</td>
		<td><button type="button" class="btn btn-primary">Update</button></td>
		<td><button type="button" class="btn btn-danger">Delete</button></td>
 
      </tr>
      
    
    </tbody>
  </table>
</div>


