app.config(function($routeProvider) {
	$routeProvider.when("/addResources", {
		templateUrl: "addResources.html",
		controller: "addResourceCtrl"
	})
})
.controller("addResourceCtrl", function($scope, $http, $window, StoreCurrentLoggedInUserInformationService) {
	var message = angular.element(document.querySelector('#message'));
	$scope.getResourceDetails = function(resource) {
		$scope.resourceObject = {
				"resourceId": "",
				"resourceName": resource.resourceName,
				"finalApprovalLevel": resource.approvalLevel,
				"organization": StoreCurrentLoggedInUserInformationService.get().organization
		};
		$http({
			method: 'POST',
			url: "/RipperFit/resource/addResource",
			data: $scope.resourceObject,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.href = "#/viewResources";
		}, function(response) {
			if(response.status === 409){
				message.text("Resource Already Present");
			}
		});
	}
});