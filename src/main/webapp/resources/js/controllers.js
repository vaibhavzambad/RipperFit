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

		$scope.email="";
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
			"profilePicture" :null,
			"is_verified" : "yes"
		};

		$http({
			method: 'POST',
			url: "/RipperFit/employee/addEmployee",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (){
		
			$scope.email = $scope.userDetails.email;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMail",
<<<<<<< HEAD
				data: $scope.email,
=======
				data: $scope.userDetails,
>>>>>>> 2d0817804549089a1e76cbea5641bc972a9afc42
				headers: {
					'Content-Type': 'application/json'
				}
			}).then( function (){
				$window.location.href = '/RipperFit/mailController';
			}, function (){ 
				alert("Registration failed!!");
			});
		}, function (){ 
			alert("Registration failed!!");
		});
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
			}
		}).then(function(response){
			$scope.employeeDetails = response.data;
			console.log("login"+$scope.employeeDetails.firstName);
			$window.location.href = '/RipperFit/welcome';
		}, function (response){ 
			if(response.status == 403) {
				alert("Please verify your account!!");
			} else {
				alert("Wrong username and password!!");
			}
		});
	}
});