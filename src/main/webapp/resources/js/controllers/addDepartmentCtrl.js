app.config(function($routeProvider) {
	$routeProvider.when("/addDepartment", {
		templateUrl: "addDepartment.html",
		controller: "addDepartmentCtrl"
	})
})
.controller("addDepartmentCtrl", function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {
	$scope.addDepartment = function(Department) {
		var message = angular.element(document.querySelector('#message'));
		$scope.employee = StoreCurrentLoggedInUserInformationService.get();
		$scope.departmentDetails = {
				"departmentId": "",
				"departmentName": $scope.Department.departmentName,
				"organization": $scope.employee.organization
		};
		$http({
			method: 'POST',
			url: "/RipperFit/departments/addDepartment",
			data: $scope.departmentDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			$window.location.href = "#/viewDepartments/";
		}, function(response) {
			if(response.status === 409){
				message.text("Department Already Present");
			}
		});
	}
});