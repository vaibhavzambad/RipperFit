app.controller('forgotPasswordCtrl', function($scope, $http, $window, $filter) {
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