<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

   
   <div class="align-content-center ">
	<form:form id="loginForm" modelAttribute="login" action="loginProcess.html" method="post" class="form-signin col-lg-4 align-middle mx-auto" >
	
          
      <h1 class="h3 mb-3 font-weight-normal" align="center">Please sign in</h1>
      
      <label for="inputUsername" class="sr-only">Username</label>
      <form:input  path="username" name="username" id="username" class="form-control mt-2" placeholder="Username" required="" autofocus=""/>
      
      <label for="inputPassword" class="sr-only">Password</label>      
      <form:password path="password" name="password" id="password"  class="form-control mt-2" placeholder="Password" required=""/>
      <form:button class="btn btn-lg btn-primary btn-block mt-3" type="submit" id="login" name="login">Sign in</form:button>
    
      <label style="font-style: italic; color: red;">${message}</label>
    
         
      </form:form>
        </div>
   