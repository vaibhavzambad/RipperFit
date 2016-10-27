app.config(function($routeProvider) {
	$routeProvider.when("/viewRequests", {
		templateUrl: "viewRequests.html",
		controller: "viewRequestsCtrl"
	})
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
	});
});