
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="align-content-center">
 <form:form id="regForm" modelAttribute="userRegister" action="registerProcess.html" method="post" class="form-signin col-lg-4 align-middle mx-auto">
             
             
             
             
             <h1 class="h3 mb-3 font-weight-normal" align="center">Register</h1>
      
      <label for="inputFirstname" class="sr-only">First name</label>
      <form:input  path="firstName" name="firstName" id="firstName" class="form-control my-2" placeholder="First name" required="" />
     
      <label for="inputSecondname" class="sr-only">Second name</label>
      <form:input  path="secondName" name="secondName" id="secondName" class="form-control my-2" placeholder="Second name" required="" />
      
      <label for="inputEmail" class="sr-only">Email</label>      
      <form:input path="email" name="email" id="email"  class="form-control mt-1" placeholder="Email" required=""/>
      
     <label for="inputGender" class="mt-1">Gender</label>
      <form:select path="gender" name="gender" id="gender" class="form-control mb-1" required="">
      		<option value="">-</option>
			<option value="Male">Male</option>
			<option value="Female">Female</option>
	  </form:select>
      
      <label for="inputUsername" class="mt-1">Birth date</label>
      <form:input path="birthDate" name="birthDate" id="birthDate" type="date" class="form-control" min="1900-01-01" max="2019-01-21" required=""/>
          
      <label for="inputUsername" class="sr-only">Username</label>
      <form:input  path="username" name="username" id="username" class="form-control my-2" placeholder="Username" required=""/>
      
      <label for="inputPassword" class="sr-only ">Password</label>      
      <form:password path="password" name="password" id="password"  class="form-control my-2" placeholder="Password" required=""/>
      

      
      <form:button class="btn btn-lg btn-primary btn-block mt-3" type="submit" id="register" name="register">Register</form:button>
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
                <!--  <table align="center">
                    <tr>
                        <td>
                            <form:label path="username">Username</form:label>
                        </td>
                        <td>
                            <form:input path="username" name="username" id="username" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Password</form:label>
                        </td>
                        <td>
                            <form:password path="password" name="password" id="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="firstName">First Name</form:label>
                        </td>
                        <td>
                            <form:input path="firstName" name="firstname" id="firstname" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="secondName">Second Name</form:label>
                        </td>
                        <td>
                            <form:input path="secondName" name="secondname" id="secondname" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td>
                            <form:button id="register" name="register">Register</form:button>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td></td>
                        <td><a href="index.jsp">Home</a>
                        </td>
                    </tr>
                </table>-->
            </form:form>
 
            </div>
