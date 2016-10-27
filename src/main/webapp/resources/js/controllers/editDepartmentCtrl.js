app.config(function($routeProvider) {
	$routeProvider.when("/editDepartment/:departmentId", {
		templateUrl: "editDepartment.html",
		controller: "editDepartmentCtrl",
	})
})
.controller("editDepartmentCtrl", function($scope, $http, $routeParams, $window) {
	$http.get("/RipperFit/departments/getDepartmentById/" + $routeParams.departmentId + " ")
	.then(function(response) {
		$scope.department = response.data;
		$scope.UpdateData = function(department) {
			$scope.dept = {
					"departmentId": $scope.department.departmentId,
					"departmentName": $scope.department.departmentName,
					"organization": $scope.department.organization
			};
			$http({
				method: 'PUT',
				url: "/RipperFit/departments/updateDepartment",
				data: $scope.department,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(function() {
				$window.location.href = "#/viewDepartments/";
			});
		}
	});
});