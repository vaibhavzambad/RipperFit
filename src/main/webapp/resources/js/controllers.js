<<<<<<< HEAD
signUp.controller('logoutCtrl',function($scope,$http,$window){
	
	$scope.logout = function() {
	$http({
		method: 'POST',
		url: "/RipperFit/employee/logout",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then( function (){
		$window.location.href = '/RipperFit/login';
	}, function (){ 
		alert("Registration failed!!");
	});
	
	}
	
});

signUp.controller('signUpCtrl', function($scope, $http,$window){

	$scope.getFormDetails=function(user) {

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
			url: "/RipperFit/employee/addEmployee",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (){
			$window.location.href = '/RipperFit/success';
		}, function (){ 
			alert("Registration failed!!");
		});
=======
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

>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	}
	
	$scope.login = function(user){

<<<<<<< HEAD
		console.log("login");
		$scope.loginDetails=angular.copy(user);

=======

		console.log("login");


		$scope.loginDetails=angular.copy(user);



>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
		$http({
			method: 'POST',
			url: "/RipperFit/employee/login",
			data: $scope.loginDetails,
			headers: {
				'Content-Type': 'application/json'
<<<<<<< HEAD
			}
		}).then( function (response){
			$scope.employeeDetails = response.data; 
			$window.location.href = '/RipperFit/welcome';
		}, function (){ 
			alert("Wrong username and password!!");
		});
	}
});
=======
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






>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
