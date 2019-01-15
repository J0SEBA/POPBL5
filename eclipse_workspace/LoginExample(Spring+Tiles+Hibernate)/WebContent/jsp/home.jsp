
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	
		<div class="row">
		
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center"><h1>SEARCH PRODUCT</h1></div>
			</div>
	

			<div class="col-lg-6 col-md-6">
				<div class="d-flex justify-content-center">
					<button type="button" class="btn btn-primary btn-lg , buttons" onclick="redirect('ELECTRONICS')">ELECTRONICS</button>
					<button type="button" class="btn btn-primary btn-lg , buttons" onclick="redirect('VIDEO GAMES')">VIDEOGAMES</button>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6">
				<div class="d-flex justify-content-center">
					<button type="button" class="btn btn-primary btn-lg, buttons" onclick="redirect('COMPUTING')">COMPUTING</button>
					<button type="button" class="btn btn-primary btn-lg , buttons" onclick="redirect('CINEMA, TV and MUSIC')">CINEMA,TV </br>and MUSIC</button>
				</div>
			</div>


			<div class="col-lg-6 col-md-6">
				<div class="d-flex justify-content-center">
					 <button type="button" class="btn btn-primary btn-lg, buttons" onclick="redirect('FASHION')">FASHION</button>
					 <button type="button" class="btn btn-primary btn-lg, buttons" onclick="redirect('FURNITURE')">FURNITURE</button>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6">
				<div class="d-flex justify-content-center">
					 <button type="button" class="btn btn-primary btn-lg, buttons" onclick="redirect('BOOKS')">BOOKS</button>
					 <button type="button" class="btn btn-primary btn-lg, buttons"  onclick="redirect('SPORTS')">SPORTS</button>
				</div>
			</div>
			
			
			<div class="col-lg-6 col-md-6 col-sm-6 col-6">
				<div class="d-flex justify-content-center">
					 <button type="button" class="btn btn-primary btn-lg, buttons" onclick="redirect('HOME and GARDEN')">HOME and GARDEN</button>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6 col-sm-6 col-6">
				<div class="d-flex justify-content-center">
					 <button type="button" class="btn btn-primary btn-lg, buttons" onclick="redirect('SCHOOL SUPPLIES')">SCHOOL SUPPLIES</button>
				</div>
			</div>
			
			
		</div>
	</div>
	
	<script>
		function redirect(category){
	   	 	window.location.href = "categoryView.html?category="+category;
		};
</script>