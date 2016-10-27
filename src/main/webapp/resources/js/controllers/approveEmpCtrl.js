app.config(function($routeProvider) {
	$routeProvider.when("/approveEmployee/:employeeId", {
		templateUrl: "approveEmployee.html",
		controller: "approveEmpCtrl",
	})
})
.controller("approveEmpCtrl", function($scope, $http, $window) {

	$scope.employeeList = "";
	$http.get("/RipperFit/employee/getEmployeeApprove")
	.then(function(response) {
		$scope.employeeList = response.data;
		var length = $scope.employeeList.length;
		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));
		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
		}

		$scope.forwardEmployee = function(
				$index, employee) {
			$scope.status = "true";
			$scope.employee = {
					"employeeId": employee.employeeId,
					"organization": employee.organization,
					"email": employee.email,
					"firstName": employee.firstName,
					"lastName": employee.lastName,
					"gender": employee.gender,
					"contactNumber": employee.contactNumber,
					"designation": employee.designation,
					"profilePicture": employee.profilePicture,
					"employee": employee.employee,
					"approvalStatus": $scope.status,
					"password" : employee.password
			};

			$http({
				method: 'PUT',
				url: "/RipperFit/employee/updateEmployee",
				data: $scope.employee,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(function() {
				$scope.employeeList.splice($index, 1);
				var length = $scope.employeeList.length;
				var myEl = angular.element(document.querySelector('#table'));
				var myE2 = angular.element(document.querySelector('#msg'));
				if (length == 0) {
					myEl.addClass('hidden');
					myE2.removeClass('hidden');
				} else {
					myE2.addClass('hidden');
				}
			});
		};
	});
});