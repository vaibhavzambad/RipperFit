/*var ripperFit =*/
angular.module('ripperFit',[]).controller('loginCtrl', function($scope, $http,$window){

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
		}).then( function (response){
			$scope.employeeDetails = response.data; 
			$window.location.href = '/RipperFit/welcome';
		}, function (){ 
			alert("Wrong username and password!!");
		});
	}
	
	console.log("hello");
});