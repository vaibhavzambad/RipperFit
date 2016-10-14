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
		templateUrl:"/RipperFit/viewEmployee",
		controller:"viewEmployeeController"
	})
	.when("/admin/viewRequest/:employeeId",{
		templateUrl:"/RipperFit/requestemployee/",
		controller:"viewEmployeeRequestController",

	}).when("/admin/addPosition",{
		templateUrl:"/RipperFit/addPosition",
		controller:"addPositionController"

	}).when("/admin/home",{
		templateUrl:"/RipperFit/DBHome/",

	})
		})
		.controller("viewRequestController",function($scope,$http)
				{
			$http.get("/RipperFit/request/viewAllRequests")
			.then(function(response) {
				$scope.requests = response.data;

				var length=$scope.requests.length;
				for(var i=0;i<length;i++)
				{

					if($scope.requests[i].status=="completed")
					{
						$scope.requests[i].color="success";


					}
					if($scope.requests[i].status=="running")
					{
						$scope.requests[i].color="primary";

					}
					if($scope.requests[i].status=="rejected")
					{
						$scope.requests[i].color="danger";

					}
					if($scope.requests[i].status=="approved")
					{
						$scope.requests[i].color="warning";

					}

				}





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
								{ $http.get("/RipperFit/employee/getEmployee")
							.then(function(response) {
								$scope.employee = response.data;
								console.log("fdf"+$scope.employee);
							});
								})
								.controller("viewEmployeeRequestController",function($scope,$http,$routeParams)
										{

									$http.get("/RipperFit/request/viewRequests/"+$routeParams.employeeId+" ")
									.then(function(response) {
										$scope.request = response.data;
										console.log("ddf"+$scope.request[0].status);
									});

										})
										.controller("addPositionController",function($scope,$http,$routeParams)
											{
										

												})