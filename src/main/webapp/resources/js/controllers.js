signUp.controller('signUpCtrl', function($scope, $http,$window){


	$scope.getFormDetails=function(user){

		$scope.userDetails=angular.copy(user);
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.userDetails.email,
				"password": $scope.userDetails.password,
				"firstName": $scope.userDetails.firstName,
				"lastName": $scope.userDetails.lastName,
				"dateOfBirth": $scope.userDetails.dateOfBirth,
				"gender": $scope.userDetails.gender,
				"contactNumber": $scope.userDetails.contactNumber,
				"address": $scope.userDetails.address,
				"designation" : null,
				"profilePicture" :null
		};



		$http({
			method: 'POST',
			url: "/RipperFit/employee/register",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}}).success(function(result) {
				console.log("worked");
				$window.location.href = '/RipperFit/success';
			}, function(error) {
				console.log("error");
			});

		/*$http.post("/RipperFit/employee/register", $scope.userDetails).success(function (data) {
	  console.log("dfdf");
		console.log(data);
  });*/

	}
	
	$scope.login = function(user){


		console.log("login");


		$scope.loginDetails=angular.copy(user);



		$http({
			method: 'POST',
			url: "/RipperFit/employee/login",
			data: $scope.loginDetails,
			headers: {
				'Content-Type': 'application/json'
			}}).then( function (response){
				
				
				$scope.employeeDetails = response.data; 
				$window.location.href = '/RipperFit/welcome';
				
			}, function (response,$window){ 
				
				alert("wrong username and password");
				
			} 
			
			);
		
		
		/*response: function(response) 
		{ var result = response.resource; 
		result.$status = response.status; 
		return result; }*/


	}

	


});






