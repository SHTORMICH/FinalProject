<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 21.11.2021
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/leave/feedback">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="loginMaster" value="${loginMaster}">
    <p>Add feedback: </p>
    <p><textarea style="resize:none" maxlength="10000" cols="40" rows="10" name="feedback" required></textarea></p>
    <input class="button" type="submit" value="Send"/>
</form>
<p><a href="${pageContext.request.contextPath}/user/profile">Back</a></p>
</body>
</html>
