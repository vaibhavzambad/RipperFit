var app=angular.module("RipperFit",["ngRoute"])
.config(function($routeProvider) {
	$routeProvider.when("/viewRequests",{
		templateUrl:"/RipperFit/viewRequests",
		controller:"viewRequestsController"
	}).when("/viewResources",{
		templateUrl:"/RipperFit/viewResources",
		controller:"viewResourcesController"
	}).when("/viewDesignations",{
		templateUrl:"/RipperFit/viewDesignations",
		controller:"viewDesignationsController"
	}).when("/viewEmployees",{
		templateUrl:"/RipperFit/viewEmployees",
		controller:"viewEmployeesController"
	}).when("/viewRequests/:employeeId",{
		templateUrl:"/RipperFit/viewEmployeeRequests/",
		controller:"viewEmployeeRequestsController",
	}).when("/addPosition",{
		templateUrl:"/RipperFit/addPosition",
		controller:"addPositionController"
	}).when("/addRequest",{
		templateUrl:"/RipperFit/addRequest", 
		controller:"addRequestController"
	})
})
.controller("headerCtrl",function($scope,$http, $filter) {
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(function(response) {
		$scope.employee = response.data;
		$scope.loggedEmployee=$scope.employee.firstName +" "+$scope.employee.lastName;
		if($scope.employee.designation != null){
			var str = $filter('uppercase')($scope.employee.designation.designationName);
			if(str == "ADMIN") {
				$scope.loggedUser = [{
					href: '#/viewRequests',
					text: 'Requests'
				}, {
					href: '#/viewEmployees',
					text: 'Members'
				}, {
					href: '#/viewResources',
					text: 'Resources'
				}, {
					href: '#/viewDesignations',
					text: 'Positions'
				}];
				$scope.loggedUserList = [{
					href: '',
					text: 'My Profile'
				}, {
					href: '#/viewRequests/'+$scope.employee.employeeId,
					text: 'My requests'
				}];
			} else if(str == "HELPDESK") {
				$scope.loggedUser = [{
					href: '#/viewRequests',
					text: 'Requests'
				}, {
					href: '#/viewResources',
					text: 'Resources'
				}];
				$scope.loggedUserList = [{
					href: '',
					text: 'My Profile'
				}];
			} else {
				$scope.loggedUser = [{
					href: '#/viewRequests',
					text: 'Requests'
				}];
				$scope.loggedUserList = [{
					href: '',
					text: 'My Profile'
				}, {
					href: '#/viewRequests/'+$scope.employee.employeeId,
					text: 'My requests'
				}];
			}
		}
	}, function(response){
		console.log(response.status);
	});
})
.controller("viewRequestsController",function($scope,$http) {
	$http.get("/RipperFit/request/getAllRequests")
	.then(function(response) {
		$scope.requests = response.data;
		var length=$scope.requests.length;
		for(var i=0;i<length;i++) {
			if($scope.requests[i].status=="completed") {
				$scope.requests[i].color="success";
			}
			if($scope.requests[i].status=="running") {
				$scope.requests[i].color="primary";
			}
			if($scope.requests[i].status=="rejected") {
				$scope.requests[i].color="danger";
			}
			if($scope.requests[i].status=="approved") {
				$scope.requests[i].color="warning";
			}
		}
	});
})
.controller("viewDesignationsController",function($scope,$http) {
	$http.get("/RipperFit/designation/getDesignations")
	.then(function(response) {
		$scope.designation = response.data;
	});
})
.controller("viewEmployeesController",function($scope,$http) {
	$http.get("/RipperFit/employee/getEmployee")
	.then(function(response) {
		$scope.employee = response.data;
	});
})
.controller("viewEmployeeRequestsController",function($scope,$http,$routeParams) {
	$http.get("/RipperFit/request/getRequestByEmployee/"+$routeParams.employeeId+" ")
	.then(function(response) {
		$scope.request = response.data;
	});
})
.controller("viewResourcesController",function($scope,$http) { 
	$http.get("/RipperFit/resource/getAllResources")
	.then(function(response) {
		$scope.resources = response.data;
	})
})
.controller("addRequestController",function($scope,$http) {
});