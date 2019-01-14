
<div class="container">
	
		<div class="row">
		
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center"><h1>PAYMENT</h1></div>
			</div>
			
			<div class="col-lg-6 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center">
					<div class="radio">
						<label><input type="radio" name="optRadio" onclick = "dis('credit')"checked>Credit Card</label>
					</div>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center">
					<div class="radio">
						<label><input type="radio" name="optRadio" onclick = "dis('payPal')">PayPal</label>
					</div>
				</div>
			</div>
	
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group row">
					<label for="credit">Credit number:</label>
					<input type="text" class="form-control" maxlength ="16" id ="credit">
				</div>
			</div>
				
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="form-group row">
					<div class = "col-xs-12">
						<label for="cvv">CVV:</label>
						<input type="text" class="form-control" maxlength ="3" id = "cvv">
					</div>
				</div>
			</div>
				
			
		</div>
		
	</div>
	
	
	<div class="d-flex justify-content-center">
		<button type="button" class="btn btn-primary btn-lg, info">CONFIRM</button>
	</div>
	
	<div class="d-flex justify-content-center">
		<button type="button" class="btn btn-primary btn-lg, info">CANCEL</button>
	</div>