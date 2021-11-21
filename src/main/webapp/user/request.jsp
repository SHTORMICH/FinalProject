<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 18.11.2021
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creat Request</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/request" >
    <p>Your description: </p>
    <p><textarea style="resize:none" maxlength="10000" cols="40" rows="10" name="description" required></textarea></p>
    <p>Cost: <input name="total_cost"/></p><br>
    <input class="button" type="submit" value="Send"/>
</form>
<p><a href="${pageContext.request.contextPath}/user/profile">Back</a></p>
</body>
</html>
