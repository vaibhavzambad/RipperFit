app.config(function($routeProvider) {
	$routeProvider.when("/viewOwnRequests/:employeeId", {
		templateUrl: "viewOwnRequests.html",
		controller: "viewEmployeeRequestsCtrl",
	})
	.when("/viewRequests/:employeeId", {
		templateUrl: "viewEmployeeRequests.html",
		controller: "viewEmployeeRequestsCtrl",
	})
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
	});
});