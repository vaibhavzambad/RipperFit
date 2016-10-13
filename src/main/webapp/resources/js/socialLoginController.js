signUp.controller('socialCtrl',function($scope,$http,$window){


	$('#signinButton').click(function() {
		// signInCallback defined in step 6.
		auth2.grantOfflineAccess({
			'redirect_uri' : 'postmessage'
		}).then(signInCallback);
	});

});

	function signInCallback(authResult) {
		if (authResult['code']) {
			// Hide the sign-in button now that the user is authorized, for example:
			$('#signinButton').attr('style', 'display: none');
			// Send the code to the server
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
					console.log($scope.user);
					//$window.location.href="/RipperFit/signUpAfterSocialLogin";
				}, function (){ 
					alert("Something went wrong");
				});
		} else {
			// There was an error.
		}
	}