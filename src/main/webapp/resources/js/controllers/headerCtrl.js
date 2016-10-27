app.controller("headerCtrl", function($scope, $http, $filter, StoreCurrentLoggedInUserInformationService) {
	$http({
		method: 'GET',
		url: "/RipperFit/employee/getCurrentEmployeeObject",
		headers: {
			'Content-Type': 'application/json'
		}
	})
	.then(function(response) {
		$scope.employee = response.data;
		StoreCurrentLoggedInUserInformationService.set($scope.employee);

		$scope.loggedEmployee = $scope.employee.firstName + " " + $scope.employee.lastName;
		var str = $filter('uppercase')($scope.employee.designation.designationName);
		if($scope.employee.approvalStatus=="false" && str!="ADMIN" && str!="HELPDESK") {
			$scope.status=false;
		} else {
			$scope.status=true;
		}
		if ($scope.employee.designation != null) {

			if (str == "ADMIN") {
				$scope.loggedUser = [{
					href: '#/viewRequests',
					text: 'Requests'
				}, {
					href: '#/viewOwnRequests/' + $scope.employee.employeeId,
					text: 'My requests'
				}, {
					href: '#/viewEmployees',
					text: 'Members'
				}, {
					href: '#/approveEmployee/' + $scope.employee.employeeId,
					text: 'Approve Employee'
				}, {
					href: '#/viewResources',
					text: 'Resources'
				}, {
					href: '#/viewDepartments',
					text: 'Departments'
				}, {
					href: '#/viewDesignations',
					text: 'Designations'
				}];
				$scope.loggedUserList = [{
					href: '#/myProfile',
					text: 'My Profile'
				}];
			} else if (str == "HELPDESK") {
				$scope.loggedUser = [{
					href: '#/viewRequests',
					text: 'Requests'
				}, {
					href: '#/viewResources',
					text: 'Resources'
				}, {
					href: '#/viewDesignationsByHelpdesk',
					text: 'Designations'
				}];
				$scope.loggedUserList = [{
					href: '#/myProfile',
					text: 'My Profile'
				}, ];
			} else {
				$scope.loggedUser = [{
					href: '#/viewRequestsToApprove',
					text: 'Action on  Requests'
				}, {
					href: '#/viewOwnRequests/' + $scope.employee.employeeId,
					text: 'My requests'
				}, {
					href: '#/approveEmployee/' + $scope.employee.employeeId,
					text: 'Approve Employee'
				}];
				$scope.loggedUserList = [{
					href: '#/myProfile',
					text: 'My Profile'
				}];
			}
		}
	});
});