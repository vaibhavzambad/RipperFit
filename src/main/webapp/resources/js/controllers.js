var RipperFit = angular.module('RipperFit',[])

.factory('StoreService',["$window",function($window) {
	function set(data) {
		$window.sessionStorage.setItem('userInfo', $window.JSON.stringify(data));
	} function get() {
		return $window.JSON.parse($window.sessionStorage.getItem('userInfo'));
	} return {
		set: set,
		get: get
	}
}])

.directive("passwordVerify", function() {
	return {
		require: "ngModel",
		scope: {
			passwordVerify: '='
		},
		link: function(scope, element, attrs, ctrl) {
			scope.$watch(function() {
				var combined;

				if (scope.passwordVerify || ctrl.$viewValue) {
					combined = scope.passwordVerify + '_' + ctrl.$viewValue; 
				}                    
				return combined;
			}, function(value) {
				if (value) {
					ctrl.$parsers.unshift(function(viewValue) {
						var origin = scope.passwordVerify;
						if (origin !== viewValue) {
							ctrl.$setValidity("passwordVerify", false);
							return undefined;
						} else {
							ctrl.$setValidity("passwordVerify", true);
							return viewValue;
						}
					});
				}
			});
		}
	};
})

.controller('socialCtrl', function($scope, $http, $window, StoreService) {
	$('#signinButton').click(function() {
		auth2.grantOfflineAccess({
			'redirect_uri' : 'postmessage'
		}).then(signInCallback);
	});

	function signInCallback(authResult) {
		if (authResult['code']) {
			$http({
				method : 'POST',
				url : '/RipperFit/social/getDetails',
				headers: {
					'Content-Type': 'application/json'
				},
				processData : false,
				data : authResult['code']
			}).then( function(response) {
				$scope.user = response.data;
				$http({
					method : 'GET',
					url : '/RipperFit/employee/getEmployeeByEmail?email='+$scope.user.email,
					headers: {
						'Content-Type': 'application/json'
					}
				}).then(function(response) {
					if(response.status == 200) {
						$http({
							method : 'GET',
							url : '/RipperFit/employee/createSession?email='+$scope.user.email,
							headers: {
								'Content-Type': 'application/json'
							}
						}).then(function() {
							$window.location.href = 'dashboard.html';
						});	
					} else {
						StoreService.set($scope.user);
						$window.location.href = 'socialSignUp.html';
					}
				});
			});
		}
	}
})

.controller('formPopulateCtrl', function($scope, StoreService, $http, $window, $filter) {

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

})

.controller('signUpCtrl', function($scope, $http, $window, $filter){
	var message = angular.element(document.querySelector('#message'));
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

	$scope.getFormDetails = function(user) {
		$scope.email="";
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
				"profilePicture" : "https://lh6.googleusercontent.com/-pkto2iAvl1A/AAAAAAAAAAI/AAAAAAAAAB8/XHv93lNF7CI/s96-c/photo.jpg"
		};
		
		$http({
			method: 'POST',
			url: "/RipperFit/employee/addEmployee",
			data: $scope.userDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.email = $scope.userDetails.email;
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMailOnSignUp",
				data: $scope.email,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function(response) {
				$window.location.href = 'dashboard.html';
			});
		}, function(response) {	
			if(response.status === 409){
				message.text("User Already Present");
			}
		});
	}

	$scope.createOrganization = function() {
		var flag=true;
		var org = angular.element( document.querySelector( '#inputOrganization' ) );
		org.removeClass('hidden');
		var organization = angular.element( document.querySelector( '#selectOrganization' ) );
		organization.addClass('hidden');

		$scope.getFormDetails = function(user) {
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
									if(flag==true) {
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
											"profilePicture" :null
									};

									$http({
										method: 'POST',
										url: "/RipperFit/employee/addEmployee",
										data: $scope.userDetails,
										headers: {
											'Content-Type': 'application/json'
										}
									}).then(function() {

										$scope.email = $scope.userDetails.email;
										$http({
											method: 'POST',
											url: "/RipperFit/mail/registrationMailOnSignUp",
											data: $scope.email,
											headers: {
												'Content-Type': 'application/json'
											}
										}).then(function() {
											$window.location.href = 'dashboard.html';
										});
									}, function(response) {
										if(response.status === 409){
											message.text("User Already Present");
										}
									});
								})
							})	
						})
					})
				});	
			},function(response){
				if(response.status === 409){
					message.text("Organization Already Present");
				}
			});
		}
	}
})

.controller('signInCtrl', function($scope, $http, $window, $filter) {
	$scope.login = function(user) {
		var message = angular.element(document.querySelector('#message'));
		$scope.loginDetails=angular.copy(user);
		$http({
			method: 'POST',
			url: "/RipperFit/employee/login",
			data: $scope.loginDetails,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$window.location.href = 'dashboard.html';
		}, function(response) {
			if(response.status === 401 || response.status === 400){
				message.text("Invalid Username or Password");
			}
		});
	}

	$scope.forgotPassword = function(email) {
		$http({
			method: 'PUT',
			url: "/RipperFit/employee/forgetPassword",
			data: email,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(function(response) {
			$scope.newPassword = response.data.pass;
			$scope.loginDetails = {
					"email": email,
					"password":$scope.newPassword
			};
			$http({
				method: 'POST',
				url: "/RipperFit/mail/registrationMail",
				data: $scope.loginDetails,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function(response) {
				$window.location.href = 'signIn.html';
			});
		});
	}
});