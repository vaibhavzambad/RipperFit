<!doctype html>
<html lang="en" ng-app="admin">
<head>
 
    <meta charset="utf-8">
    <title>Admin App</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/bootstrap.min.css" />
  <script src="resources/js/jquery-3.1.1.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>

  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>


      <script src="resources/js/adminRoute.js" ></script>
</head>
<body>
      <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">RipperFit</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>

      <li><a href="#/admin/viewRequest">View Resource Request</a></li>
	   <li><a href="#/admin/viewRole">View Designations</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">

        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">View Profile</a></li>
          <li><a href="#">Log Out</a></li>
     
        </ul>
      </li>

    </ul>
  </div>
</nav>
  
   
    
 
    <div ng-view="">
Welcome to RipperFit
	</div>
     <footer class="navbar-fixed-bottom text-center" style="background-color:black">
	@copyright Ripperfit.com
	</footer>
     
 
</body>
</html>
