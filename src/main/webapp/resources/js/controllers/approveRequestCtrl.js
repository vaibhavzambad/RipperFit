app.config(function($routeProvider) {
	$routeProvider.when("/viewRequestsToApprove", {
		templateUrl: "viewRequestToApprove.html",
		controller: "approveRequestCtrl"
	})
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
				$scope.requestsToApprove[i].requestDate = $filter('date')(new Date($scope.requestsToApprove[i].requestDate),'yyyy-MM-dd');
				if ($scope.requestsToApprove[i].status == "completed" || $scope.requestsToApprove[i].status == "success") {
					$scope.requestsToApprove[i].color = "success";
				}
				if ($scope.requestsToApprove[i].status == "running") {
					$scope.requestsToApprove[i].color = "primary";
				}
				if ($scope.requestsToApprove[i].status == "pending") {
					$scope.requestsToApprove[i].color = "primary";
				}
				if ($scope.requestsToApprove[i].status == "rejected") {
					$scope.requestsToApprove[i].color = "danger";
				}
				if ($scope.requestsToApprove[i].status == "approved") {
					$scope.requestsToApprove[i].color = "warning";
				}
			}
		}
	});
});