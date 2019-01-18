<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<form action="getSelectedProducts.html" method="post">
<div class="container">
	
		<div class="row">
		
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center"><h1><%= request.getParameter("category") %></h1></div>
			</div>
			<%int i=0;%>
			<c:forEach items="${requestScope.products}" var="product">
				<div class="col-lg-6 col-md-6 col-sm-6 my-3" align="center">
				<div class="d-block">
					<img class="img-fluid img-thumbnail" src="images/<c:out value="${product.description}"/>.png" alt="${product.description}" style="height: 20rem;" />
					<p><c:out value="${product.description}"/></p>
					<p><c:out value="${product.price}"/>&euro;</p>
					<div>
						<input class="checkBox" type="checkbox" name="cb<%=i%>" value="${product.id}" onclick="change()">Add to cart
						<input class="quantity" type="number"  min="0" max="${product.stock}" name="quantity<%=i%>" value=0 >
					</div>
					<p>Stock: <c:out value="${product.stock}"/></p>
				</div>
			</div>
			<%i++; %>
			</c:forEach>
	</div>
	</div>
	
	<input type="hidden" name="count" value=<%=i%>>
	
	<div class="d-flex justify-content-center">
		<button id="bt1" type="submit" class="btn btn-primary btn-lg, info" name="action" value="keepBuying" disabled>KEEP ON BUYING</button>
	</div>
	
	<div class="d-flex justify-content-center">
		<button id="bt2" type="submit" class="btn btn-primary btn-lg, info" name="action" value="makeOrder" disabled>MAKE ORDER</button>
	</div>
	</form>
		
	<script>
		function change(){
			var cb=document.getElementsByClassName("checkBox");
			var n=document.getElementsByClassName("quantity");
			var bt1=document.getElementById("bt1");
			var bt2=document.getElementById("bt2");
			bt1.disabled=true;
			bt2.disabled=true;
			for (var i = 0; i < cb.length; i++) { 
				  if(cb[i].checked==true){
					  n[i].value=1;
					  n[i].min=1;
					  bt1.disabled=false;
					  bt2.disabled=false;
				  }
				  else{
					  n[i].value=0;
					  n[i].min=0;
				  }
			}
		}
	</script>