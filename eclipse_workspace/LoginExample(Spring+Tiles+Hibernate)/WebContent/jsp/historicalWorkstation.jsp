
<script src="css/code/highcharts.js"></script>
<script src="css/code/modules/exporting.js"></script>
<script src="css/code/modules/export-data.js"></script>
	<div class="col-lg-12 d-flex justify-content-center align-items-center" >
			 <h1 class="text-center">HISTORICAL WORKSTATION</h1>
	</div>


	<div class="container">
		<div class="row">
		
		<div class="col-lg-2">
		  <select class="form-control">
			<option value="1">Workstation 1</option>
			<option value="+46">Workstation 2</option>
			<option value="+45">Workstation 3</option>
			<option value="+47">Workstation 4</option>
			<option value="+46">Workstation 5</option>
			<option value="+45">Workstation 6</option>
		  </select>
		  </div>
		  
		<div class="col-lg-8">
		<select class="form-control">
			<option value="+47">Amount of product on stock per month </option>

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
		</div>
	<script> Highcharts.chart('graph1', {

    chart: {
        type: 'column'
    },

    title: {
        text: 'Amount of product on stock per month'
    },

    xAxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    },

    yAxis: {
        allowDecimals: false,
        min: 0,
        title: {
            text: 'Number of fruits'
        }
    },

    tooltip: {
        formatter: function () {
            return '<b>' + this.x + '</b><br/>' +
                this.series.name + ': ' + this.y + '<br/>' +
                'Total: ' + this.point.stackTotal;
        }
    },

    plotOptions: {
        column: {
            stacking: 'normal'
        }
    },

    series: [{
        name: 'Category 1',
        data: [1, 3, 4, 7, 2],
        stack: 'male'
    }, {
        name: 'Category 2',
        data: [3, 4, 4, 2, 5],
        stack: 'male'
    }, {
        name: 'Category 3',
        data: [2, 5, 6, 2, 1],
        stack: 'female'
    }, {
        name: 'Category 4',
        data: [3, 0, 4, 4, 3],
        stack: 'female'
    }]
});
</script>