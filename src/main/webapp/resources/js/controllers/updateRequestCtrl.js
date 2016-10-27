app.controller("updateRequestCtrl", function($scope, $http, $window) {
	 $scope.clickable = function(requestId) {
		   location.href = '#/viewRequestDetail/' + requestId;
		  }
	$scope.forwardRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/forwardRequest/" + request.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		})
		.then(function() {
			$window.location.reload("#/viewRequestsToApprove");
		});
	}

	$scope.rejectRequest = function(request) {
		$http({
			method: 'GET',
			url: "/RipperFit/approve/rejectRequest/" + request.requestId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.reload("#/viewRequestsToApprove");
		});
	}
});