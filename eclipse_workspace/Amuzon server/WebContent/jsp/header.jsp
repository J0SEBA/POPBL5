<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="main.java.domain.category.model.*" %>
<%@ page import="main.java.domain.login.model.*" %>
<%@ page import="main.java.domain.operator.model.*" %>
<%@ page import="main.java.domain.order.model.*" %>
<%@ page import="main.java.domain.ordersProduct.model.*" %>
<%@ page import="main.java.domain.product.model.*" %>
<%@ page import="main.java.domain.user.model.*" %>
<%@ page import="main.java.domain.vehicle.model.*" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="mr-auto nav-link" href="home.html"><img src="images/amuzon.svg" height="50 rem"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse pr-5" id="navbarSupportedContent">
  <c:choose>  
    <c:when test="${not empty sessionScope.user}">
		<div class=" btn-group ml-auto " >
		  <button type="button" class=" btn btn-info dropdown-toggle mr-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <c:out value="${sessionScope.user.username}"/>
		  </button>
		  <div class="dropdown-menu">
		  
		  	<c:choose>
		  	<c:when test="${not empty sessionScope.shoppingCart}">
		  	<a class="dropdown-item" href="shoppingCart.html">Shopping cart</a>
		  	</c:when>
		  	<c:otherwise>
		  	<a class="dropdown-item disabled" href="#" onclick="return false;">Shopping cart</a>
		  	</c:otherwise>
		  	</c:choose>
		    <a class="dropdown-item" href="customerActualOrders.html">Actual orders</a>
		    <a class="dropdown-item" href="customerPreviousOrders.html">Previous orders</a>
		    <div class="dropdown-divider"></div>
		    <a class=" dropdown-item" id="logout" href="logout.html">Log out</a>
			
		  </div>
		</div>
    </c:when>
    <c:when test="${not empty sessionScope.operator}">
    	 <c:choose>  
    	  <c:when test="${sessionScope.manager}">
            <label class="nav-link ml-auto mx-1" style="color: red;">Manager</label>
        <div class=" btn-group  " >
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
         <label class="nav-link ml-auto mx-1" style="color: red;">Operator</label>
		<div class=" btn-group  " >
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
   
  </div>
</nav> 

