var ripperFit = angular.module('ripperFit',[]);

ripperFit.controller('logoutCtrl',function($scope,$http,$window){

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