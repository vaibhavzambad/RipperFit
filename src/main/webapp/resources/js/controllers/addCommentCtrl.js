app.controller('addCommentCtrl', function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {
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
		});
	}
});