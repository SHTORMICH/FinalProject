<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/account">
    <p>Your account: <c:out value="${user.account}"/></p>
    <p>Top up the account: <input name="account" required/></p>
    <input class="button" type="submit" value="Update"/>
</form>
<p><a href="${pageContext.request.contextPath}/user/profile">Back</a></p>
</body>
</html>
