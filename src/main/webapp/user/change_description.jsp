<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/change/description">
    <input type="hidden" name="id" value="${id}">
    <p>Change description: </p>
    <p><textarea style="resize:none" maxlength="10000" cols="40" rows="10" name="description" required></textarea></p>
    <input class="button" type="submit" value="Send"/>
</form>
<p><a href="${pageContext.request.contextPath}/user/profile">Back</a></p>
</body>
</html>
