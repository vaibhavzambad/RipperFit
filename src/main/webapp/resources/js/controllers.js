var signUp = angular.module('signUp',[]);

signUp.factory('StoreService',["$window",function($window){

	function set(data){
		$window.sessionStorage.setItem('userInfo',$window.JSON.stringify(data));
	}function get(){
		return $window.JSON.parse($window.sessionStorage.getItem('userInfo'));
	}
	return {
		set: set,
		get: get
	}
}]);

signUp.controller('socialCtrl',function($scope,$http,$window,StoreService){

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
				console.log(response.data);
				$scope.user = response.data;
				StoreService.set($scope.user);
				$window.location.href="/RipperFit/signUpAfterSocialLogin";
			}, function (){ 
				alert("Something went wrong");
			});
		} else {
		}
	}
});

signUp.controller('formPopulateCtrl',function($scope,StoreService,$http,$window,$filter){

	$scope.user = StoreService.get();

	$scope.getDesignations=function()
	{
		$http({
			method: 'GET',
			url: "/RipperFit/role/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			$scope.designationDetails = response.data; 
		}, function (){ 
			alert("No designations found");
		});
	}
	
	$scope.getAllOrganizations=function()
	{
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			$scope.organizationDetails = response.data; 
		}, function (){ 
			alert("No Organizations found");
		});
	}

	$scope.addUser=function(user) {
		$scope.userDetails=angular.copy(user);
		console.log("org: "+$scope.userDetails.organization);
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.userDetails.email,
				"organization": $scope.userDetails.organization,
				"password": $scope.userDetails.password,
				"firstName": $scope.userDetails.firstName,
				"lastName": $scope.userDetails.lastName,
				"dateOfBirth": $scope.userDetails.dateOfBirth,
				"gender": $scope.userDetails.gender,
				"contactNumber": $scope.userDetails.contactNumber,
				"address": $scope.userDetails.address,
				"designation" : $scope.userDetails.designation,
				"profilePicture" :null
		};

		$http({
			method: 'POST',
			url: "/RipperFit/employee/socialLogin",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (){
			if($scope.userDetails.designation != null){
				var str = $filter('uppercase')($scope.userDetails.designation.designationName);
				if(str == "ADMIN") {
					$window.location.href = '/RipperFit/admin';
				} else if(str == "HELPDESK") {
					$window.location.href = '/RipperFit/helpdesk';
				} else {
					$window.location.href = '/RipperFit/employee';
				}
			}else{
				$window.location.href = '/RipperFit/employee';
			}
		});
	}
});

signUp.controller('signUpCtrl', function($scope, $http, $window, $filter){

	$scope.getDesignations=function()
	{
		$http({
			method: 'GET',
			url: "/RipperFit/role/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			$scope.designationDetails = response.data; 
		}, function (){ 
			alert("No designations found");
		});
	}
	$scope.getAllOrganizations=function()
	{
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			$scope.organizationDetails = response.data; 
		}, function (){ 
			alert("No Organizations found");
		});
	}
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
				"dateOfBirth": $scope.userDetails.dateOfBirth,
				"gender": $scope.userDetails.gender,
				"contactNumber": $scope.userDetails.contactNumber,
				"address": $scope.userDetails.address,
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
		}).then( function (){

			$scope.email = $scope.userDetails.email;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMail",
				data: $scope.email,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then( function (){
				$window.location.href = '/RipperFit/mailController';
			}, function (){ 
				alert("Registration failed!!");
			});
		});
	}

	$scope.login = function(user){

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
			if($scope.employeeDetails.designation != null){
				var str = $filter('uppercase')($scope.employeeDetails.designation.designationName);
				if(str == "ADMIN") {
					$window.location.href = '/RipperFit/admin';
				} else if(str == "HELPDESK") {
					$window.location.href = '/RipperFit/helpdesk';
				} else {
					$window.location.href = '/RipperFit/employee';
				}
			} else {
				$window.location.href = '/RipperFit/employee';
			}
		}, function (){ 
			alert("Wrong username and password!!");
		});
	}
});