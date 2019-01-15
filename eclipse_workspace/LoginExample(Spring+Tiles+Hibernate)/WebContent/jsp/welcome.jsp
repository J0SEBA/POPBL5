
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
            <tr>
                <td>Welcome ${sessionScope.user.id}</td>
                
            </tr>
            <tr>
              <td>username:<c:out value="${sessionScope.user.username}"/></td>
            </tr>
            <tr>
            </tr>
            <tr>
                <td><a href="index.jsp">Home</a>
                </td>
            </tr>
        </table>