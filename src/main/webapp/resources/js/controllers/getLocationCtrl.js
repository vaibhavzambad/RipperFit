app.controller('getLocationCtrl', function($scope, $http, $window, $filter) {
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
			$window.location.reload("#/viewRequests");		
		});
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
			$window.location.reload("#/viewRequests");
			
		});
	}
});