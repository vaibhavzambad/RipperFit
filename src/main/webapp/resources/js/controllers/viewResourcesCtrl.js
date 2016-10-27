app.config(function($routeProvider) {
	$routeProvider.when("/viewResources", {
		templateUrl: "viewResources.html",
		controller: "viewResourcesCtrl"
	})
})
.controller("viewResourcesCtrl", function($scope, $http, $filter, StoreCurrentLoggedInUserInformationService) {
	$scope.employee = StoreCurrentLoggedInUserInformationService.get();
	var str = $filter('uppercase')($scope.employee.designation.designationName);

	if(str=="ADMIN") {
		$scope.edit=false;
	} 
	var des = $filter('uppercase')(
			$scope.employee.designation.designationName);

	var myEl = angular.element(document
			.querySelector('#addResources'));
	if (des == "HELPDESK") {
		myEl.removeClass('hidden');
	} else {
		myEl.addClass('hidden');
	}
	$http.get("/RipperFit/resource/getResourcesByOrganizationId/" + $scope.employee.organization.organizationId)
	.then(function(response) {
		$scope.resources = response.data;

		var myEl = angular.element(document.querySelector('#table'));
		var myE2 = angular.element(document.querySelector('#msg'));
		var length = $scope.resources.length;

		if (length == 0) {
			myEl.addClass('hidden');
			myE2.removeClass('hidden');
		} else {
			myE2.addClass('hidden');
		}
	}),
	function(response) {
		if(response.status === 409){
			message.text("Resource Already Present");
		}
	}
});