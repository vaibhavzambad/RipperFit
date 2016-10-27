app.controller("forwardRequestCtrl", function($scope, $http, $window) {
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
		});
	}
});