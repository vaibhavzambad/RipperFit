

<div class="container">
  <h2>View Designations</h2>
           
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Designation Id</th>
        <th>Designation</th>
        <th>Parent Designation Id</th>

      </tr>
    </thead>
    <tbody>
      <tr ng-repeat="des in designation">
        <td>{{des.designationId}}</td>
		<td>{{des.designationName}}</td>
		<td>{{des.parent_designation_id}}</td>
		
 
      </tr>
      
    
    </tbody>
  </table>
</div>


