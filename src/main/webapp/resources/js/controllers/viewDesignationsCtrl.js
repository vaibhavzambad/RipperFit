app.config(function($routeProvider) {
	$routeProvider.when("/viewDesignations", {
		templateUrl: "viewDesignations.html",
		controller: "viewDesignationsCtrl"	
	})
	.when("/viewDesignationsByHelpdesk", {
		templateUrl: "viewDesignationsByHelpdesk.html",
		controller: "viewDesignationsCtrl"
	})
})
.controller("viewDesignationsCtrl", function($scope, $http, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$http.get("/RipperFit/designation/getDesignations/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.designation = response.data;
	});
});