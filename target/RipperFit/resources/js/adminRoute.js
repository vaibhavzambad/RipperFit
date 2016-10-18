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
	}).when("/viewDepartments",{
		templateUrl:"/RipperFit/viewDepartments",
		controller:"viewDepartmentsController"
	}).when("/viewEmployees",{
		templateUrl:"/RipperFit/viewEmployees",
		controller:"viewEmployeesController"
	}).when("/viewOwnRequests/:employeeId",{
		templateUrl:"/RipperFit/viewOwnRequests/",
		controller:"viewEmployeeRequestsController",
	}).when("/viewRequests/:employeeId",{
		templateUrl:"/RipperFit/viewEmployeeRequests/",
		controller:"viewEmployeeRequestsController",
	}).when("/addPosition",{
		templateUrl:"/RipperFit/addPosition",
		controller:"addDesignationCtrl"
	}).when("/addRequest",{
		templateUrl:"/RipperFit/addRequest", 
		controller:"addRequestCtrl"
	}).when("/viewRequestDetail/:requestId",{
		templateUrl:"/RipperFit/viewRequestDetail", 
		controller:"viewRequestDetailController"
	}).when("/editDepartment/:departmentId",{
		templateUrl:"/RipperFit/editDepartment/",
		controller:"editDepartmentController",
	}).when("/addDepartment",{
		templateUrl:"/RipperFit/addDepartment", 
		controller:"addDepartmentController"
	}).when("/myProfile",{
		templateUrl:"/RipperFit/myProfile", 
		controller:"myProfileController"
	}).when("/changePassword",{
		templateUrl:"/RipperFit/changePassword",
		controller: ""
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
				}, {
					href: '#/viewDepartments',
					text: 'Departments'
				}];
				$scope.loggedUserList = [{
					href: '#/myProfile',
					text: 'My Profile'
				}, {
					href: '#/viewOwnRequests/'+$scope.employee.employeeId,
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
					href: '#/myProfile',
					text: 'My Profile'
				}];
			} else {
				$scope.loggedUser = [{
					href: '#/viewOwnRequests',
					text: 'Requests'
				}];
				$scope.loggedUserList = [{
					href: '#/myProfile',
					text: 'My Profile'
				}, {
					href: '#/viewOwnRequests/'+$scope.employee.employeeId,
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
.controller("deleteRequestController",function($scope,$http,$routeParams) {
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

	$http.delete("/RipperFit/comment/deleteComment/"+$routeParams.requestId)
	.then(function(response) {
		console.log(response);
	});
	$http.delete("/RipperFit/request/deleteRequest/"+$routeParams.requestId)
	.then(function(response) {
		console.log(response);
	});
})
.controller("addDepartmentController",function($scope,$http, $window, $filter) {
	$scope.addDepartment=function(Department) {
		$scope.departmentDetails = {
				"departmentId": "",
				"departmentName": $scope.Department.departmentName
		};
		$http({
			method: 'POST',
			url: "/RipperFit/departments/addDepartment",
			data: $scope.departmentDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response) {
			$window.location.href="#/viewDepartments/";
		}, function(response) {
			console.log(response.status);
		});
	}
})
.controller("viewDepartmentsController",function($scope,$http) {
	$http.get("/RipperFit/departments/getDepartments")
	.then(function(response) {
		$scope.departments = response.data;
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
.controller("editDepartmentController",function($scope,$http,$routeParams) {
	$http.get("/RipperFit/departments/editDepartment/"+$routeParams.departmentId)
	.then(function(response) {
		$scope.request = response.data;
	}, function(response) {
		console.log(response.status);
	});
})
.controller("viewResourcesController",function($scope,$http) { 
	$http.get("/RipperFit/resource/getAllResources")
	.then(function(response) {
		console.log("dff");
		$scope.resources = response.data;
	})
})
.controller("myProfileController",function($scope,$http) { 
	$http.get("/RipperFit/employee/getCurrentEmployeeObject")
	.then(function(response) {
		$scope.employeeDetails = response.data;
	})
	$scope.getUpdatedDetails=function(user) {
		$scope.email="";
		$scope.userDetails=angular.copy(user);
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.userDetails.email,
				"organization": $scope.userDetails.organization,
				"password": $scope.userDetails.password,
				"gender": $scope.userDetails.gender,
				"contactNumber": $scope.userDetails.contactNumber,
				"designationName" : $scope.userDetails.designationName,
		};
		$http({
			method: 'POST',
			url: "/RipperFit/employee/updateEmployee",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}
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
})
.controller('getLocationCtrl', function($scope, $http, $window, $filter){

	$scope.clickable=function(requestId) {
		console.log(requestId);
		location.href = '#/viewRequestDetail/'+requestId;
	}
})
.controller('addRequestCtrl', function($scope, $http, $window, $filter){

	$scope.getResources=function() {
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
			$http({
				method: 'POST',
				url: "/RipperFit/request/addRequest",
				data: $scope.requestDetails,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function(response) {
				$window.location.href="#/viewOwnRequests/"+$scope.employee.employeeId;
			}, function(response) {
				console.log(response.status);
			});
			//console.log("requestor"+ $scope.employee);
		});	
	}
})
.controller('addDesignationCtrl', function($scope, $http, $window, $filter){

	$scope.getDepartments=function() {
		$http({
			method: 'GET',
			url: "/RipperFit/departments/getDepartments",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			console.log("dfg");
			$scope.departmentDetails = response.data; 
		}, function (){ 
			alert("No departments found");
		});
	}
	
	$scope.getDesignations=function() {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function (response){
			$scope.designationDetails = response.data; 
		}, function (){ 
			alert("No departments found");
		});
	}
	
	/*$scope.getFormDetails=function(request) {
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
		});	
	}*/
});
app.controller('addCommentCtrl', function($scope, $http, $window, $filter){
	
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(function(response) {
		$scope.employee = response.data;
	})
	var date = new Date();
			$scope.FromDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	$scope.getCommentDetails=function(commentBox,requests) {
		console.log("dfd"+$scope.employee);
		$scope.comment = {
				"commentId": "",
				"employee": $scope.employee,
				"comments": $scope.commentBox.comments,
				
				"resourceRequest": $scope.requests,
				"date":$scope.FromDate,
		};
		console.log("dfd"+$scope.commentBox.comments);
		$http({
			method: 'POST',
			url: "/RipperFit/comment/addComment",
			data: $scope.comment,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$window.location.href="#/viewOwnRequests/"+$scope.employee.employeeId;
		}, function(response) {
			console.log(response.status);
		});
	}
	
	
	
	
});


	
