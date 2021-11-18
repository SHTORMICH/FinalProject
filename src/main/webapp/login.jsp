<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 14.11.2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="form">
    <h1>Login</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/login">
        Login: <input type="text" required placeholder="login" name="login" required>
        <br><br>
        Password: <input type="password" required placeholder="password" name="password" required><br><br>
        <input class="button" type="submit" value="Войти">
    </form>
    <a href="${pageContext.request.contextPath}/registration">Registration</a>
</div>
</body>
</html>
