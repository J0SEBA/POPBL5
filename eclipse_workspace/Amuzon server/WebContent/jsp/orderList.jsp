
<div class="container">
		  <div class="row justify-content-center"  >
			
			
			 <h1 class=" ">ORDER LIST</h1>
		  </div>
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
		
	  </tbody>
	</table>
	<div class="d-flex justify-content-center">
		<button id="button" type="button" class="btn btn-primary btn-lg btn-block" onclick="redirect()">VIEW</button>
	</div>
	
	<script>
		var radios=document.getElementsByClassName("form-check-input");
		
			
			
			
		
		function redirect(){
			var orderId;
			var radios=document.getElementsByClassName("form-check-input");
			for(let i=0;i<radios.length;i++){
				console.log(radios[i]);
				if(radios[i].checked==true)orderId=radios[i].value;
				
			}
			
			window.location.href = "customerOrderView.html?orderId="+orderId;
		}
	
	
	
	
	
		var string = ''
		window.onload = function(){
			
			
			document.getElementById('button').style.display = 'none'
			getData()
			
		}
		
		function getData(i){
			console.log('sartu da')
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET", "http://localhost:8080/SpringMVCTiles/orderList.html",true)
			xmlhttp.send()
			xmlhttp.onreadystatechange = function() {
		        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
		           if (xmlhttp.status == 200) {
		        	   data = xmlhttp.responseText.split(',')
		        	   console.log(data)
		        	   var aux = []
		        	   for(var d of data){
		        		   aux.push(d.split('/'))
		        	   }
		        	   if(xmlhttp.responseText !== string){
		        		   string = xmlhttp.responseText
		        		   generate(aux)
		        	   }
		        	   else {
		        		   
		        		   setTimeout(getData, 1000)
		        	   }
		           }
		        }
			}
		}
		
		function generate(data){
			var body = document.getElementById('body')
			body.innerHTML = ''
			var str = ''
			for(var d of data){
				str = str + '<tr><th class="' + d[0] +'" scope="row">' + d[0] + '</th>'
				str = str + '<td class="'+d[0]+'">'+d[1]+'</td>'
				str = str + '<td class="'+d[0]+'">'+d[2]+'&euro;</td>'
				str = str + '<td style = "border-top:hidden;border-bottom:hidden;border-right:hidden;">'
				str = str + '<div class="form-check, col4">'
				str = str + '<input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios'+d[0]+'" value="'+d[0]+'">'
				str = str + '<label class="form-check-label" for="exampleRadios'+d[0]+'">'
				str = str + '</label>'
				str = str + '</div>'
				str = str + '</td>'
				str = str + '</tr>'
			}
			body.innerHTML=str
			console.log(body.innerHTML)
			document.getElementById('button').style.display = ''
			document.getElementById('tabla').style.display = ''
			if(radios.length==0)document.getElementById("button").disabled=true;
			else radios[0].checked=true;
			setTimeout(getData, 1000)
			
		}
	</script>
	