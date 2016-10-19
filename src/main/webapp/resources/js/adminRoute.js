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
	}).when("/viewRequestsToApprove",{
		templateUrl:"/RipperFit/approveRequests",
		controller: "approveRequestController"
	}).when("/addResources",{
		templateUrl:"/RipperFit/addResources",
		controller: "addResourceController"
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
			//var myEl = angular.element(document.querySelector('#button'));
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
				//myE1.addClass("hidden");
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
					text: 'Requests',
				}, {
					href: '#/viewRequestsToApprove',
					text: 'Approve Requests'
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
.controller("forwaRe")
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
.controller("viewEmployeeRequestsController",function($scope,$http,$routeParams,$window) {

	$http.get("/RipperFit/request/getRequestByEmployee/"+$routeParams.employeeId+" ")
	.then(function(response) {

		$scope.requests = response.data;

		var myEl = angular.element( document.querySelector( '#table' ) );
		var myE2 = angular.element( document.querySelector( '#msg' ) );
		var length=$scope.requests.length;
		if(length==0)
		{
			myEl.addClass('hidden');
			myE2.removeClass('hidden');

		}else
		{

			myE2.addClass('hidden');

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
		}
	}), function(error){
		console.log("Error");
	};
})
.controller("editDepartmentController",function($scope,$http,$routeParams, $window) { 
	$http.get("/RipperFit/departments/getDepartmentById/"+$routeParams.departmentId+" ")
	.then(function(response) {
		$scope.department = response.data;
		console.log($scope.department);

		$scope.UpdateData=function(department){
			console.log($scope.department);
			$scope.dept = {
					"departmentId": $scope.department.departmentId,
					"departmentName": $scope.department.departmentName,
					"organization": $scope.department.organization
			};
			console.log($scope.dept);
			$http({
				method: 'PUT',
				url: "/RipperFit/departments/updateDepartment",
				data: $scope.department,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then( function (response) {
				$window.location.href="#/viewDepartments/";
			}, function(response) {
				console.log(response.status);
			});
		}
	}) ;
})
.controller("viewResourcesController",function($scope,$http) { 
	$http.get("/RipperFit/resource/getAllResources")
	.then(function(response) {
		$scope.resources = response.data;
	})
})
.controller("addResourceController",function($scope,$http, $window) { 

	$scope.getResourceDetails=function(resource) {
		$scope.resourceObject = {
				"resourceId": "",
				"resourceName": resource.resourceName,
				"finalApprovalLevel": resource.approvalLevel
		};
		$http({
			method: 'POST',
			url: "/RipperFit/resource/addResource",
			data : $scope.resourceObject,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewResources");
		}, function(response) {
			console.log(response.status);
		});	
	}
})
.controller("myProfileController",function($scope,$http) { 
	$http.get("/RipperFit/employee/getCurrentEmployeeObject")
	.then(function(response) {
		$scope.employeeDetails = response.data;
		console.log($scope.employeeDetails);

		$scope.UpdateData=function(employeeDetails){
			console.log($scope.employeeDetails);
			$scope.employee = {
					"employeeId": $scope.employeeDetails.employeeId,
					"organization": $scope.employeeDetails.organization,
					"email": $scope.employeeDetails.email,
					"firstName": $scope.employeeDetails.firstName,
					"lastName": $scope.employeeDetails.lastName,
					"gender": $scope.employeeDetails.gender,
					"contactNumber": $scope.employeeDetails.contactNumber,
					"designation" : $scope.employeeDetails.designation,
					"profilePicture" :$scope.employeeDetails.profilePicture
			};
			console.log($scope.employee);
			$http({
				method: 'PUT',
				url: "/RipperFit/employee/updateEmployee",
				data: $scope.employee,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then( function (response) {
				console.log("success    "+response.data);
			}, function(response) {
				console.log(response.status);
			});
		}
	}) ;
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
			}).then(function() {
				$window.location.href="#/viewOwnRequests/"+$scope.requestDetails.employee.employeeId;
			}, function(response) {
				console.log(response.status);
			});
			//console.log("requestor"+ $scope.employee);
		});	
	}
})
.controller("approveRequestController",function($scope,$http) {
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(function(response) {
		$scope.employee = response.data;
		console.log("hello : "+$scope.employee.employeeId);
		$http({
			method: 'GET',
			url: "/RipperFit/request/getAllRequestToApprove/"+$scope.employee.employeeId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.requestsToApprove = response.data;
			var length=$scope.requestsToApprove.length;
			for(var i=0;i<length;i++) {
				if($scope.requestsToApprove[i].resourceRequest.status=="completed" ||$scope.requestsToApprove[i].resourceRequest.status=="success") {
					$scope.requestsToApprove[i].color="success";
				}
				if($scope.requestsToApprove[i].resourceRequest.status=="running") {
					$scope.requestsToApprove[i].color="primary";
				}
				if($scope.requestsToApprove[i].resourceRequest.status=="pending") {
					$scope.requestsToApprove[i].color="primary";
				}
				if($scope.requestsToApprove[i].resourceRequest.status=="rejected") {
					$scope.requestsToApprove[i].color="danger";
				}
				if($scope.requestsToApprove[i].resourceRequest.status=="approved") {
					$scope.requestsToApprove[i].color="warning";
				}
			}
		})
	});
}).controller("forwardRequestCtrl",function($scope,$http,$window) {
	console.log("enter");

	$scope.forwardRequest=function(request)
	{
		console.log("t"+request.resourceRequest.requestId);
		$http({
			method: 'GET',
			url: "/RipperFit/approve/forwardRequest/"+request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$window.location.reload("#/viewRequestsToApprove");
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

	$scope.getDesignationDetails=function(position) {

		$scope.level = position.childDesignation.designationLevel;
		$scope.designationObject = {
				"designationId": "",
				"designationName": position.designation,
				"department": position.department,
				"designationLevel": $scope.level
		};
		$http({
			method: 'PUT',
			url: "/RipperFit/designation/updateLevels",
			data : $scope.level,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {

			$http({
				method: 'POST',
				url: "/RipperFit/designation/addDesignation",
				data : $scope.designationObject,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.reload("#/viewDesignations");
			}, function(response) {
				console.log(response.status);
			});	
		}, function(response) {
			console.log(response.status);
		});
	}
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
			$window.location.reload("#/viewRequestDetail/"+requests.requestId);
		}, function(response) {
			console.log(response.status);
		});
	}

});