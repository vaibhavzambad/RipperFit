var app = angular.module("RipperFit", ["ngRoute"])

.factory('StoreCurrentLoggedInUserInformationService', ["$window", function($window) {
	function set(data) {
		$window.sessionStorage.setItem(
				'currentLoggedInUser', $window.JSON
				.stringify(data));
	}

	function get() {
		return $window.JSON
		.parse($window.sessionStorage
				.getItem('currentLoggedInUser'));
	}
	return {
		set: set,
		get: get
	}
}]);