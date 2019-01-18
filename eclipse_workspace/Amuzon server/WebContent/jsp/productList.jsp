
<div class="container">
<table id="tabla" class="table table-bordered , text-center">
	  <thead>
		<tr>
		  <th scope="col">ProductID</th>
		  <th scope="col">Description</th>
		  <th scope="col">Price</th>
		  <th scope="col">Stock</th>
		</tr>
	  </thead>
	  <tbody id="body">
	  
	  </tbody>
	</table>
	</div>
	
	<script>
		var string = ''
		window.onload = function(){
			getData()
		}
		
		function getData(i){
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET", "http://localhost:8080/SpringMVCTiles/getProductList.html",true)
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
				str = str + '<td class="'+d[0]+'">'+d[3]+'</td>'
				str = str + '</tr>'
			}
			body.innerHTML = str
			document.getElementById('tabla').style.display = ''
			setTimeout(getData, 1000)
		}
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	