

<div class="container">
  <h2>Resource request</h2>
           
  <table class="table table-bordered">
    <thead>
      <tr>
      
      
        <th>Resource</th>
        <th>Requestor Name</th>
		<th>Request Priority</th>
		<th>Request Status</th>
		<th>Description</th>
      </tr>
    </thead>
    <tbody>
      <tr ng-repeat="request in requests"  >
      
       
		<td>{{request.resource.resourceName}}</td>
		<td>{{request.employee.firstName +" "+ request.employee.lastName}}</td>
		<td>{{request.priority}}</td>
		
		<td><span class="label label-{{request.color}}">{{request.status}}</span></td>
		
		<td>{{request.comments}}</td>
 
      </tr>
      
    
    </tbody>
  </table>
</div>


