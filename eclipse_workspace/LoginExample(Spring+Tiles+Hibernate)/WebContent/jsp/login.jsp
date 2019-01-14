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
    

           <!--     <table >
                    <tr>
                        <td>
                            <form:label path="username">Username: </form:label>
                        </td>
                        <td>
                            <form:input path="username" name="username" id="username" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Password:</form:label>
                        </td>
                        <td>
                            <form:password path="password" name="password" id="password" />
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                            <form:button id="login" name="login">Login</form:button>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td></td>
                        <td>
                        </td>
                    </tr>
                </table>
         
            <table >
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>  --> 
         
      </form:form>
        </div>
   