
<script src="css/code/highcharts.js"></script>
<script src="css/code/modules/exporting.js"></script>
<script src="css/code/modules/export-data.js"></script>

	<script type="text/javascript" src="http://d3js.org/d3.v3.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

	<div class="col-lg-12 d-flex justify-content-center align-items-center" >
			 <h1 class="text-center">HISTORICAL ORDERS</h1>
	</div>


	<div class="container">
		<div class="row">
		<div class="col-lg-8">
		<select id="mySelect" class="form-control" onchange="prueba()">
			<option value="1">Number of product per user age</option>
			<option value="2">Price per order per year</option>
			<option value="3">Number of product per category per day</option>
		  </select>
		 </div>
		  <div class="col-lg-4">
		  <select class="form-control">
			<option value="2017">2017</option>
			<option value="2016">2016</option>
			<option value="2015">2015</option>
		  </select>
		  </div>
	</div>
	</div>
	
	<div id="graph1" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto"></div>
	<div id="graph2" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto; display:none" ></div>
	<div id="graph3" style="min-width: 310px; max-width: 1200px; height: 400px; margin: 0 auto; display:none" ></div>
	<script>
	function prueba(){
	 var prueba = document.getElementById("mySelect").value;
	 console.log(prueba);
		if(prueba=="2"){
			document.getElementById("graph1").style.display="none";
			document.getElementById("graph2").style.display="block";
			document.getElementById("graph3").style.display="none";
			
			
		}
		if(prueba=="1"){
			document.getElementById("graph2").style.display="none";
			document.getElementById("graph1").style.display="block";		
			document.getElementById("graph3").style.display="none";			
		}
		
		if(prueba=="3"){
			document.getElementById("graph2").style.display="none";
			document.getElementById("graph3").style.display="block";		
			document.getElementById("graph1").style.display="none";			
		}
	}
	
	</script>

<script>

    var categories = [
    '18-19', '20-24', '25-29', '30-34', '35-39', '40-44',
    '45-49', '50-54', '55-59', '60-64', '65-69',
    '70-74', '75-79', '80-84', '85+'
];

Highcharts.chart('graph1', {
    chart: {
        type: 'bar'
    },
    title: {
        text: 'Number of product per user age men/women'
    },
    
	
    xAxis: [{
        categories: categories,
        reversed: false,
        labels: {
            step: 1
        }
    }, { // mirror axis on right side
        opposite: true,
        reversed: false,
        categories: categories,
        linkedTo: 0,
        labels: {
            step: 1
        }
    }],
    yAxis: {
        title: {
            text: null
        },
        labels: {
            formatter: function () {
                return Math.abs(this.value) + '';
            }
        }
    },

    plotOptions: {
        series: {
            stacking: 'normal'
        }
    },

    tooltip: {
        formatter: function () {
            return '<b>' + this.series.name + ', age ' + this.point.category + '</b><br/>' +
                'Population: ' + Highcharts.numberFormat(Math.abs(this.point.y), 0);
        }
    },

    series: [{
        name: 'Male',
        data: [
            -2.2, -2.1, -2.2, -2.4,
            -2.7, -3.0, -3.3, -3.2,
            -2.9, -3.5, -4.4, -4.1,
            -3.4, -2.7,-2.6
        ]
    }, {
        name: 'Female',
        data: [
            2.1, 2.0, 2.1, 2.3, 2.6,
            2.9, 3.2, 3.1, 2.9, 3.4,
            4.3, 4.0, 3.5, 2.9,2.5
        ]
    }]
});
        
    </script>
	
<script>
	
	
	Highcharts.chart('graph2', {
    chart: {
        type: 'line'
    },
    title: {
        text: 'Price per order'
    },
    xAxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },
    yAxis: {
        title: {
            text: 'Price (€) '
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
        name: 'Tokyo',
        data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
    }]
});
	 </script>

<script>
Highcharts.chart('graph3', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Number of product per category'
    },
    xAxis: {
        categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Total fruit consumption'
        }
    },
    tooltip: {
        pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        shared: true
    },
    plotOptions: {
        column: {
            stacking: 'percent'
        }
    },
    series: [{
        name: 'John',
        data: [5, 3, 4, 7, 2]
    }, {
        name: 'Jane',
        data: [2, 2, 3, 2, 1]
    }, {
        name: 'Joe',
        data: [3, 4, 4, 2, 5]
    }]
});

</script>	 