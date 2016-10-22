var app = angular
.module("RipperFit", ["ngRoute"])

.config(function($routeProvider) {
	$routeProvider.when("/viewRequests", {
		templateUrl: "viewRequests.html",
		controller: "viewRequestsCtrl"
	})
	.when("/viewResources", {
		templateUrl: "viewResources.html",
		controller: "viewResourcesCtrl"
	})
	.when("/viewDesignations", {
		templateUrl: "viewDesignations.html",
		controller: "viewDesignationsCtrl"
	}).when("/viewDesignations", {
		templateUrl: "viewDesignationsByHelpdesk.html",
		controller: "viewDesignationsCtrl"
	})
	.when("/viewDepartments", {
		templateUrl: "viewDepartments.html",
		controller: "viewDepartmentsCtrl"
	})
	.when("/viewEmployees", {
		templateUrl: "viewEmployees.html",
		controller: "viewEmployeesCtrl"
	})
	.when("/viewOwnRequests/:employeeId", {
		templateUrl: "viewOwnRequests.html",
		controller: "viewEmployeeRequestsCtrl",
	})
	.when("/viewRequests/:employeeId", {
		templateUrl: "viewEmployeeRequests.html",
		controller: "viewEmployeeRequestsCtrl",
	})
	.when("/addPosition", {
		templateUrl: "addPosition.html",
		controller: "addDesignationCtrl"
	})
	.when("/addRequest", {
		templateUrl: "addRequest.html",
		controller: "addRequestCtrl"
	})
	.when("/viewRequestDetail/:requestId", {
		templateUrl: "viewRequestDetail.html",
		controller: "viewRequestDetailCtrl"
	})
	.when("/editDepartment/:departmentId", {
		templateUrl: "editDepartment.html",
		controller: "editDepartmentCtrl",
	})
	.when("/editDesignation/:designationId", {
		templateUrl: "editDesignation.html",
		controller: "editDesignationCtrl",
	})
	.when("/addDepartment", {
		templateUrl: "addDepartment.html",
		controller: "addDepartmentCtrl"
	})
	.when("/myProfile", {
		templateUrl: "myProfile.html",
		controller: "myProfileCtrl"
	})
	.when("/changePassword", {
		templateUrl: "changePassword.html",
		controller: "changePasswordCtrl"
	})
	.when("/viewRequestsToApprove", {
		templateUrl: "viewRequestToApprove.html",
		controller: "approveRequestCtrl"
	})
	.when("/addResources", {
		templateUrl: "addResources.html",
		controller: "addResourceCtrl"
	})
	.when("/editResource/:resourceId", {
		templateUrl: "editResource.html",
		controller: "editResourceCtrl",
	})
	.when("/editRequest/:requestId", {
		templateUrl: "editRequest.html",
		controller: "editRequestCtrl",
	})
	.when("/approveEmployee/:employeeId", {
		templateUrl: "approveEmployee.html",
		controller: "approveEmpCtrl",
	}).when("/Home", {
		templateUrl: "Home.html",
		controller: "dashBoardHomeCtrl"
	})
	.when("/", {
		templateUrl: "Home.html",
		controller: "dashBoardHomeCtrl"
	})
})

.factory('StoreCurrentLoggedInUserInformationService', ["$window", function($window) {
	function set(data) {
		$window.sessionStorage.setItem(
				'currentLoggedInUser', $window.JSON
				.stringify(data));
	}

	function get() {
		return $window.JSON
		.parse($window.sessionStorage
				.getItem('currentLoggedInUser'));
	}
	return {
		set: set,
		get: get
	}
}
])

.controller("headerCtrl", function($scope, $http, $filter, StoreCurrentLoggedInUserInformationService) {
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	})
	.then(
			function(response) {
				$scope.employee = response.data;
				StoreCurrentLoggedInUserInformationService.set($scope.employee);

				$scope.loggedEmployee = $scope.employee.firstName + " " + $scope.employee.lastName;
				var str = $filter('uppercase')($scope.employee.designation.designationName);
				if($scope.employee.approvalStatus=="false" && str!="ADMIN" && str!="HELPDESK") {
					$scope.status=false;
				} else {
					$scope.status=true;
				}
				if ($scope.employee.designation != null) {

					if (str == "ADMIN") {
						$scope.loggedUser = [{
							href: '#/viewRequests',
							text: 'Requests'
						}, {
							href: '#/viewOwnRequests/' + $scope.employee.employeeId,
							text: 'My requests'
						}, {
							href: '#/viewEmployees',
							text: 'Members'
						}, {
							href: '#/approveEmployee/' + $scope.employee.employeeId,
							text: 'Approve Employee'
						}, {
							href: '#/viewResources',
							text: 'Resources'
						}, {
							href: '#/viewDepartments',
							text: 'Departments'
						}, {
							href: '#/viewDesignations',
							text: 'Positions'
						}];
						$scope.loggedUserList = [{
							href: '#/myProfile',
							text: 'My Profile'
						}];
					} else if (str == "HELPDESK") {
						$scope.loggedUser = [{
							href: '#/viewRequests',
							text: 'Requests'
						}, {
							href: '#/viewResources',
							text: 'Resources'
						}, {
							href: '#/viewDesignations',
							text: 'Positions'
						}];
						$scope.loggedUserList = [{
							href: '#/myProfile',
							text: 'My Profile'
						}, ];
					} else {
						$scope.loggedUser = [{
							href: '#/viewRequestsToApprove',
							text: 'Action on  Requests'
						}, {
							href: '#/viewOwnRequests/' + $scope.employee.employeeId,
							text: 'My requests'
						}, {
							href: '#/approveEmployee/' + $scope.employee.employeeId,
							text: 'Approve Employee'
						}];
						$scope.loggedUserList = [{
							href: '#/myProfile',
							text: 'My Profile'
						}];
					}
				}
			},
			function(response) {
				console.log(response.status);
			});
})

.controller("editRequestCtrl", function($scope, $http, $routeParams, $window, $filter) {
	$http
	.get(
			"/RipperFit/request/getRequest/" + $routeParams.requestId)
			.then(
					function(response) {
						$scope.request = response.data;
						$scope.request.requestDate = $filter('date')(new Date($scope.request.requestDate), 'yyyy-MM-dd');
						$scope.UpdateData = function(request) {
							$scope.req = {
									"requestId": $scope.request.resourceId,
									"resource": $scope.request.resource,
									"employee": $scope.request.employee,
									"currentApprovalLevel": $scope.request.currentApprovalLevel,
									"priority": $scope.request.priority,
									"status": $scope.request.status,
									"message": $scope.request.message,
									"requestDate": $scope.request.requestDate,
									"organization": request.organization
							};
							var length = $scope.req.length;
							for (var i = 0; i < length; i++) {
								$scope.req[i].requestDate = $filter('date')(new Date($scope.req[i].requestDate),'yyyy-MM-dd');
							}
							$http({
								method: 'PUT',
								url: "/RipperFit/request/updateRequest",
								data: $scope.request,
								headers: {
									'Content-Type': 'application/json'
								}
							})
							.then(
									function() {
										$window.location.href = "#/viewOwnRequests/" + $scope.request.employee.employeeId;
									}, function(response) {
										console.log(response.status);
									});
						}
					}),
					function(response) {
		console.log(response.status);
	};
})

.controller("viewRequestsCtrl", function($scope, $http, $filter, StoreCurrentLoggedInUserInformationService) {

	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	var str = $filter('uppercase')($scope.employee.designation.designationName);
	if(str=="HELPDESK") {
		$scope.edit=false;
	}
	if(str=="ADMIN") {
		$scope.degStatus=false;
		$scope.edit=false;
	} else {
		$scope.degStatus=true;
	}
	$http.get("/RipperFit/request/getRequestsByOrganization/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.requests = response.data;
		var length = $scope.requests.length;
		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));

		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
			for (var i = 0; i < length; i++) {
				$scope.requests[i].requestDate = $filter('date')(new Date($scope.requests[i].requestDate), 'yyyy-MM-dd');
				if ($scope.requests[i].status == "completed" || $scope.requests[i].status == "success") {
					$scope.requests[i].color = "success";
				}
				if ($scope.requests[i].status == "running") {
					$scope.requests[i].color = "primary";
				}
				if ($scope.requests[i].status == "pending") {
					$scope.requests[i].color = "primary";
				}
				if ($scope.requests[i].status == "rejected") {
					$scope.requests[i].color = "danger";
				}
				if ($scope.requests[i].status == "approved") {
					$scope.requests[i].color = "warning";
				}
			}
		}
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("viewDesignationsCtrl", function($scope, $http, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$http.get("/RipperFit/designation/getDesignations/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.designation = response.data;
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("viewEmployeesCtrl", function($scope, $http, StoreCurrentLoggedInUserInformationService) {
	$scope.employeeDetail = StoreCurrentLoggedInUserInformationService.get();
	$http.get("/RipperFit/employee/getEmployeesByOrganizationId/" + $scope.employeeDetail.organization.organizationId)
	.then(function(response) {
		$scope.employee = response.data;
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("deleteRequestCtrl", function($scope, $http, $routeParams) {
	$http.get("/RipperFit/request/getAllRequests")
	.then(function(response) {
		$scope.requests = response.data;
		var length = $scope.requests.length;
		for (var i = 0; i < length; i++) {
			if ($scope.requests[i].status == "completed" || $scope.requests[i].status == "success") {
				$scope.requests[i].color = "success";
			}
			if ($scope.requests[i].status == "running") {
				$scope.requests[i].color = "success";
			}
			if ($scope.requests[i].status == "pending") {
				$scope.requests[i].color = "primary";
			}
			if ($scope.requests[i].status == "rejected") {
				$scope.requests[i].color = "danger";
			}
			if ($scope.requests[i].status == "approved") {
				$scope.requests[i].color = "warning";
			}
		}
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("addDepartmentCtrl", function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {
	$scope.addDepartment = function(Department) {
		$scope.employee = StoreCurrentLoggedInUserInformationService.get();
		$scope.departmentDetails = {
				"departmentId": "",
				"departmentName": $scope.Department.departmentName,
				"organization": $scope.employee.organization
		};
		$http({
			method: 'POST',
			url: "/RipperFit/departments/addDepartment",
			data: $scope.departmentDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.href = "#/viewDepartments/";
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller("viewDepartmentsCtrl", function($scope, $http, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$http.get("/RipperFit/departments/getAllDepartmentsInAnOrganization/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.departments = response.data;
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("approveEmpCtrl", function($scope, $http, $window) {
	
	$scope.employeeList = "";
	$http.get("/RipperFit/employee/getEmployeeApprove")
	.then(function(response) {
		$scope.employeeList = response.data;
		console.log("emp list: "+$scope.employeeList);
		var length = $scope.employeeList.length;
		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));
		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
		}

		$scope.forwardEmployee = function(
				$index, employee) {
			console.log(employee.password);
			$scope.status = "true";
			$scope.employee = {
					"employeeId": employee.employeeId,
					"organization": employee.organization,
					"email": employee.email,
					"firstName": employee.firstName,
					"lastName": employee.lastName,
					"gender": employee.gender,
					"contactNumber": employee.contactNumber,
					"designation": employee.designation,
					"profilePicture": employee.profilePicture,
					"employee": employee.employee,
					"approvalStatus": $scope.status,
					"password" : employee.password
			};

			$http({
				method: 'PUT',
				url: "/RipperFit/employee/updateEmployee",
				data: $scope.employee,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(
					function() {

						$scope.employeeList.splice($index, 1);
						var length = $scope.employeeList.length;
						var myEl = angular.element(document.querySelector('#table'));
						var myE2 = angular.element(document.querySelector('#msg'));
						if (length == 0) {
							myEl.addClass('hidden');
							myE2.removeClass('hidden');
						} else {
							myE2.addClass('hidden');
						}
					});
		};
	});
})

.controller("viewEmployeeRequestsCtrl", function($scope, $http, $routeParams, $window) {
	$http.get("/RipperFit/request/getRequestByEmployee/" + $routeParams.employeeId + " ")
	.then(function(response) {
		$scope.requests = response.data;
		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));
		var myE3 = angular.element(document.querySelector('#input'));

		var length = $scope.requests.length;
		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
			myE3.addClass('hidden');
		} else {
			myE2.addClass('hidden');

			for (var i = 0; i < length; i++) {

				if ($scope.requests[i].status == "completed" || $scope.requests[i].status == "success") {
					$scope.requests[i].color = "success";
				}
				if ($scope.requests[i].status == "running") {
					$scope.requests[i].color = "primary";
				}
				if ($scope.requests[i].status == "pending") {
					$scope.requests[i].color = "primary";
				}
				if ($scope.requests[i].status == "rejected") {
					$scope.requests[i].color = "danger";
				}
				if ($scope.requests[i].status == "approved") {
					$scope.requests[i].color = "warning";
				}
			}
		}
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("editDepartmentCtrl", function($scope, $http, $routeParams, $window) {
	$http.get("/RipperFit/departments/getDepartmentById/" + $routeParams.departmentId + " ")
	.then(function(response) {
		$scope.department = response.data;
		$scope.UpdateData = function(department) {
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
			})
			.then(function() {
				$window.location.href = "#/viewDepartments/";
			}, function(response) {
				console.log(response.status);
			});
		}
	});
})

.controller("editDesignationCtrl", function($scope, $http, $routeParams, $window) {

	$http.get("/RipperFit/designation/getDesignationById/" + $routeParams.designationId)
	.then(function(response) {
		$scope.designation = response.data;
		$scope.UpdateData = function(designation) {
			$scope.des = {
					"designationId": $scope.designation.designationId,
					"designationName": $scope.designation.designationName,
					"designationLevel": $scope.designation.designationLevel,
					"department": $scope.designation.department,
					"organization": $scope.designation.organization
			};
			console.log(designation);

			$http({
				method: 'PUT',
				url: "/RipperFit/designation/updateDesignation",
				data: $scope.des,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href = "#/viewDesignations/";
			}, function(response) {
				console.log(response.status);
			});
		}
	});
})

.controller("viewResourcesCtrl", function($scope, $http, $filter, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	var des = $filter('uppercase')(
			$scope.employee.designation.designationName);

	var myEl = angular.element(document
			.querySelector('#addResources'));
	if (des == "HELPDESK") {
		myEl.removeClass('hidden');
	} else {
		myEl.addClass('hidden');
	}
	$http.get("/RipperFit/resource/getResourcesByOrganizationId/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.resources = response.data;

		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));
		var length = $scope.resources.length;

		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
		}
	}),
	function(response) {
		console.log(response.status);
	}
})

.controller("addResourceCtrl", function($scope, $http, $window, StoreCurrentLoggedInUserInformationService) {
	$scope.getResourceDetails = function(resource) {
		$scope.resourceObject = {
				"resourceId": "",
				"resourceName": resource.resourceName,
				"finalApprovalLevel": resource.approvalLevel,
				"organization": StoreCurrentLoggedInUserInformationService.get().organization
		};
		$http({
			method: 'POST',
			url: "/RipperFit/resource/addResource",
			data: $scope.resourceObject,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.href = "#/viewResources";
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller("viewRequestDetailCtrl", function($scope, $http, $routeParams, $filter) {
	$http.get("/RipperFit/request/getRequest/" + $routeParams.requestId)
	.then(function(response) {
		$scope.requests = response.data;
		$http.get("/RipperFit/comment/getCommentByRequestId/" + $routeParams.requestId)
		.then(function(response) {
			$scope.comments = response.data;

			var myEl = angular.element(document.querySelector('#comment'));
			var length = $scope.comments.length;

			if (length == 0) {
				myEl.addClass('hidden');
			}
			for (var i = 0; i < length; i++) {
				$scope.comments[i].date = $filter('date')(new Date($scope.comments[i].date), 'yyyy-MM-dd');
			}
		})
	}),
	function(response) {
		console.log(response.status);
	}
})

.controller('getLocationCtrl', function($scope, $http, $window, $filter) {

	$scope.clickable = function(requestId) {
		location.href = '#/viewRequestDetail/' + requestId;
	}

	$scope.completeRequest = function(request) {
		request.disabled = true;
		$http({
			method: 'GET',
			url: "/RipperFit/approve/completeRequest/" + request.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.href = "#/viewRequests";
		}),
		function(response) {
			console.log(response.status);
		};
	}

	$scope.rejectRequest = function(request) {
		$scope.isDisabled = true;
		$http({
			method: 'GET',
			url: "/RipperFit/approve/rejectRequest/" + request.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.href = "#/viewRequests";
		}),
		function(response) {
			console.log(response.status);
		};
	}
})

.controller("myProfileCtrl", function($scope, $http, $window, StoreCurrentLoggedInUserInformationService) {
	$scope.employeeToReport = "";
	$scope.employeeDetails = StoreCurrentLoggedInUserInformationService.get();
	$scope.status = $scope.employeeDetails.approvalStatus;
	$scope.reportTo = $scope.employeeDetails.employee;
	$scope.password = $scope.employeeDetails.password;

	$http.get("/RipperFit/employee/getEmployeesByOrganizationId/" + $scope.employeeDetails.organization.organizationId)
	.then(function(response) {
		$scope.employeeList = response.data;
		$scope.newList;

		var k = 1;
		var length = $scope.employeeList.length;
		for (var i = 0; i < $scope.employeeList.length;) {

			if ($scope.employeeDetails.email == $scope.employeeList[i].email || $scope.employeeList[i].designation.designationName == "Helpdesk") {
				$scope.employeeList.splice(i, 1);
				k++;
			} else {
				i++;
			}
		}
	})
	$scope.reportId = function(emp) {
		var email = emp.split('(');
		var email = email[0];
		$http({
			method: 'GET',
			url: "/RipperFit/employee/getEmployeeByEmail?email=" + email,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.employeeToReport = response.data;
		});
	}
	$scope.UpdateData = function(employeeDetails) {
		
		if($scope.employeeToReport === "") {
			$scope.employeeToReport = $scope.employeeDetails.employee;
		}
		
		$scope.employee = {
				"employeeId": $scope.employeeDetails.employeeId,
				"organization": $scope.employeeDetails.organization,
				"email": $scope.employeeDetails.email,
				"password": $scope.password,
				"firstName": $scope.employeeDetails.firstName,
				"lastName": $scope.employeeDetails.lastName,
				"gender": $scope.employeeDetails.gender,
				"contactNumber": $scope.employeeDetails.contactNumber,
				"designation": $scope.employeeDetails.designation,
				"profilePicture": $scope.employeeDetails.profilePicture,
				"employee": $scope.employeeToReport,
				"approvalStatus":$scope.status
		};
		$http({
			method: 'PUT',
			url: "/RipperFit/employee/updateEmployee",
			data: $scope.employee,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			StoreCurrentLoggedInUserInformationService.set($scope.employee);
			$window.location.reload("#/myProfile");
		},
		function(response) {
			console.log(response.status);
		});
	}
})

.controller('addRequestCtrl', function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {

	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$scope.getResources = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/resource/getResourcesByOrganizationId/" + $scope.employee.organization.organizationId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.resourceDetails = response.data;
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.getFormDetails = function(request) {
		$scope.level = $scope.employee.designation.designationLevel;
		$scope.requestDetails = angular.copy(request);
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
				"requestDate": $scope.FromDate,
				"organization": $scope.employee.organization
		};
		$http({
			method: 'POST',
			url: "/RipperFit/request/addRequest",
			data: $scope.requestDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		})
		.then(
				function() {
					$window.location.href = "#/viewOwnRequests/" + $scope.requestDetails.employee.employeeId;
				},
				function(response) {
					console.log(response.status);
				});
	}
})

.controller("approveRequestCtrl", function($scope, $http, $filter, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService .get();
	$http({
		method: 'GET',
		url: "/RipperFit/request/getAllRequestToApprove/" + $scope.employee.employeeId,
		headers: {
			'Content-Type': 'application/json'
		}
	})
	.then(function(response) {
		$scope.requestsToApprove = response.data;
		var length = $scope.requestsToApprove.length;
		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));
		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
			for (var i = 0; i < length; i++) {
				$scope.requestsToApprove[i].resourceRequest.requestDate = $filter('date')(new Date($scope.requestsToApprove[i].resourceRequest.requestDate),'yyyy-MM-dd');
				if ($scope.requestsToApprove[i].resourceRequest.status == "completed" || $scope.requestsToApprove[i].resourceRequest.status == "success") {
					$scope.requestsToApprove[i].color = "success";
				}
				if ($scope.requestsToApprove[i].resourceRequest.status == "running") {
					$scope.requestsToApprove[i].color = "primary";
				}
				if ($scope.requestsToApprove[i].resourceRequest.status == "pending") {
					$scope.requestsToApprove[i].color = "primary";
				}
				if ($scope.requestsToApprove[i].resourceRequest.status == "rejected") {
					$scope.requestsToApprove[i].color = "danger";
				}
				if ($scope.requestsToApprove[i].resourceRequest.status == "approved") {
					$scope.requestsToApprove[i].color = "warning";
				}
			}
		}
	}),
	function(response) {
		console.log(response.status);
	};
})

.controller("forwardRequestCtrl", function($scope, $http, $window) {
	$scope.forwardRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/forwardRequest/" + request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		})
		.then(function(response) {
			$window.location.href = "#/viewRequestsToApprove";
		}),
		function(response) {
			console.log(response.status);
		};
	}
})

.controller('addDesignationCtrl', function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {

	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$scope.getDepartments = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/departments/getAllDepartmentsInAnOrganization/" + $scope.employee.organization.organizationId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.departmentDetails = response.data;
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.getDesignations = function(department) {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignationsByDepartment/" + department.departmentId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.designationDetails = response.data;
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.getDesignationDetails = function(position) {

		$http.get("/RipperFit/employee/getCurrentEmployeeObject")
		.then(function(response) {
			console.log(response);
			if (position.parentDesignation === undefined) {

				$scope.level = 1;
				$scope.designationObject = {
						"designationId": "",
						"designationName": position.designation,
						"department": position.department,
						"designationLevel": $scope.level,
						"organization": response.data.organization
				};

				if ($scope.designationObject.designationName === "Admin" || $scope.designationObject.designationName === "Helpdesk") {
					$scope.designationObject.designationLevel = 0;
				}

				$http({
					method: 'POST',
					url: "/RipperFit/designation/addDesignation",
					data: $scope.designationObject,
					headers: {
						'Content-Type': 'application/json'
					}
				})
				.then(function() {
					$window.location.href = "#/viewDesignations";
				}, function(response) {
					console.log("a" + response.status);
				});
			} else {
				$scope.level = position.parentDesignation.designationLevel;
				$scope.level = $scope.level + 1;

				$scope.designationObject = {
						"designationId": "",
						"designationName": position.designation,
						"department": position.department,
						"designationLevel": $scope.level,
						"organization": response.data.organization
				};
				$http({

					method: 'PUT',
					url: "/RipperFit/designation/updateLevels",
					data: position.parentDesignation,
					headers: {
						'Content-Type': 'application/json'
					}
				})
				.then(function() {
					console.log($scope.designationObject);
					$http({
						method: 'POST',
						url: "/RipperFit/designation/addDesignation",
						data: $scope.designationObject,
						headers: {
							'Content-Type': 'application/json'
						}
					})
					.then(function() {
						$window.location.href = "#/viewDesignations";
					}, function(response) {
						console.log("a" + response.status);
					});
				}, function(response) {
					console.log("b" + response.status);
				});
			}
		});
	}
})

.controller('addCommentCtrl', function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	var date = new Date();
	$scope.FromDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	$scope.getCommentDetails = function(commentBox, requests) {
		$scope.comment = {
				"commentId": "",
				"employee": $scope.employee,
				"comments": $scope.commentBox.comments,
				"resourceRequest": $scope.requests,
				"date": $scope.FromDate,
		};
		$http({
			method: 'POST',
			url: "/RipperFit/comment/addComment",
			data: $scope.comment,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewRequestDetail/" + requests.requestId);
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller("editResourceCtrl", function($scope, $http, $routeParams, $window) {
	$http.get("/RipperFit/resource/getResourceById/" + $routeParams.resourceId)
	.then(function(response) {
		$scope.resource = response.data;
		$scope.UpdateData = function(resource) {
			$scope.resourceDetails = {
					"resourceId": $scope.resource.resourceId,
					"resourceName": $scope.resource.resourceName,
					"finalApprovalLevel": $scope.resource.finalApprovalLevel,
					"organization": $scope.resource.organization
			};
			$http({
				method: 'PUT',
				url: "/RipperFit/resource/updateResource",
				data: $scope.resourceDetails,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(function() {
				$window.location.href = "#/viewResources/";
			}, function(response) {
				console.log(response.status);
			});
		}
	});
})

.controller("changePasswordCtrl", function($scope, $http, $window, StoreCurrentLoggedInUserInformationService) {
	$scope.changePassword = function(user) {
		var message = angular.element(document.querySelector('#message'));
		if (user.new_password == user.confirm_password) {
			$http({
				method: 'POST',
				url: "/RipperFit/employee/changePassword?oldPassword=" + user.old_password + "&newPassword=" + user.new_password,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(function(response) {
				$scope.employee = StoreCurrentLoggedInUserInformationService.get();
				$scope.employee.password = user.new_password;
				StoreCurrentLoggedInUserInformationService.set($scope.employee);

				message.text("Password Changed");
			}, function(response) {
				message.text("Wrong Password");
				console.log(response.status);
			});
		} else {
			message.text("Password and confirm password do not match");
		}
	}
})

.controller("updateRequestCtrl", function($scope, $http, $window) {
	 $scope.clickable = function(requestId) {
		 console.log(requestId);
		   location.href = '#/viewRequestDetail/' + requestId;
		  }
	$scope.forwardRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/forwardRequest/" + request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		})
		.then(function() {
			$window.location.reload("#/viewRequestsToApprove");
		}, function(response) {
			console.log(response.status);
		});
	}

	$scope.rejectRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/rejectRequest/" + request.resourceRequest.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewRequestsToApprove");
		}, function(response) {
			console.log(response.status);
		});
	}
})

.controller("dashBoardHomeCtrl", function($scope, $http, $routeParams, $window, $filter) {

	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(function(response) {
		$scope.employee = response.data;
		var deg = $filter('uppercase')($scope.employee.designation.designationName);
		console.log(deg);
		if (deg == "ADMIN") {

		} else if (deg == "HELPDESK") {

			var pending = angular.element(document.querySelector('#pending'));
			var member = angular.element(document.querySelector('#member'));
			var reject = angular.element(document.querySelector('#reject'));
			var complete = angular.element(document.querySelector('#complete'));
			var running = angular.element(document.querySelector('#running'));
			//  var request = angular.element(document.querySelector('#request'));
			var position = angular.element(document.querySelector('#position'));
			pending.addClass("hidden");
			member.addClass("hidden");
			reject.addClass("hidden");
			complete.addClass("hidden");
			running.addClass("hidden");
			// request.addClass("hidden");
			position.addClass("hidden");

		} else {
			var resource = angular.element(document.querySelector('#resource'));
			var member = angular.element(document.querySelector('#member'));
			var requestAll = angular.element(document.querySelector('#requestAll'));
			var position = angular.element(document.querySelector('#position'));
			resource.addClass("hidden");
			member.addClass("hidden");
			requestAll.addClass("hidden");
			position.addClass("hidden");
		}

		$http.get("/RipperFit/request/getRequestByEmployee/" + $scope.employee.employeeId + " ")
		.then(function(response) {
			$scope.request = response.data;
			$scope.request = $scope.request.length;
		});
		$http.get("/RipperFit/request/getRequestsByOrganization/" + $scope.employee.organization.organizationId)
		.then(function(response) {
			$scope.requestAll = response.data;
			$scope.requestAll = response.data.length;
		});

		$http.get("/RipperFit/designation/getDesignations/" + $scope.employee.organization.organizationId + " ")
		.then(function(response) {
			$scope.designations = response.data;
			$scope.totalDesignations = $scope.designations.length;
		});
		$http.get("/RipperFit/resource/getResourcesByOrganizationId/" + $scope.employee.organization.organizationId)
		.then(function(response) {
			$scope.totalResources = response.data.length;
		});

		$http.get("/RipperFit/employee/getEmployeesByOrganizationId/" + $scope.employee.organization.organizationId)
		.then(function(response) {
			$scope.Totalemployees = response.data.length;
		});

		$scope.pending = "pending";
		$scope.running = "running";
		$scope.completed = "completed";
		$scope.rejected = "rejected";

		$http.get("/RipperFit/request/getRequestByStatus/" + $scope.pending)
		.then(function(response) {
			$scope.totalPendingRequests = response.data.length;
		});
		$http.get("/RipperFit/request/getRequestByStatus/" + $scope.running)
		.then(function(response) {
			$scope.totalRunningRequests = response.data.length;
		});
		$http.get("/RipperFit/request/getRequestByStatus/" + $scope.completed)
		.then(function(response) {
			$scope.totalCompletedRequests = response.data.length;
		});
		$http.get("/RipperFit/request/getRequestByStatus/" + $scope.rejected).then(function(response) {
			$scope.totalRejectedRequests = response.data.length;
		});
	});
})