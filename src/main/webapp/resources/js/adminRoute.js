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
	}).when("/viewRequestDetail/:requestId",{
		templateUrl:"/RipperFit/viewRequestDetail", 
		controller:"viewRequestDetailController"
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
			if($scope.requests[i].status=="completed" ||$scope.requests[i].status=="success") {
				$scope.requests[i].color="success";
			}
			if($scope.requests[i].status=="running") {
				$scope.requests[i].color="primary";
			}
			if($scope.requests[i].status=="pending") {
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
		$scope.requests = response.data;
		var length=$scope.requests.length;
		for(var i=0;i<length;i++) {
			if($scope.requests[i].status=="completed" ||$scope.requests[i].status=="success") {
				$scope.requests[i].color="success";
			}
			if($scope.requests[i].status=="running") {
				$scope.requests[i].color="primary";
			}
			if($scope.requests[i].status=="pending") {
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
.controller("viewResourcesController",function($scope,$http) { 
	$http.get("/RipperFit/resource/getAllResources")
	.then(function(response) {
		console.log("dff");
		$scope.resources = response.data;
	})
})
.controller("addRequestController",function($scope,$http) {
})
.controller("viewRequestDetailController",function($scope,$http,$routeParams) {
	$http.get("/RipperFit/request/getRequest/"+$routeParams.requestId+" ")
	.then(function(response) {
		$scope.requests = response.data;
		console.log($scope.requests);
	})
	$http.get("/RipperFit/comment/getCommentByRequestId/"+$routeParams.requestId+" ")
	.then(function(response) {
		$scope.comments = response.data;
		
	})
});
app.controller('getLocationCtrl', function($scope, $http, $window, $filter){
	
	console.log("dfd");
	$scope.clickable=function(requestId)
	{
		console.log(requestId);
		 location.href = '#/viewRequestDetail/'+requestId;
	}
}
)

app.controller('getResourceCtrl', function($scope, $http, $window, $filter){

	$scope.getResources=function()
	{
		console.log("dfd");
		$http({
			method: 'GET',
			url: "/RipperFit/resource/getAllResources",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			$scope.resourceDetails = response.data; 
		}, function (){ 
			alert("No resource found");
		});
	}
	
	
	$scope.getFormDetails=function(request) {
 $scope.employee="";
 $scope.level="";
		$http({
			method: 'GET',
			url: "/RipperFit/employee/getCurrentEmployeeObject",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.employee = response.data;
			$scope.level=$scope.employee.designation.designationLevel;
			
		
		$scope.requestDetails=angular.copy(request);
		var date = new Date();
		$scope.FromDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);


		$scope.requestDetails = {
				"requestId": "",
				"resource": $scope.requestDetails.resource,
				"employee": $scope.employee,
				"currentApprovalLevel": $scope.level,
				"priority": $scope.requestDetails.priority,
				"status": "pending",
				"message": $scope.requestDetails.des,
				"requestDate":$scope.FromDate,
				
		};
		console.log($scope.requestDetails);
		$http({
			method: 'POST',
			url: "/RipperFit/request/addRequest",
			data: $scope.requestDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			
			$window.location.href="#/viewRequests/";
		}, function(response) {
			console.log(response.status);
		});
		//console.log("requestor"+ $scope.employee);
		})
		
	}
});