var RipperFit = angular.module('RipperFit',[])

.factory('StoreService',["$window",function($window) {

	function set(data) {
		$window.sessionStorage.setItem('userInfo',$window.JSON.stringify(data));
	} function get() {
		return $window.JSON.parse($window.sessionStorage.getItem('userInfo'));
	} return {
		set: set,
		get: get
	}
}])

.controller('socialCtrl',function($scope,$http,$window,StoreService) {

	$('#signinButton').click(function() {
		// signInCallback defined in step 6.
		auth2.grantOfflineAccess({
			'redirect_uri' : 'postmessage'
		}).then(signInCallback);
	});

	function signInCallback(authResult) {
		if (authResult['code']) {
			// Hide the sign-in button now that the user is authorized, for example:
			$('#signinButton').attr('style', 'display: none');
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
				StoreService.set($scope.user);
				$window.location.href="/RipperFit/signUpAfterSocialLogin";
			}, function () {
				alert("Something went wrong");
			});
		}
	}
})
.controller('formPopulateCtrl',function($scope,StoreService,$http,$window,$filter) {

	$scope.user = StoreService.get();
	$scope.getDesignations=function() {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response) {
			$scope.designationDetails = response.data; 
		}, function () {
			alert("No designations found");
		});
	}

	$scope.getAllOrganizations=function() {
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response) {
			$scope.organizationDetails = response.data; 
		}, function () { 
			alert("No Organizations found");
		});
	}

	$scope.addUser=function(user) {
		$scope.userDetails=angular.copy(user);
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.userDetails.email,
				"organization": $scope.userDetails.organization,
				"password": null,
				"firstName": $scope.userDetails.firstName,
				"lastName": $scope.userDetails.lastName,
				"gender": $scope.userDetails.gender,
				"contactNumber": $scope.userDetails.contactNumber,
				"designation" : $scope.userDetails.designation,
				"profilePicture" :$scope.userDetails.profilePicture
		};

		$http({
			method: 'POST',
			url: "/RipperFit/employee/socialLogin",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function () {
			$window.location.href = '/RipperFit/dashboard';
		});
	}
})
.controller('signUpCtrl', function($scope, $http, $window, $filter){

	$scope.getDesignations=function() {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response) {
			$scope.designationDetails = response.data; 
		}, function (response) {
			alert("No designations found");
		});
	}
	$scope.getAllOrganizations=function() {
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response) {
			$scope.organizationDetails = response.data; 
		}, function () {
			alert("No Organizations found");
		});
	}
	$scope.getFormDetails=function(user) {

		$scope.email="";
		$scope.userDetails=angular.copy(user);
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.userDetails.email,
				"organization": $scope.userDetails.organization,
				"password": $scope.userDetails.password,
				"firstName": $scope.userDetails.firstName,
				"lastName": $scope.userDetails.lastName,
				"gender": $scope.userDetails.gender,
				"contactNumber": $scope.userDetails.contactNumber,
				"designation" : $scope.userDetails.designation,
				"profilePicture" :null
		};
		$http({
			method: 'POST',
			url: "/RipperFit/employee/addEmployee",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function () {

			$scope.email = $scope.userDetails.email;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMail",
				data: $scope.email,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then( function () {
				$window.location.href = '/RipperFit/mailController';
			}, function () {
				alert("Registration failed!!");
			});
		});
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
		}).then( function (response) {
			$window.location.href = '/RipperFit/dashboard';
		}, function (response) {
			alert("Wrong username and password!!");
		});
	}
});
