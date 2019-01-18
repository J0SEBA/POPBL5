<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="container">
<div class="row">
				
			<h1 class="col text-center">SHOPPING CART</h1>
			
		
	
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
		<c:forEach var = "i" begin = "1" end = "${requestScope.products.size()}">
			<tr>
			  <td scope="row"><c:out value="${requestScope.products.get(i-1).description}"/></td>
			  <td><c:out value="${requestScope.quantities.get(i-1)}"/></td>
			  <td><c:out value="${requestScope.products.get(i-1).price}"/>&euro;</td>
			</tr>
		
		</c:forEach>
		
	  </tbody>
	</table>
	
	<h5>Total: ${sessionScope.total}&euro;</h5>
	</div>
	
	
	
	<div class=" row">
		<button type="button" class="btn btn-primary btn-lg btn-block col-lg-2 mx-auto" onclick="redirect()">PAYMENT</button>
	</div>
	
	<script>
		function redirect(){
			
	   	 	window.location.href = "payment.html";
		};
		
		
	</script>