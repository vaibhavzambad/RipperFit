<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en" ng-app="admin">
<head>
 
    <meta charset="utf-8">
    <title>Admin App</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
  <script src="<c:url value="/resources/js/jquery-3.1.1.min.js" />"></script>
 <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

  <%-- <script src="<c:url value="/angular.min.js" />"></script>
    <script src="angular-route.min.js"></script> --%>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>


      <script src="<c:url value="/resources/js/adminRoute.js" />"></script>
      <script>
      function status()
      {
    	  console.log("dfd");
    	  var status=document.getElementById('status').value();
    	  console.log("dfd");
      }
      </script>
</head>
<body>
      <nav class="navbar navbar-inverse" style="border-radius:0px">
  <div class="container-fluid">
    <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="">RipperFit</a>
    </div>
      <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
      <li class="active"><a href="#admin/home">Home</a></li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="">Designation<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#/admin/viewRole">Designations</a></li>
          <li><a href="">Add Designation</a></li>
          
        </ul>
      </li>
      <li><a href="#/admin/viewRequest">Resource Request</a></li>
      <li><a href="#/admin/viewEmployee">Employees</a></li>
	   <li><a href="">Resources</a></li>
	   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="">Resource request<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="">Add Own Request</a></li>
         
        </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">

        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="">AdminPanel<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="">View Profile</a></li>
          <li><a href="">Log Out</a></li>
     
        </ul>
      </li>

    </ul>
  </div>
  </div>
</nav>
  
   
    
 
    <div ng-view="">
Welcome to RipperFit
	</div>
	<div ng-app="">
	<div ng-include="" src="'footer.jsp'"></div>
	</div>
	
    </div>
     
 
<%--<script src="<c:url value="/resources/js/controller/ItemListController.js" />"></script>
<script src="<c:url value="/resources/js/controller/ItemDetailsController.js" />"></script>
 --%>
  
</body>

</html>
