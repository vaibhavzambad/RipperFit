

<div class="container" style="margin-top: 100px;">
  <h2>Resource request of {{request[0].employee.firstName}}</h2>
           
  <table class="table table-bordered">
    <thead>
      <tr>
     
        <th>Resource Name</th>
        <th>Requestor Name</th>
		<th>Request Priority</th>
		<th>Request Status</th>
		<th>Description</th>
      </tr>
    </thead>
    <tbody>
      <tr>
     
		<td>{{request[0].resource.resourceName}}</td>
		<td>{{request[0].employee.firstName +" "+ request[0].employee.lastName}}</td>
		<td>{{request[0].priority}}</td>
		<td><span class="label label-success">{{request[0].status}}</span></td>
		<td>{{request[0].comments}}</td>
 
      </tr>
      
    
    </tbody>
  </table>
</div>


<!-- 

<div class="container">
  <h2><strong>Request Pool</strong> of {{request[0].employee.firstName}}</h2>
           
  <table class="table table-bordered">
    <thead>
			<tr>
				<th>#</th>
				<th>Resource</th>
				<th>Request Date</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
   <tbody>
			<tr ng-repeat="request in requests">
			<td>{{$index+1}}</td>
				<td>{{request.resource.resourceName}}</td>
				<td>DatePending</td>
				
				<td><span class="label label-{{request.color}}">{{request.status}}</span></td>
				<td>&nbsp&nbsp&nbsp<i class="fa fa-rocket" aria-hidden="true"></i>&nbsp&nbsp<i
					class="fa fa-pencil" aria-hidden="true"></i>&nbsp&nbsp<i
					class="fa fa-trash-o" aria-hidden="true"></i></td>
			</tr>
		</tbody>
  </table>
</div>


 -->


