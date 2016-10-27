app.controller('socialSignUpCtrl', function($scope, StoreService, $http, $window, $filter) {

	$scope.user = StoreService.get();
	$scope.getOrganizations = function() {
		$http({
			method: 'GET',
			url: "/RipperFit/organization/getAllOrganizations",
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.organizationDetails = response.data; 
		});
	}

	$scope.getDesignations = function(organization) {
		$http({
			method: 'GET',
			url: "/RipperFit/designation/getDesignations/"+organization.organizationId,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.designationDetails = response.data; 
		});
	}

	$scope.addUser = function(user) {
		$scope.userDetails = {
				"employeeId": "",
				"email": $scope.user.email,
				"organization": $scope.user.organization,
				"password": null,
				"firstName": $scope.user.firstName,
				"lastName": $scope.user.lastName,
				"gender": $scope.user.gender,
				"contactNumber": $scope.user.phoneNumber,
				"designation" : $scope.user.designation,
				"profilePicture" :$scope.user.profilePicture
		};
		$http({
			method: 'POST',
			url: "/RipperFit/employee/socialLogin",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.login = response.data;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMail",
				data: $scope.login,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href = 'dashboard.html';
			});
		});

	}

	$scope.createOrganization = function() {
		var flag=true;
		var org = angular.element( document.querySelector( '#inputOrganization' ) );
		org.removeClass('hidden');
		var organization = angular.element( document.querySelector( '#selectOrganization' ) );
		organization.addClass('hidden');

		$scope.addUser = function(user) {
			$scope.newOrganization={
					"organizationId": "",
					"organizationName": $scope.user.organization
			}
			$http({
				method: 'POST',
				url: "/RipperFit/organization/addOrganization",
				data: $scope.newOrganization,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function(response) {
				$http({
					method: 'GET',
					url: "/RipperFit/organization/getOrganizationByName/"+$scope.newOrganization.organizationName,
					headers: {
						'Content-Type': 'application/json'
					}
				}).then(function(response) {
					$scope.organization = response.data; 
					$scope.departmentDetails = {
							"departmentId": "",
							"departmentName":"Admin",
							"organization" : $scope.organization
					};
					$http({
						method: 'POST',
						url: "/RipperFit/departments/addDepartment",
						data: $scope.departmentDetails,
						headers: {
							'Content-Type': 'application/json'
						}
					}).then(function(response) {

						$http({
							method: 'GET',
							url: "/RipperFit/departments/getDepartmentByName/"+'Admin'+"/"+$scope.organization.organizationId,
							headers: {
								'Content-Type': 'application/json'
							}
						}).then(function(response) {
							$scope.department = response.data; 

							$scope.designationDetails = {
									"designationId": "",
									"designationName": "Admin",
									"department": $scope.department,
									"designationLevel":0,
									"organization" : $scope.organization
							};
							$http({
								method: 'POST',
								url: "/RipperFit/designation/addDesignation",
								data: $scope.designationDetails,
								headers: {
									'Content-Type': 'application/json'
								}
							}).then(function(response) {

								$http({
									method: 'GET',
									url: "/RipperFit/designation/getDesignationByName/"+'Admin'+"/"+$scope.organization.organizationId,
									headers: {
										'Content-Type': 'application/json'
									}
								}).then(function(response) {
									$scope.designation= response.data; 

									if(flag==true)
									{
										$scope.user.organization=$scope.organization;
										$scope.user.designation=$scope.designation;
									}
									$scope.userDetails = {
											"employeeId": "",
											"email": $scope.user.email,
											"organization": $scope.user.organization,
											"password": $scope.user.password,
											"firstName": $scope.user.firstName,
											"lastName": $scope.user.lastName,
											"gender": $scope.user.gender,
											"contactNumber": $scope.user.phoneNumber,
											"designation" : $scope.user.designation,
											"profilePicture" : $scope.user.profilePicture
									};

									$http({
										method: 'POST',
										url: "/RipperFit/employee/socialLogin",
										data: $scope.userDetails,
										headers: {
											'Content-Type': 'application/json'
										}
									}).then(function(response) {

										$scope.login = response.data;
										$http({
											method: 'POST',
											url: "/RipperFit/mail/registrationMail",
											data: $scope.login,
											headers: {
												'Content-Type': 'application/json'
											}
										}).then(function() {
											$window.location.href = 'dashboard.html';
										});
									});
								})
							})	
						})
					})
				});	
			})
		}
	}
});