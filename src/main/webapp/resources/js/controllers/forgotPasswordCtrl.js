app.controller('forgotPasswordCtrl', function($scope, $http, $window, $filter) {
	$scope.forgotPassword = function(email) {
		var message = angular.element(document.querySelector('#message'));
		
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
				message.text("Please check your email account .A password has been sent.");
			});
		});
	}
});