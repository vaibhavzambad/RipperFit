var ripperFit = angular.module('ripperFit',[]);

ripperFit.controller('googleLoginCtrl', function($scope, $http,$window){

	$scope.googleLogin = function(){
		
		auth2.grantOfflineAccess({
			'redirect_uri' : 'postmessage'
		}).then(function(authResult){
				
				if (authResult['code']) {
					// Hide the sign-in button now that the user is authorized, for example:
					/*$('#signinButton').attr('style',
							'display: none');*/
					// Send the code to the server
					$.ajax({
						type : 'POST',
						url : '/RipperFit/social/getDetails',
						contentType : 'application/octet-stream; charset=utf-8',
						success : function(result) {
								// Handle or verify the server response.
								//$scope.employeeDetails = response.data; 
								$window.location.href = '/RipperFit/welcome';
							},
							processData : false,
							data : authResult['code']
						});
				} else {
					// There was an error.
					$window.location.href = '/RipperFit/login';
				}
			}
		);	
	}
});