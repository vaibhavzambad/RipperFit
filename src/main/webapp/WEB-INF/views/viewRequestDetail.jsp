<div class="container" style="margin-top: 100px;">
	<div class="row">
	
			<h2>Resource Request Detail</h2>
			<br>
			<form>
				<div class="form-group">
					<label for="">Resource Requested is {{requests.resource.resourceName}}</label> 
				<div class="form-group">
					<label for="">{{requests.priority}}Priority</label>
					
				</div>
				<div class="form-group">
					<label for="">Request is  {{requests.status}}</label>
					
				</div>
				<div class="form-group">
					<label for="">{{requests.message}}</label>
					
				</div>
				
			<div class = "panel panel-default">
   <div class ="panel-heading">Comment Box</div>
   
   
   
   <ul class = "list-group">
      <li class = "list-group-item" ng-repeat="comment in comments">
     <div class="row" >
     <div class="col-lg-2">
     <div class="commenterImage">
               <img src= alt="image" width=80px height=80px/>
       </div>
     
     </div>
     <div class="col-lg-10">
    {{comment.employee.firstName}}
     </div>
     <div class="col-lg-10">
   {{comment.commentDate}}
     </div>
    
     <div class="col-lg-10">
   {{comment.comments}}
     </div>
     </div>
      </li>
     
     
   </ul>

</div>
			</form>
				<form>
				<div class="form-group">
					<label for="">Write Comment</label>
					<textarea class="form-control" >                     
                </textarea>
				</div>

				<input type="button" class=" btn btn-info text-center" value="Add Comments"/>
				</form>
	
</div>
