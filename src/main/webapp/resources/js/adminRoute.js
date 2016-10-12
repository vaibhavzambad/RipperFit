var app=angular.module("admin",["ngRoute"])
.config(function($routeProvider)
		{
	$routeProvider.when("/admin/viewRequest",{
		templateUrl:"/RipperFit/request",
		controller:"viewRequestController"
	})
	.when("/admin/viewRole",{
		templateUrl:"/RipperFit/role",
		controller:"viewRoleController"
	})
	.when("/admin/viewEmployee",{
		templateUrl:"/RipperFit/employee",
		controller:"viewEmployeeController"
	})
	.when("/admin/viewRequest/:employeeeId",{
		templateUrl:"/RipperFit/requestemployee/",
		controller:"viewEmployeeRequestController",
		
	})
		})
		.controller("viewRequestController",function($scope,$http)
				{
			$http.get("/RipperFit/request/viewAllRequests")
			.then(function(response) {
				$scope.requests = response.data;
			});

				})
				.controller("viewRoleController",function($scope,$http)
						{$http.get("/RipperFit/role/getDesignations")
					.then(function(response) {
						$scope.designation = response.data;
						console.log($scope.designation);
					});
						})
						.controller("viewEmployeeController",function($scope,$http)
								{$http.get("/RipperFit/employee/getEmployee")
							.then(function(response) {
								$scope.employee = response.data;
								console.log($scope.employee);
							});
								})
								.controller("viewEmployeeRequestController",function($scope, $routeParams)
								{
									
							      console.log($routeParams.employeeId); 
							})