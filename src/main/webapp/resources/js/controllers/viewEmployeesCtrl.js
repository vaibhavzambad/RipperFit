app.config(function($routeProvider) {
	$routeProvider.when("/viewEmployees", {
		templateUrl: "viewEmployees.html",
		controller: "viewEmployeesCtrl"
	})
})
.controller("viewEmployeesCtrl", function($scope, $http, StoreCurrentLoggedInUserInformationService) {
	$scope.employeeDetail = StoreCurrentLoggedInUserInformationService.get();
	$http.get("/RipperFit/employee/getEmployeesByOrganizationId/" + $scope.employeeDetail.organization.organizationId)
	.then(function(response) {
		$scope.employee = response.data;
	});
});