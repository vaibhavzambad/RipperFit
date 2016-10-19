<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<h2>Reset Password</h2>
				<hr style="margin-top: 7px;">
				<br>
				<form ng-controller="changePasswordController">
					<div class="form-group">
						<label for="email">Old Password</label>
						<input type="password" class="form-control" ng-model="oldPassword" id="oldPassword" placeholder="Enter Old Password">
					</div>
					<div class="form-group">
						<label for="pwd">New Password</label>
						<input type=password class="form-control" ng-model="newPassword" id="newPassword" placeholder="Enter New Password">
					</div>
					<div class="form-group">
						<label for="pwd">Confirm Password</label>
						<input type=password class="form-control" id="confirmPassword" placeholder="Enter Confirm Password">
					</div>
					<br>
					<input type="button" class="btn btn-default btn-primary" ng-click="changePassword(oldPassword,newPassword)" value="Reset Password"/>
					<br/>
					<br/>
					<div id="message">  </div> 
				</form>
			</div>
		</div>
		<div class="col-sm-4"></div>
	</div>>