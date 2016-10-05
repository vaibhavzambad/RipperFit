var myApp = angular.module("MyApp", [ 'ui.bootstrap' ]);
$('header').hide();

myApp.controller('requestController',
		['$scope', '$http',
		 			function($scope, $http) {
					
						// Getting Resource Requests JSON Object from REST Service
						$scope.viewRequests = function(){$http.get('/RipperFit/request/viewrequests/' + $scope.requests[$index].request_id)
							.success(function(data) {
								$scope.requests = data;
							}).error(function() {
								console.log("error");
							});
						}
						
						// Getting All Resource Requests JSON Object from REST Service
						$scope.viewAllRequests = function(){$http.get('/RipperFit/request/viewrequests')
							.success(function(data) {
								$scope.requests = data;
							}).error(function() {
								console.log("error");
							});
						}
											
						// Setting objects for modals
						$scope.editedItem = {
							"request_id" : "",
							"resource_id" : "",
							"requestor_id" : "",
							"current_approval_designation_id" : "",
							"priority" : "",
							"status" : "",
							"comments" : "",
						};
											
						// Delete request
						$scope.deleteRequests = function($index) {
							console.log("delete details called");
							$http({
								method : 'DELETE',
								url : 'http://localhost:8080/RipperFit/request/deleteRequest/' + $scope.requests[$index].request_id,
								headers : {'Content-Type' : 'application/json'}
							})
							.then(
								function(responses) {
									$scope.viewRequests();
									$scope.cars.splice($index, 1);
									$('header').show();
									$scope.message = "Request deleted Sucessfully";
									console.log("delete success message called");
									setTimeout(function() {$("header").fadeOut(1500);}, 1500);
								},
								function(responses) {
									console.log("Error function called");
									console.log(responses);
									$('header').show();
									$scope.message = "Something went wrong sorry for inconvenience !!!!";
									setTimeout(function() {$("header").fadeOut(1500);}, 1500);
								}
							);
						}
				
						$scope.addItem = {
							"request_id" : "",
							"resource_id" : "",
							"requestor_id" : "",
							"current_approval_designation_id" : "",
							"priority" : "",
							"status" : "",
							"comments" : "",
						};
				
						// Adding requests
						$scope.addRequests = function() {
							$http({
								method : 'POST',
								url : 'http://localhost:8080/RipperFit/request/addRequest/' + $scope.employees[$index].employee_id,
								data : $scope.addItem
							})
							.then(
								function(data) {
									$scope.getCars();
									$('header').show();
									$scope.message = "Car Added Sucessfully !!!!";
									setTimeout(function() {$("header").fadeOut(1500);}, 1500);
								},
								function(data) {
									$('header').show();
									$scope.message = "Something went wrong sorry for inconvenience !!!!";
									setTimeout(function() {$("header").fadeOut(1500);}, 1500);
								}
							);
						}
						
					}
		]
);