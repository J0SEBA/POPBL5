<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

    <div class="d-flex justify-content-between">
			<button class='btn btn-dark"' style = "font-size: 3em; background-color:transparent; "><i class="fa fa-arrow-left"></i> </button>	
			<h1 class="text-center">ORDER</h1>
			<button class='btn btn-dark"' style = "font-size: 3em; background-color:transparent; "><i class="fa fa-home"></i> </button>	
		</div>
	
	
	<c:choose>
	<c:when test="${not empty requestScope.prices}">
	<table class="table table-bordered , text-center">
	  <thead>
		<tr>
		  <th scope="col">Product</th>
		  <th scope="col">Quantity</th>
		  <th scope="col">Price</th>
		</tr>
	  </thead>
	  <tbody>
		<!--<tr>
		  <td scope="row">Spoon</td>
		  <td>1</td>
		  <td>350&euro;</td>
		</tr>
		<tr>
		  <td scope="row">Golf stick</td>
		  <td>1</td>
		  <td>100&euro;</td>
		</tr>-->
		<c:forEach var = "i" begin = "1" end = "${requestScope.descriptions.size()}">
			<tr>
			  <td scope="row"><c:out value="${requestScope.descriptions.get(i)}"/></td>
			  <td>1</td>
			  <td>100&euro;</td>
			</tr>
		
		</c:forEach>
	  </tbody>
	</table>
	</c:when>
	<c:otherwise>
		<p class="text-center">You don&#39;t have any pending order</p>
	</c:otherwise>
	</c:choose>