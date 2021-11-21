<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/manager/change/user/account">
    <input type="hidden" name="login" value="${login}">
    <p>Top up the account: <input name="account" required/></p>
    <input class="button" type="submit" value="Update"/>
</form>
<p><a href="${pageContext.request.contextPath}/manager/users">Back</a></p>
</body>
</html>
