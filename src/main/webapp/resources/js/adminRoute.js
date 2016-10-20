var app=angular.module("RipperFit",["ngRoute"])

.config(function($routeProvider) {
	$routeProvider.when("/viewRequests",{
		templateUrl:"viewRequests.html",
		controller:"viewRequestsCtrl"
	})
	.when("/viewResources",{
		templateUrl:"viewResources.html",
		controller:"viewResourcesCtrl"
	})
	.when("/viewDesignations",{
		templateUrl:"viewDesignations.html",
		controller:"viewDesignationsCtrl"
	})
	.when("/viewDepartments",{
		templateUrl:"viewDepartments.html",
		controller:"viewDepartmentsCtrl"
	})
	.when("/viewEmployees",{
		templateUrl:"viewEmployees.html",
		controller:"viewEmployeesCtrl"
	})
	.when("/viewOwnRequests/:employeeId",{
		templateUrl:"viewOwnRequests.html",
		controller:"viewEmployeeRequestsCtrl",
	})
	.when("/viewRequests/:employeeId",{
		templateUrl:"viewEmployeeRequests.html",
		controller:"viewEmployeeRequestsCtrl",
	})
	.when("/addPosition",{
		templateUrl:"addPosition.html",
		controller:"addDesignationCtrl"
	})
	.when("/addRequest",{
		templateUrl:"addRequest.html", 
		controller:"addRequestCtrl"
	})
	.when("/viewRequestDetail/:requestId",{
		templateUrl:"viewRequestDetail.html", 
		controller:"viewRequestDetailCtrl"
	})
	.when("/editDepartment/:departmentId",{
		templateUrl:"editDepartment.html",
		controller:"editDepartmentCtrl",
	})
	.when("/addDepartment",{
		templateUrl:"addDepartment.html", 
		controller:"addDepartmentCtrl"
	})
	.when("/myProfile",{
		templateUrl:"myProfile.html", 
		controller:"myProfileCtrl"
	})
	.when("/changePassword",{
		templateUrl:"changePassword.html",
		controller: "changePasswordCtrl"
	})
	.when("/viewRequestsToApprove",{
		templateUrl:"viewRequestToApprove.html",
		controller: "approveRequestCtrl"
	})
	.when("/addResources",{
		templateUrl:"addResources.html",
		controller: "addResourceCtrl"
	})
	.when("/editResource/:resourceId",{
		templateUrl:"editResource.html",
		controller:"editResourceCtrl",
	})
	.when("/editRequest/:requestId",{
		templateUrl:"editRequest.html",
		controller:"editRequestCtrl",
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

					href: '#/viewRequestsToApprove',
					text: 'Action on  Requests'
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

.controller("editRequestCtrl", function($scope, $http, $routeParams, $window, $filter) { 
	$http.get("/RipperFit/request/getRequest/"+$routeParams.requestId+" ")
	.then(function(response) {
		$scope.request = response.data;
		$scope.request.requestDate = $filter('date')(new Date($scope.request.requestDate),'yyyy-MM-dd');	
		$scope.UpdateData=function(request) {		
			$scope.req = {
					"requestId": $scope.request.resourceId,
					"resource": $scope.request.resource,
					"employee": $scope.request.employee,
					"currentApprovalLevel": $scope.request.currentApprovalLevel,
					"priority": $scope.request.priority,
					"status": $scope.request.status,
					"message": $scope.request.message,
					"requestDate": $scope.request.requestDate
			};
			var length=$scope.req.length;
			for(var i=0;i<length;i++) {
				$scope.req[i].requestDate = $filter('date')(new Date($scope.req[i].requestDate),'yyyy-MM-dd');	
			}
			$http({
				method: 'PUT',
				url: "/RipperFit/request/updateRequest",
				data: $scope.request,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href="#/viewOwnRequests/"+$scope.request.employee.employeeId;
			}, function(response) {
				console.log(response.status);
			});
		}
	}), function(response) {
		console.log(response.status);
	};
})

.controller("viewRequestsCtrl", function($scope, $http, $filter) {
	$http.get("/RipperFit/request/getAllRequests")
	.then(function(response) {
		$scope.requests = response.data;
		var length=$scope.requests.length;
		var myEl = angular.element( document.querySelector( '#table' ) );
		var myE2 = angular.element( document.querySelector( '#msg' ) );

		if(length==0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
			for(var i=0;i<length;i++) {

				$scope.requests[i].requestDate = $filter('date')(new Date($scope.requests[i].requestDate),'yyyy-MM-dd');
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
	}), function(response) {
		console.log(response.status);
	};
})

.controller("viewDesignationsCtrl", function($scope, $http) {
	$http.get("/RipperFit/designation/getDesignations")
	.then(function(response) {
		$scope.designation = response.data;
	}), function(response) {
		console.log(response.status);
	};
})

.controller("viewEmployeesCtrl", function($scope, $http) {
	$http.get("/RipperFit/employee/getEmployee")
	.then(function(response) {
		$scope.employee = response.data;
	}), function(response) {
		console.log(response.status);
	};
})

.controller("deleteRequestCtrl", function($scope, $http, $routeParams) {
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
	}), function(response) {
		console.log(response.status);
	};

	/*$http.delete("/RipperFit/comment/deleteComment/"+$routeParams.requestId)
	.then(function(response) {
		console.log(response);
	});
	$http.delete("/RipperFit/request/deleteRequest/"+$routeParams.requestId)
	.then(function(response) {
		console.log(response);
	});*/
})

.controller("addDepartmentCtrl", function($scope, $http, $window, $filter) {
	$scope.addDepartment = function(Department) {
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
		}).then(function() {
			$window.location.href="#/viewDepartments/";
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller("viewDepartmentsCtrl", function($scope, $http) {
	$http.get("/RipperFit/departments/getDepartments")
	.then(function(response) {
		$scope.departments = response.data;
	}), function(response) {
		console.log(response.status);
	};
})

.controller("viewEmployeeRequestsCtrl", function($scope, $http, $routeParams, $window) {
	$http.get("/RipperFit/request/getRequestByEmployee/"+$routeParams.employeeId+" ")
	.then(function(response) {
		$scope.requests = response.data;
		var myEl = angular.element( document.querySelector( '#table' ) );
		var myE2 = angular.element( document.querySelector( '#msg' ) );
		var myE3 = angular.element( document.querySelector( '#input' ) );

		var length=$scope.requests.length;
		if(length==0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
			myE3.addClass('hidden');
		} else {
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
	}), function(response) {
		console.log(response.status);
	};
})

.controller("editDepartmentCtrl", function($scope, $http, $routeParams, $window) { 
	$http.get("/RipperFit/departments/getDepartmentById/"+$routeParams.departmentId+" ")
	.then(function(response) {
		$scope.department = response.data;
		$scope.UpdateData=function(department){
			$scope.dept = {
					"departmentId": $scope.department.departmentId,
					"departmentName": $scope.department.departmentName,
					"organization": $scope.department.organization
			};
			$http({
				method: 'PUT',
				url: "/RipperFit/departments/updateDepartment",
				data: $scope.department,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href="#/viewDepartments/";
			}, function(response) {
				console.log(response.status);
			});
		}
	}) ;
})

.controller("viewResourcesCtrl", function($scope, $http, $filter) { 
	$http.get("/RipperFit/employee/getCurrentEmployeeObject")
	.then(function(response) {
		$scope.employeeDetails = response.data;
		var des=$filter('uppercase')($scope.employeeDetails.designation.designationName);

		var myEl = angular.element( document.querySelector( '#addResources'));
		if(des=="HELPDESK")	{
			myEl.removeClass('hidden');
		} else {
			myEl.addClass('hidden');
		}
		$http.get("/RipperFit/resource/getAllResources")
		.then(function(response) {
			$scope.resources = response.data;

			var myEl = angular.element( document.querySelector( '#table'));
			var myE2 = angular.element( document.querySelector( '#msg' ) );
			var length=$scope.resources.length;

			if(length==0) {
				myEl.addClass('hidden');
				myE2.removeClass('hidden');
			} else {
				myE2.addClass('hidden');
			}
		}), function(response) {
			console.log(response.status);
		}
	});
})

.controller("addResourceCtrl", function($scope, $http, $window) { 
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

.controller("myProfileCtrl", function($scope, $http, $window) { 
	$http.get("/RipperFit/employee/getCurrentEmployeeObject")
	.then(function(response) {
		$scope.employeeDetails = response.data;
		$scope.UpdateData = function(employeeDetails) {
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
			$http({
				method: 'PUT',
				url: "/RipperFit/employee/updateEmployee",
				data: $scope.employee,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.reload("#/myProfile");
			}, function(response) {
				console.log(response.status);
			});
		}
	}) ;
})

.controller("viewRequestDetailCtrl", function($scope, $http, $routeParams, $filter) {
	$http.get("/RipperFit/request/getRequest/"+$routeParams.requestId+" ")
	.then(function(response) {
		$scope.requests = response.data;
		$http.get("/RipperFit/comment/getCommentByRequestId/"+$routeParams.requestId+" ")
		.then(function(response) {
			$scope.comments = response.data;	
			var length=$scope.comments.length;

			var myEl = angular.element( document.querySelector( '#comment'));
			if(length==0) {
				myEl.addClass('hidden');
			}
			for(var i=0;i<length;i++) {
				$scope.comments[i].date = $filter('date')(new Date($scope.comments[i].date),'yyyy-MM-dd');
			}
		})
	}), function(response) {
		console.log(response.status);
	}
})

.controller('getLocationCtrl', function($scope, $http, $window, $filter){
	$scope.clickable = function(requestId) {
		console.log(requestId);
		location.href = '#/viewRequestDetail/'+requestId;
	}
})

.controller('addRequestCtrl', function($scope, $http, $window, $filter){
	$scope.getResources = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/resource/getAllResources",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			$scope.resourceDetails = response.data; 
		}, function(response){ 
			console.log(response.status);
		});
	}
	$scope.getFormDetails = function(request) {
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
		});	
	}
})

.controller("approveRequestCtrl", function($scope, $http, $filter) {
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(function(response) {
		$scope.employee = response.data;
		$http({
			method: 'GET',
			url: "/RipperFit/request/getAllRequestToApprove/"+$scope.employee.employeeId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.requestsToApprove = response.data;
			var length=$scope.requestsToApprove.length;
			var myEl = angular.element( document.querySelector( '#table'));
			var myE2 = angular.element( document.querySelector( '#msg' ) );
			if(length==0) {
				myEl.addClass('hidden');
				myE2.removeClass('hidden');
			} else {
				myE2.addClass('hidden');
				for(var i=0;i<length;i++) {
					$scope.requestsToApprove[i].resourceRequest.requestDate = $filter('date')(new Date($scope.requestsToApprove[i].resourceRequest.requestDate),'yyyy-MM-dd');
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
			}
		})
	}), function(response) {
		console.log(response.status);
	};
})

.controller("forwardRequestCtrl", function($scope, $http, $window) {
	$scope.forwardRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/forwardRequest/"+request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$window.location.reload("#/viewRequestsToApprove");
		}), function(response) {
			console.log(response.status);
		};
	}
})

.controller('addDesignationCtrl', function($scope, $http, $window, $filter){
	$scope.getDepartments = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/departments/getDepartments",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response){
			$scope.departmentDetails = response.data; 
		}, function(response){ 
			console.log(response.status);
		});
	}

	$scope.getDesignations = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then( function(response){
			$scope.designationDetails = response.data; 
		}, function(response){ 
			console.log(response.status);
		});
	}

	$scope.getDesignationDetails = function(position) {
		$scope.level = position.parentDesignation.designationLevel;
		$scope.designationObject = {
				"designationId": "",
				"designationName": position.designation,
				"department": position.department,
				"designationLevel": $scope.level+1
		};
		$http({
			method: 'PUT',
			url: "/RipperFit/designation/updateLevels",
			data : position.parentDesignation,
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
				$window.location.href = "#/viewDesignations";
			}, function(response) {
				console.log(response.status);
			});	
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller('addCommentCtrl', function($scope, $http, $window, $filter){
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(function(response) {
		$scope.employee = response.data;
	}), function(response) {
		console.log(response.status);
	}
	var date = new Date();
	$scope.FromDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	$scope.getCommentDetails=function(commentBox,requests) {
		$scope.comment = {
				"commentId": "",
				"employee": $scope.employee,
				"comments": $scope.commentBox.comments,
				"resourceRequest": $scope.requests,
				"date":$scope.FromDate,
		};
		$http({
			method: 'POST',
			url: "/RipperFit/comment/addComment",
			data: $scope.comment,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewRequestDetail/"+requests.requestId);
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller("editResourceCtrl", function($scope, $http, $routeParams, $window) { 
	$http.get("/RipperFit/resource/getResourceById/"+$routeParams.resourceId+" ")
	.then(function(response) {
		$scope.resource = response.data;
		$scope.UpdateData = function(resource) {
			$scope.resourceDetails = {
					"resourceId": $scope.resource.resourceId,
					"resourceName": $scope.resource.resourceName,
					"finalApprovalLevel": $scope.finalApprovalLevel
			};
			$http({
				method: 'PUT',
				url: "/RipperFit/resource/updateResource",
				data: $scope.resourceDetails,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href="#/viewResources/";
			}, function(response) {
				console.log(response.status);
			});
		}
	}) ;
})

.controller("changePasswordCtrl", function($scope, $http, $window) {
	$scope.changePassword = function(oldPassword, newPassword){
		$http({
			method: 'POST',
			url: "/RipperFit/employee/changePassword?oldPassword="+oldPassword+"&newPassword="+newPassword,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			var message = angular.element( document.querySelector( '#message' ) );
			message.text("Password Changed");
		}, function(response) {
			var message = angular.element( document.querySelector( '#message' ) );
			message.text("Wrong Password");
			console.log(response.status);
		});
	}
})
.controller("updateRequestCtrl", function($scope, $http, $window) {
	$scope.forwardRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/forwardRequest/"+request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewRequestsToApprove");
		}), function(response) {
			console.log(response.status);
		};
	}

	$scope.rejectRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/rejectRequest/"+request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewRequestsToApprove");
		}), function(response) {
			console.log(response.status);
		};
	}
})