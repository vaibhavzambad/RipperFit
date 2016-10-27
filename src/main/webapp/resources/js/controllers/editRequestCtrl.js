app.config(function($routeProvider) {
	$routeProvider.when("/editRequest/:requestId", {
		templateUrl: "editRequest.html",
		controller: "editRequestCtrl",
	})
})
.controller("editRequestCtrl", function($scope, $http, $routeParams, $window, $filter) {
	$http.get("/RipperFit/request/getRequest/" + $routeParams.requestId)
	.then(function(response) {
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
			.then(function() {
				$window.location.href = "#/viewOwnRequests/" + $scope.request.employee.employeeId;
			});
		}
	});
});