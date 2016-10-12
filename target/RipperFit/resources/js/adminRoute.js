var app=angular.module("admin",["ngRoute"])
.config(function($routeProvider)
{
$routeProvider.when("/admin/viewRequest",{
templateUrl:"/RipperFit/request",
controller:"viewRequestController"
}).when("/admin/viewRole",{
templateUrl:"/RipperFit/role",
controller:"viewRoleController"
})
}).controller("viewRequestController",function($scope,$http)
{
 $http.get("/RipperFit/request/viewAllRequests")
  .then(function(response) {
      $scope.requests = response.data;
  });

})
.controller("viewRoleController",function($scope,$http)
{$http.get("/RipperFit/role/viewDesignation")
  .then(function(response) {
      $scope.designation = response.data;
      console.log($scope.designation);
  });
})