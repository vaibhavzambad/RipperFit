app.config(function($routeProvider) {
	$routeProvider.when("/viewDepartments", {
		templateUrl: "viewDepartments.html",
		controller: "viewDepartmentsCtrl"
	})
})
.controller("viewDepartmentsCtrl", function($scope, $http, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$http.get("/RipperFit/departments/getAllDepartmentsInAnOrganization/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.departments = response.data;
	});
});