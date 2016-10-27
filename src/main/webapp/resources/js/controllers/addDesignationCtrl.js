app.config(function($routeProvider) {
	$routeProvider.when("/addDesignation", {
		templateUrl: "addDesignation.html",
		controller: "addDesignationCtrl"
	})
})
.controller('addDesignationCtrl', function($scope, $http, $window, $filter, StoreCurrentLoggedInUserInformationService) {
	
	var message = angular.element(document.querySelector('#message'));
	var text = "Designation Already Present";
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	$scope.getDepartments = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/departments/getAllDepartmentsInAnOrganization/" + $scope.employee.organization.organizationId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.departmentDetails = response.data;
		});
	}

	$scope.getDesignations = function(department) {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignationsByDepartment/" + department.departmentId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.designationDetails = response.data;
		});
	}

	$scope.getDesignationDetails = function(position) {

		$http.get("/RipperFit/employee/getCurrentEmployeeObject")
		.then(function(response) {
			if (position.parentDesignation === undefined) {

				$scope.level = 1;
				$scope.designationObject = {
						"designationId": "",
						"designationName": position.designation,
						"department": position.department,
						"designationLevel": $scope.level,
						"organization": response.data.organization
				};

				if ($scope.designationObject.designationName === "Admin" || $scope.designationObject.designationName === "Helpdesk") {
					$scope.designationObject.designationLevel = 0;
				}

				$http({
					method: 'POST',
					url: "/RipperFit/designation/addDesignation",
					data: $scope.designationObject,
					headers: {
						'Content-Type': 'application/json'
					}
				})
				.then(function() {
					$window.location.href = "#/viewDesignations";
				}, function(response) {
					if(response.status === 409){
						message.text(text);
					}
				});
			} else {
				$scope.level = position.parentDesignation.designationLevel;
				$scope.level = $scope.level + 1;

				$scope.designationObject = {
						"designationId": "",
						"designationName": position.designation,
						"department": position.department,
						"designationLevel": $scope.level,
						"organization": response.data.organization
				};
				$http({

					method: 'PUT',
					url: "/RipperFit/designation/updateLevels",
					data: position.parentDesignation,
					headers: {
						'Content-Type': 'application/json'
					}
				})
				.then(function() {
					$http({
						method: 'POST',
						url: "/RipperFit/designation/addDesignation",
						data: $scope.designationObject,
						headers: {
							'Content-Type': 'application/json'
						}
					})
					.then(function() {
						$window.location.href = "#/viewDesignations";
					}, function(response) {
						if(response.status === 409){
							message.text(text);
						}
					});
				});
			}
		});
	}
});