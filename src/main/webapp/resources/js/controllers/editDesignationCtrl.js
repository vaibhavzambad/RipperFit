app.config(function($routeProvider) {
	$routeProvider.when("/editDesignation/:designationId", {
		templateUrl: "editDesignation.html",
		controller: "editDesignationCtrl",
	})
})
.controller("editDesignationCtrl", function($scope, $http, $routeParams, $window) {

	$http.get("/RipperFit/designation/getDesignationById/" + $routeParams.designationId)
	.then(function(response) {
		$scope.designation = response.data;
		$scope.UpdateData = function(designation) {
			$scope.des = {
					"designationId": $scope.designation.designationId,
					"designationName": $scope.designation.designationName,
					"designationLevel": $scope.designation.designationLevel,
					"department": $scope.designation.department,
					"organization": $scope.designation.organization
			};

			$http({
				method: 'PUT',
				url: "/RipperFit/designation/updateDesignation",
				data: $scope.des,
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function() {
				$window.location.href = "#/viewDesignations/";
			});
		}
	});
});