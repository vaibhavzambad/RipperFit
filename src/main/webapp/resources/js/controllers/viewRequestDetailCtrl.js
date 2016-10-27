app.config(function($routeProvider) {
	$routeProvider.when("/viewRequestDetail/:requestId", {
		templateUrl: "viewRequestDetail.html",
		controller: "viewRequestDetailCtrl"
	})
})
.controller("viewRequestDetailCtrl", function($scope, $http, $routeParams, $filter) {
	$http.get("/RipperFit/request/getRequest/" + $routeParams.requestId)
	.then(function(response) {
		$scope.requests = response.data;
		$http.get("/RipperFit/comment/getCommentByRequestId/" + $routeParams.requestId)
		.then(function(response) {
			$scope.comments = response.data;

			var myEl = angular.element(document.querySelector('#comment'));
			var length = $scope.comments.length;

			if (length == 0) {
				myEl.addClass('hidden');
			}
			for (var i = 0; i < length; i++) {
				$scope.comments[i].date = $filter('date')(new Date($scope.comments[i].date), 'yyyy-MM-dd');
			}
		})
	}),
	function(response) {
		if(response.status === 409){
			message.text("Resource Already Present");
		}
	}
});