app.config(function($routeProvider) {
	$routeProvider.when("/Home", {
		templateUrl: "dashHome.html",
		controller: "dashBoardHomeCtrl"
	})
	.when("/", {
		templateUrl: "dashHome.html",
		controller: "dashBoardHomeCtrl"
	})
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
		if (deg == "ADMIN") {

		} else if (deg == "HELPDESK") {

			var pending = angular.element(document.querySelector('#pending'));
			var member = angular.element(document.querySelector('#member'));
			var reject = angular.element(document.querySelector('#reject'));
			var complete = angular.element(document.querySelector('#complete'));
			var running = angular.element(document.querySelector('#running'));
			var position = angular.element(document.querySelector('#position'));
			pending.addClass("hidden");
			member.addClass("hidden");
			reject.addClass("hidden");
			complete.addClass("hidden");
			running.addClass("hidden");
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

		$http.get("/RipperFit/request/getRequestByStatus/status=" + $scope.pending+"/organizationName="+$scope.employee.organization.organizationName)
		.then(function(response) {
			$scope.totalPendingRequests = response.data.length;
		});
		$http.get("/RipperFit/request/getRequestByStatus/status=" + $scope.running+"/organizationName="+$scope.employee.organization.organizationName)
		.then(function(response) {
			$scope.totalRunningRequests = response.data.length;
		});
		$http.get("/RipperFit/request/getRequestByStatus/status=" + $scope.completed+"/organization="+$scope.employee.organization.organizationName)
		.then(function(response) {
			$scope.totalCompletedRequests = response.data.length;
		});
		$http.get("/RipperFit/request/getRequestByStatus/status=" + $scope.rejected+"/organization="+$scope.employee.organization.organizationName)
		.then(function(response) {
			$scope.totalRejectedRequests = response.data.length;
		});
	});
});