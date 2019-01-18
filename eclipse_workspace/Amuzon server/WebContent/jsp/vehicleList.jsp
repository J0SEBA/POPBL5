
<div class="col-lg-12 d-flex justify-content-center align-items-center" >
			 <h1 class="text-center">VEHICLES</h1>
			</div>
			
			<table id="tabla" class="table table-bordered, text-center">
				<thead>
					<tr>
						<th scope="col">VehicleID</th>
						<th scope="col">Position</th>
						<th scope="col">State</th>
						<th scope="col">Total Distance</th>
					</tr>
				</thead>
				<tbody id="body">
				
				</tbody>
			</table>
			
		
		<script>
			var string = ''
			window.onload=function(){
				getData()
			}
			
			
			function getData(){
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("GET", "http://localhost:8080/SpringMVCTiles/getVehicleList.html?q=1",true)
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
			        		   console.log(string)
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
					str = str + '<td class="'+d[0]+'">'+d[2]+'</td>'
					str = str + '<td class="'+d[0]+'">'+d[3]+'</td>'
					str = str + '</tr>'
				}
				body.innerHTML = str
				setTimeout(getData, 1000)
			}
		</script>