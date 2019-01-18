<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="container">
		  <div class="row justify-content-center"  >
			
			
			 <h1 class=" ">ORDER LIST</h1>
		  </div>
		  
	
	<table id="tabla" class="table table-bordered , text-center">
	  <thead>
		<tr>
		  <th scope="col">OrderID</th>
		  <th scope="col">Date</th>
		  <th scope="col">Total</th>
		  <th style = "border-top:hidden;border-bottom:hidden;border-right:hidden;"> </th>
		</tr>
	  </thead>
	  <tbody id="body">
		<c:forEach items="${requestScope.orders}" var="order">
		<tr>
			<td scope="col"><c:out value="${order.id}"/></td>
		  <td scope="col"><c:out value="${order.date}"/></td>
		  <td scope="col"><c:out value="${order.total}"/>&euro;</td>
		  <td style = "border-top:hidden;border-bottom:hidden;border-right:hidden;"> 
			<div class="form-check, col4">
			<input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="${order.id}"  >
			<label class="form-check-label" for="exampleRadios1"></label>
				
			
		  	</div>
		  </td>
		  </tr>
		</c:forEach>
	  </tbody>
	</table>
	
	<div class="d-flex justify-content-center">
		<button id="button" type="button" class="btn btn-primary btn-lg btn-block" onclick="redirect()">VIEW</button>
	</div>
	
	</div>
	<script>
		var radios=document.getElementsByClassName("form-check-input");
		window.onload=function(){
			
			if(radios.length==0)document.getElementById("button").disabled=true;
			else radios[0].checked=true;
		}
			
		
		function redirect(){
			var orderId;
			var radios=document.getElementsByClassName("form-check-input");
			console.log("${orders.size()}");
			for(let i=0;i<"${orders.size()}";i++){
				console.log(radios[i]);
				if(radios[i].checked==true)orderId=radios[i].value;
				
			}
			
			window.location.href = "customerOrderView.html?orderId="+orderId;
		}
	</script>
	