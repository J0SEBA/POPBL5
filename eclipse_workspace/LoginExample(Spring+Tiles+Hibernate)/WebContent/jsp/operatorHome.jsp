
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<div class="wh-100 p-3">
		<c:choose>
		<c:when test="${sessionScope.manager}">
			
		  <a class="btn btn-primary btn-lg btn-block" href="historicalVehicles.html" role="button">HISTORICAL OF VEHICLES</a>
		<a class="btn btn-primary btn-lg btn-block" href="historicalOrders.html" role="button">HISTORICAL OF ORDERS</a>
		<a class="btn btn-primary btn-lg btn-block" href="historicalWorkstation.html" role="button">HISTORICAL OF WAREHOUSE</a>
		  
		</c:when>
		<c:otherwise>
			<a class="btn btn-primary btn-lg btn-block" href="vehicleList.html" role="button">POSITION AND STATUS OF VEHICLES</a>
			<a class="btn btn-primary btn-lg btn-block" href="operatorOrderList.html" role="button">ORDERS LIST</a>
			<a class="btn btn-primary btn-lg btn-block" href="productList.html" role="button">LIST OF PRODUCTS</a>
		  	
		</c:otherwise>
		</c:choose>
	</div>