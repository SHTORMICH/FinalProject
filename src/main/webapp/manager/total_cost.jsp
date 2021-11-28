<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 20.11.2021
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/manager/change/totalCost">
    <input type="hidden" name="id" value="${id}">
    <p>Change total cost: <input name="totalCost" required/></p>
    <input class="button" type="submit" value="Change"/>
</form>
<p><a href="${pageContext.request.contextPath}/manager/users/requests">Back</a></p>
</body>
</html>
