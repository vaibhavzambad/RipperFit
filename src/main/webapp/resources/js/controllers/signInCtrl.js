var app = angular.module('RipperFit',[])

.factory('StoreService',["$window",function($window) {
	function set(data) {
		$window.sessionStorage.setItem('userInfo', $window.JSON.stringify(data));
	} function get() {
		return $window.JSON.parse($window.sessionStorage.getItem('userInfo'));
	} return {
		set: set,
		get: get
	}
}])

.controller('signInCtrl', function($scope, $http, $window, $filter, StoreService) {
	$scope.login = function(user) {
		var message = angular.element(document.querySelector('#message'));
		$scope.loginDetails=angular.copy(user);
		$http({
			method: 'POST',
			url: "/RipperFit/employee/login",
			data: $scope.loginDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$window.location.href = 'dashboard.html';
		}, function(response) {
			if(response.status === 401 || response.status === 400){
				message.text("Invalid Username or Password");
			}
		});
	}
	
	$('#signinButton').click(function() {
		auth2.grantOfflineAccess({
			'redirect_uri' : 'postmessage'
		}).then(signInCallback);
	});

	function signInCallback(authResult) {
		if (authResult['code']) {
			$http({
				method : 'POST',
				url : '/RipperFit/social/getDetails',
				headers: {
					'Content-Type': 'application/json'
				},
				processData : false,
				data : authResult['code']
			}).then( function(response) {
				$scope.user = response.data;
				$http({
					method : 'GET',
					url : '/RipperFit/employee/getEmployeeByEmail?email='+$scope.user.email,
					headers: {
						'Content-Type': 'application/json'
					}
				}).then(function(response) {
					if(response.status == 200) {
						$http({
							method : 'GET',
							url : '/RipperFit/employee/createSession?email='+$scope.user.email,
							headers: {
								'Content-Type': 'application/json'
							}
						}).then(function() {
							$window.location.href = 'dashboard.html';
						});	
					} else {
						StoreService.set($scope.user);
						$window.location.href = 'socialSignUp.html';
					}
				});
			});
		}
	}
});