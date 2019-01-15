<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	
		<div class="row">
		
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center"><h1><%= request.getParameter("category") %></h1></div>
			</div>
			
			<c:forEach items="${requestScope.products}" var="product">
			
				<div class="col-lg-6 col-md-6 col-sm-6 my-3">
				<div class="d-block">
					<img class="img-fluid img-thumbnail" src="images/<c:out value="${product.description}"/>.png" alt="<c:out value="${product.description}"/> image" />
					<p><c:out value="${product.description}"/></p>
					<div>
						<input type="checkbox">Add to cart</hr>
						<select name="quantity">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="10">10</option>
							<option value="50">50</option>
						</select></hr>
					</div>
				</div>
			</div>
			</c:forEach>
			
			
			
			
		
	</div>
	</div>
	
	<div class="d-flex justify-content-center">
		<button type="button" class="btn btn-primary btn-lg, info">KEEP ON BUYING</button>
	</div>
	
	<div class="d-flex justify-content-center">
		<button type="button" class="btn btn-primary btn-lg, info">MAKE ORDER</button>
	</div>