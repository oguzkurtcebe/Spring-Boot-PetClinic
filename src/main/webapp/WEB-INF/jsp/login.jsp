<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>PetClinic Login Page</h1>
<form action="login" method="post">
     UserName:<input type="text" name="username"/><br/>
     Password:<input type="password" name="password"/><br/>
     Remember Me:<input type="checkbox" name="remember-me"/>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     <input type="submit" value="Login"/>
     
     <font color="red">
       <c:if test="${not empty param.loginFailed }">
         <c:out value="Login Failed, Incorrect Username or Password"></c:out>
       
       </c:if>
  
  
     </font>

</form>
</body>
</html>