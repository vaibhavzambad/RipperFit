app.config(function($routeProvider) {
	$routeProvider.when("/editResource/:resourceId", {
		templateUrl: "editResource.html",
		controller: "editResourceCtrl",
	})
})
.controller("editResourceCtrl", function($scope, $http, $routeParams, $window) {
	$http.get("/RipperFit/resource/getResourceById/" + $routeParams.resourceId)
	.then(function(response) {
		$scope.resource = response.data;
		$scope.UpdateData = function(resource) {
			$scope.resourceDetails = {
					"resourceId": $scope.resource.resourceId,
					"resourceName": $scope.resource.resourceName,
					"finalApprovalLevel": $scope.resource.finalApprovalLevel,
					"organization": $scope.resource.organization
			};
			$http({
				method: 'PUT',
				url: "/RipperFit/resource/updateResource",
				data: $scope.resourceDetails,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(function() {
				$window.location.href = "#/viewResources/";
			});
		}
	});
});