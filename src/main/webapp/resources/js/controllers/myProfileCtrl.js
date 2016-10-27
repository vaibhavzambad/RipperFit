app.config(function($routeProvider) {
	$routeProvider.when("/myProfile", {
		templateUrl: "myProfile.html",
		controller: "myProfileCtrl"
	})
})
.controller("myProfileCtrl", function($scope, $http, $window, StoreCurrentLoggedInUserInformationService) {
	$scope.employeeToReport = "";
	$scope.employeeDetails = StoreCurrentLoggedInUserInformationService.get();
	$scope.status = $scope.employeeDetails.approvalStatus;
	$scope.reportTo = $scope.employeeDetails.employee;
	$scope.password = $scope.employeeDetails.password;

	$http.get("/RipperFit/employee/getEmployeesByOrganizationId/" + $scope.employeeDetails.organization.organizationId)
	.then(function(response) {
		$scope.employeeList = response.data;
		$scope.newList;

		var k = 1;
		var length = $scope.employeeList.length;
		for (var i = 0; i < $scope.employeeList.length;) {

			if ($scope.employeeDetails.email == $scope.employeeList[i].email || $scope.employeeList[i].designation.designationName == "Helpdesk") {
				$scope.employeeList.splice(i, 1);
				k++;
			} else {
				i++;
			}
		}
	})
	$scope.reportId = function(emp) {
		var email = emp.split('(');
		var email = email[0];
		$http({
			method: 'GET',
			url: "/RipperFit/employee/getEmployeeByEmail?email=" + email,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.employeeToReport = response.data;
		});
	}
	$scope.UpdateData = function(employeeDetails) {
		
		if($scope.employeeToReport === "") {
			$scope.employeeToReport = $scope.employeeDetails.employee;
		}
		
		$scope.employee = {
				"employeeId": $scope.employeeDetails.employeeId,
				"organization": $scope.employeeDetails.organization,
				"email": $scope.employeeDetails.email,
				"password": $scope.password,
				"firstName": $scope.employeeDetails.firstName,
				"lastName": $scope.employeeDetails.lastName,
				"gender": $scope.employeeDetails.gender,
				"contactNumber": $scope.employeeDetails.contactNumber,
				"designation": $scope.employeeDetails.designation,
				"profilePicture": $scope.employeeDetails.profilePicture,
				"employee": $scope.employeeToReport,
				"approvalStatus":$scope.status
		};
		$http({
			method: 'PUT',
			url: "/RipperFit/employee/updateEmployee",
			data: $scope.employee,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function() {
			StoreCurrentLoggedInUserInformationService.set($scope.employee);
			$window.location.reload("#/myProfile");
		});
	}
});