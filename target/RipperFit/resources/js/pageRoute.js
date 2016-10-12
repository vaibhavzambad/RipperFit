var app=angular.module("RipperFit",["ngRoute"])
.config(function($routeProvider)
		{
	$routeProvider.when("/login",{
		templateUrl:"WEB-INF/views/login.jsp",
	}).when("/signUp",{
		templateUrl:"WEB-INF/views/employee",
	})
		})