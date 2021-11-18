<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 12.11.2021
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>
<h1>Registration</h1><br>
<form action="${pageContext.request.contextPath}/registration" method="POST">
    login: <input name="login" required/>
    <br><br>
    email: <input name="email" required/>
    <br><br>
    password: <input name="password" required/>
    <br><br>
    firstName: <input name="firstName" required/>
    <br><br>
    lastName: <input name="lastName" required/>
    <br><br>
    phoneNumber: <input name="phoneNumber" required/>
    <br><br>
    <input type="submit" value="Submit" required/>
</form>
<a href="${pageContext.request.contextPath}/login">LogIn</a>
</body>
</html>
