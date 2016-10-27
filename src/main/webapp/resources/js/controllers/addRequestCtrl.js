app.config(function($routeProvider) {
	$routeProvider.when("/addRequest", {
		templateUrl: "addRequest.html",
		controller: "addRequestCtrl"
	})
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
		.then(function() {
					$window.location.href = "#/viewOwnRequests/" + $scope.requestDetails.employee.employeeId;
				});
	}
});