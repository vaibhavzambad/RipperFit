var RipperFit = angular.module('RipperFit',[])

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

.directive("passwordVerify", function() {
	return {
		require: "ngModel",
		scope: {
			passwordVerify: '='
		},
		link: function(scope, element, attrs, ctrl) {
			scope.$watch(function() {
				var combined;

				if (scope.passwordVerify || ctrl.$viewValue) {
					combined = scope.passwordVerify + '_' + ctrl.$viewValue; 
				}                    
				return combined;
			}, function(value) {
				if (value) {
					ctrl.$parsers.unshift(function(viewValue) {
						var origin = scope.passwordVerify;
						if (origin !== viewValue) {
							ctrl.$setValidity("passwordVerify", false);
							return undefined;
						} else {
							ctrl.$setValidity("passwordVerify", true);
							return viewValue;
						}
					});
				}
			});
		}
	};
})

.controller('socialCtrl', function($scope, $http, $window, StoreService) {
	$('#signinButton').click(function() {
		// signInCallback defined in step 6.
		auth2.grantOfflineAccess({
			'redirect_uri' : 'postmessage'
		}).then(signInCallback);
	});

	function signInCallback(authResult) {
		if (authResult['code']) {
			// Send the code to the server
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
						}), function(response) {
							console.log(response.status);
						};	
					} else {
						StoreService.set($scope.user);
						$window.location.href = 'socialSignUp.html';
					}
				}), function(response) {
					console.log(response.status);
				};
			}, function(response) {
				console.log(response.status);
			});
		}
	}
})

.controller('formPopulateCtrl', function($scope, StoreService, $http, $window, $filter) {
	$scope.user = StoreService.get();
	$scope.getDesignations = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.designationDetails = response.data; 
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.getAllOrganizations = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.organizationDetails = response.data; 
		}, function(response) { 
			console.log(response.status);
		});
	}

	$scope.addUser = function(user) {
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.user.email,
				"organization": $scope.user.organization,
				"password": null,
				"firstName": $scope.user.firstName,
				"lastName": $scope.user.lastName,
				"gender": $scope.user.gender,
				"contactNumber": $scope.user.contactNumber,
				"designation" : $scope.user.designation,
				"profilePicture" :$scope.user.profilePicture
		};
		$http({
			method: 'POST',
			url: "/RipperFit/employee/socialLogin",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.login = response.data;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMail",
				data: $scope.login,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href = 'dashboard.html';
			}, function(response) {
				console.log(response.status);
			});
		});
	}
})

.controller('signUpCtrl', function($scope, $http, $window, $filter){
	$scope.getDesignations = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.designationDetails = response.data; 
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.getAllOrganizations = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.organizationDetails = response.data; 
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.getFormDetails = function(user) {
		$scope.email="";
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.user.email,
				"organization": $scope.user.organization,
				"password": $scope.user.password,
				"firstName": $scope.user.firstName,
				"lastName": $scope.user.lastName,
				"gender": $scope.user.gender,
				"contactNumber": $scope.user.contactNumber,
				"designation" : $scope.user.designation,
				"profilePicture" :null
		};
		$http({
			method: 'POST',
			url: "/RipperFit/employee/addEmployee",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$scope.email = $scope.userDetails.email;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMailOnSignup",
				data: $scope.email,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href = 'dashboard.html';
			}, function(response) {
				console.log(response.status);
			});
		}), function(response) {
			console.log(response.status);
		};
	}

	$scope.login = function(user) {
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
			console.log(response.status);
		});
	}
});