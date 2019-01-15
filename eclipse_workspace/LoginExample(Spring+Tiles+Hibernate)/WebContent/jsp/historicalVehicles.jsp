

<script src="css/code/highcharts.js"></script>
<script src="css/code/modules/exporting.js"></script>
<script src="css/code/modules/export-data.js"></script>
<script src="https://d3js.org/d3.v5.min.js"></script>
	<div class="col-lg-12 d-flex justify-content-center align-items-center" >
			 <h1 class="text-center">HISTORICAL ROBOTS</h1>
	</div>
	<div class="container">
		<div class="row">
		
		<div class="col-lg-2">
		  <select class="form-control">
			<option value="robot1">Robot 1</option>
			<option value="robot2">Robot 2</option>
			<option value="robot3">Robot 3</option>
		  </select>
		  </div>
		  
		<div class="col-lg-8">
		<select id="mySelect" class="form-control" onchange="prueba()">
			<option value="1">Number of time used </option>
			<option value="2">Number of Km per month</option>

		  </select>
		 </div>
		  <div class="col-lg-2">
		  <select class="form-control">
			<option value="+47">2017</option>
			<option value="+46">2016</option>
			<option value="+45">2015</option>
		  </select>
		  </div>
		  </div>
		  
		<div id="graph1" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto"></div>
		<div id="graph2" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto; display:none" ></div>
</div>
<script>
	function prueba(){
	 var prueba = document.getElementById("mySelect").value;
	 console.log(prueba);
		if(prueba=="2"){
			document.getElementById("graph1").style.display="none";
			document.getElementById("graph2").style.display="block";		
		}
		if(prueba=="1"){
			document.getElementById("graph2").style.display="none";
			document.getElementById("graph1").style.display="block";				
		}
	}
	
	</script>
		
<script>
Highcharts.chart('graph1', {

    title: {
        text: 'Number of time used'
    },
	 xAxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    yAxis: {
        title: {
            text: 'Number of times the robot has been used'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },

    

    series: [{
        name: 'Robot',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175,8888,777,625454,215344]
    }],

    responsive: {
        rules: [{
            condition: {
                maxWidth: 500
            },
            chartOptions: {
                legend: {
                    layout: 'horizontal',
                    align: 'center',
                    verticalAlign: 'bottom'
                }
            }
        }]
    }

});


</script>		
		
		
<script>
	
	
	Highcharts.chart('graph2', {
    chart: {
        type: 'line'
    },
    title: {
        text: 'Amount of KM per month'
    },
    xAxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    yAxis: {
        title: {
            text: 'KM'
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
        name: 'Robot',
        data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
    }]
});
	 </script>		  