
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="css/code/highcharts.js"></script>

<div class="container">
	<div class="row text-center justify-content-center mb-3">
		<div class="col-5 ">
			<h3>Percentage of use of vehicles</h3>
			<div id="first"></div>
		</div>
	</div>
	<div class="row text-center justify-content-center mb-3">
	<div class="col-10" >
		<h3>Customers general information</h3>
			<div id="second">
				<table id="tabla" class="table table-bordered, text-center">
					<thead>
						<tr>
							<th scope="col">CustomerID</th>
							<th scope="col">Name</th>
							<th scope="col">Gender</th>
							<th scope="col">Quantity of orders</th>
							<th scope="col">Total money spent</th>	
						</tr>
					</thead>
					<tbody id="body">
					</tbody>
				</table>
			</div>
			</div>
	</div>
	<div class="row text-center justify-content-center mb-3">
	<div class="col-10" ">
		<h3>Quantity of products taken from workstations in current year</h3>
		<div id="third"></div>
		</div>
	</div>
		<div class="row text-center justify-content-center">
		<div class="col-10 " >
			<h3>Earnings of warehouse by month</h3>
			<div id="forth"></div>
			</div>
		</div>
		</div>
		
		<script>
			var first = second = third = forth = ''
			window.onload=function(){
				console.log('a')
				getData()
			}
			function getData(){
				console.log('b')
				
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("GET", "http://localhost:8080/SpringMVCTiles/pieChart.html",true)
				xmlhttp.send()
				xmlhttp.onreadystatechange = function() {
			        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
			           if (xmlhttp.status == 200) {
			        	   if(first!==xmlhttp.responseText){
			        		   var data = xmlhttp.responseText.split(',')
			        		   first = xmlhttp.responseText
			        		   generate(data, 1)
			        	   } 
			        	   
			        	   
			           }
			        }
				}
				var xmlhttp2 = new XMLHttpRequest();
				xmlhttp2.open("GET", "http://localhost:8080/SpringMVCTiles/getUsersInfo.html",true)
				xmlhttp2.send()
				xmlhttp2.onreadystatechange = function() {
			        if (xmlhttp2.readyState == XMLHttpRequest.DONE) {
			           if (xmlhttp2.status == 200) {
			        	   if(second!==xmlhttp2.responseText){
			        		 second=xmlhttp2.responseText
			        		 var data = xmlhttp2.responseText.split(',')
			        		   generate(data, 2)
			        	   } 
			        	   
			        	   
			           }
			        }
				}
				var xmlhttp3 = new XMLHttpRequest();
				xmlhttp3.open("GET", "http://localhost:8080/SpringMVCTiles/getNumberOfProductsPerCategory.html",true)
				xmlhttp3.send()
				xmlhttp3.onreadystatechange = function() {
			        if (xmlhttp3.readyState == XMLHttpRequest.DONE) {
			           if (xmlhttp3.status == 200) {
			        	   if(third!==xmlhttp3.responseText){
			        		   var data = xmlhttp3.responseText.split('/')
				        	   var aux = []
				        	   var fin = []
				        	   var a1=[]
				        	   var a2=[]
				        	   var a3=[]
				        	   var a4=[]
				        	   var a5=[]
				        	   for(var d of data){
				        		  aux.push(parseInt(d))
				        	   }
				        	   for(var i =0;i<60;i++){
				        		   if(i%5==0){
				        			   a1.push(aux[i])
				        		   }
				        		   if(i%5==1){
				        			   a2.push(aux[i])
				        		   }
				        		   if(i%5==2){
				        			   a3.push(aux[i])
				        		   }
				        		   if(i%5==3){
				        			   a4.push(aux[i])
				        		   }
				        		   if(i%5==4){
				        			   a5.push(aux[i])
				        		   }
				        	   }
				        	   var fin = [a1,a2,a3,a4,a5]
				        	   third = xmlhttp3.responseText
				        		generate(fin, 3)
			        	   } 
			        	   
			        	   
			           }
			        }
				}
				
				var xmlhttp4 = new XMLHttpRequest();
				xmlhttp4.open("GET", "http://localhost:8080/SpringMVCTiles/getOrdersProductsList.html",true)
				xmlhttp4.send()
				xmlhttp4.onreadystatechange = function() {
			        if (xmlhttp4.readyState == XMLHttpRequest.DONE) {
			           if (xmlhttp4.status == 200) {
			        	   if(forth!==xmlhttp4.responseText){
			        		   var data = xmlhttp4.responseText.split('/')
				        	   forth  =xmlhttp4.responseText
				        		   generate(data, 4)
			        	   }
			        	   
			        	   
			           }
			        }
				}
				
				setTimeout(getData, 5000)
				
				
			}
			
			function generate(date, i){
				if(i==1){
					console.log(1,date)
					Highcharts.chart('first', {
						  chart: {
						    plotBackgroundColor: null,
						    plotBorderWidth: null,
						    plotShadow: false,
						    type: 'pie'
						  },
						  title: {
						    text: ''
						  },
						  tooltip: {
						    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
						  },
						  plotOptions: {
						    pie: {
						      allowPointSelect: true,
						      cursor: 'pointer',
						      dataLabels: {
						        enabled: false
						      },
						      showInLegend: true
						    }
						  },
						  series: [{
						    name: 'Vehicles',
						    colorByPoint: true,
						    data: [{
						      name: 'Vehicle 1',
						      y: parseInt(date[0]),
						      sliced: false,
						      selected: true
						    }, {
						      name: 'Vehicle 2',
						      y: parseInt(date[1])
						    }, {
						      name: 'Vehicle 3',
						      y: parseInt(date[2])
						    }, {
						      name: 'Vehicle 4',
						      y: parseInt(date[3])
						    }, {
						      name: 'Vehicle 5',
						      y: parseInt(date[4])
						    }]
						  }]
						});
				}
				if(i==2){
					var body = document.getElementById('body')
					body.innerHTML = ''
					var str=''
					date.pop()
					for(var d of date){
						var aux=d.split('/')
						str = str + '<tr><th>' + aux[0] + '</th>'
						str = str + '<td>'+aux[1]+'</td>'
						str = str + '<td>'+aux[2]+'</td>'
						str = str + '<td>'+aux[3]+'</td>'
						str = str + '<td>'+aux[4]+'&euro;</td>'
						str = str + '</tr>'
						console.log("total: "+aux[4])
					}
					body.innerHTML = str
				}
				if(i==3){
					console.log(1111,date)
					Highcharts.chart('third', {
						  chart: {
						    type: 'column'
						  },
						  title: {
						    text: ''
						  },
						  xAxis: {
						    categories: [
						      'Jan',
						      'Feb',
						      'Mar',
						      'Apr',
						      'May',
						      'Jun',
						      'Jul',
						      'Aug',
						      'Sep',
						      'Oct',
						      'Nov',
						      'Dec'
						    ],
						    allowDecimals: false,
						    crosshair: true
						  },
						  yAxis: {
						    min: 0,
						    allowDecimals: false,
						    title: {
						      text: 'Order quantity'
						    }
						  },
						  tooltip: {
						    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
						    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
						      '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
						    footerFormat: '</table>',
						    shared: true,
						    useHTML: true
						  },
						  plotOptions: {
						    column: {
						      pointPadding: 0.2,
						      borderWidth: 0
						    }
						  },
						  series: [{
						    name: 'WS1',
						    data: date[0]

						  }, {
						    name: 'WS2',
						    data: date[1]

						  }, {
						    name: 'WS3',
						    data: date[2]

						  }, {
						    name: 'WS4',
						    data: date[3]

						  },{
							    name: 'WS5',
							    data: date[4]

							  }
						  ]
						});
				}
				
				if(i==4){
					var data=[]
					for(var d of date){
						data.push(parseInt(d))
					}
					Highcharts.chart('forth', {
					    chart: {
					        type: 'line'
					    },
					    title: {
					        text: ''
					    },
					    xAxis: {
					        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
					    },
					    yAxis: {
					        title: {
					            text: 'Earnings (&euro;) '
					        }
					    },
					    plotOptions: {
					        line: {
					            dataLabels: {
					                enabled: true
					            },
					            enableMouseTracking: false
					        }
					    },
					    series: [{
					        name: 'Earnings',
					        data: data
					    }]
					});
				}
			}
		
		</script>
	</div>