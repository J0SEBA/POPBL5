<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="main.java.model.*" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="mr-auto nav-link" href="home.html"><img src="images/amuzon.svg" height="50 rem"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  <c:choose>  
    <c:when test="${not empty sessionScope.user}">
		<div class=" btn-group ml-auto mx-2" >
		  <button type="button" class=" btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <c:out value="${sessionScope.user.username}"/>
		  </button>
		  <div class="dropdown-menu">
		    <a class="dropdown-item" href="orderView.html">Actual order</a>
		    <a class="dropdown-item" href="orderList.html">Previous orders</a>
		    <div class="dropdown-divider"></div>
		    <a class=" dropdown-item" id="logout" href="logout.html">Log out</a>
			
		  </div>
		</div>
    </c:when>
    <c:when test="${not empty sessionScope.operator}">
    	 <c:choose>  
    	  <c:when test="${sessionScope.manager}">
            <label class="nav-link ml-auto mx-1" style="color: red;">Manager</label>
        <div class=" btn-group  mx-2" >
		  <button type="button" class=" btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <c:out value="${sessionScope.operator.username}"/>
		  </button>
		  <div class="dropdown-menu">
		    <div class="dropdown-divider"></div>
		    <a class=" dropdown-item" id="logout" href="logout.html">Log out</a>
		  </div>
		</div>
        </c:when>
         <c:otherwise>
		<div class=" btn-group ml-auto mx-2" >
		  <button type="button" class=" btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <c:out value="${sessionScope.operator.username}"/>
		  </button>
		  <div class="dropdown-menu">
		    <div class="dropdown-divider"></div>
		    <a class=" dropdown-item" id="logout" href="logout.html">Log out</a>
		  </div>
		</div>
		</c:otherwise>
		 </c:choose> 
    </c:when>
    <c:otherwise>
	   <ul class="navbar-nav ml-auto ">
          <li class="nav-item">
            <a class="nav-link" href="login.html">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="register.html">Register</a>
          </li> 
        </ul>
	</c:otherwise>
  </c:choose>
    <form class="form-inline my-2 my-lg-0 ">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav> 
