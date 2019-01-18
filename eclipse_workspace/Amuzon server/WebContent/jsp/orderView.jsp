<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="container">
<div class="row">
				
			<h1 class="col text-center">ORDER</h1>
			
		
	
	</div>
	<table class="table table-bordered , text-center">
	  <thead>
		<tr>
		  <th scope="col">Product</th>
		  <th scope="col">Quantity</th>
		  <th scope="col">Price</th>
		</tr>
	  </thead>
	  <tbody>
		
		<c:forEach var = "i" begin = "1" end = "${requestScope.descriptions.size()}">
			<tr>
			  <td scope="row"><c:out value="${requestScope.descriptions.get(i-1)}"/></td>
			  <td><c:out value="${requestScope.ordersProduct.get(i-1).quantity}"/></td>
			  <td><c:out value="${requestScope.ordersProduct.get(i-1).price}"/>&euro;</td>
			</tr>
		
		</c:forEach>
	  </tbody>
	</table>
	</div>