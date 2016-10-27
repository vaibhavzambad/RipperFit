app.config(function($routeProvider) {
	$routeProvider.when("/changePassword", {
		templateUrl: "changePassword.html",
		controller: "changePasswordCtrl"
	})
})
.controller("changePasswordCtrl", function($scope, $http, $window, StoreCurrentLoggedInUserInformationService) {
	$scope.changePassword = function(user) {
		var message = angular.element(document.querySelector('#message'));
		if (user.new_password == user.confirm_password) {
			$http({
				method: 'POST',
				url: "/RipperFit/employee/changePassword?oldPassword=" + user.old_password + "&newPassword=" + user.new_password,
				headers: {
					'Content-Type': 'application/json'
				}
			})
			.then(function(response) {
				$scope.employee = StoreCurrentLoggedInUserInformationService.get();
				$scope.employee.password = user.new_password;
				StoreCurrentLoggedInUserInformationService.set($scope.employee);
				message.text("Password Changed");
			}, function(response) {
				message.text("Wrong Password");
			});
		} else {
			message.text("Password and confirm password do not match");
		}
	}
});