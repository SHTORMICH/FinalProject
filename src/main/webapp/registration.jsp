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
<form action="reg" method="POST">
    login: <input name="login" />
    <br><br>
    email: <input name="email" />
    <br><br>
    password: <input name="password" />
    <br><br>
    firstName: <input name="firstName" />
    <br><br>
    lastName: <input name="lastName" />
    <br><br>
    phoneNumber: <input name="phoneNumber" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
