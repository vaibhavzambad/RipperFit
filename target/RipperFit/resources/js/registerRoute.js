var app=angular.module("register",["ngRoute"])
.config(function($routeProvider)
		{
	$routeProvider.when("/login",{
		templateUrl:"/RipperFit/login",
		controller:"viewLogInController"
	})
	.when("/signUp",{
		templateUrl:"/RipperFit/signUp",
		controller:"viewSignUpController"
	})
		})
		.controller("viewLogInController",function($scope,$http, user) {
			$scope.loginDetails=angular.copy(user);
				
				$http({
					method: 'POST',
					url: "/RipperFit/employee/login",
					data: $scope.loginDetails,
					headers: {
						'Content-Type': 'application/json'
					}
					})
			.then(function(response) {
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
			}, function () {
				alert("Wrong username and password!!");
			});
				})
				.controller("viewEmployeeController",function($scope,$http)
						{ $http.get("/RipperFit/employee/getEmployee")
					.then(function(response) {
						$scope.employee = response.data;
						console.log("fdf"+$scope.employee);
					});
						})
