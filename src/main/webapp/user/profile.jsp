<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 14.11.2021
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
<p>First Name: <c:out value="${user.firstName}"/></p>
<p>Last name: <c:out value="${user.lastName}"/></p>
<p>Username: <c:out value="${user.login}"/></p>
<p>Email: <c:out value="${user.email}"/></p>
<p>Phone number: <c:out value="${user.phoneNumber}"/></p>
<p>Account: <c:out value="${user.account}"/></p>
<br>
<p><a href="${pageContext.request.contextPath}/user/creatRequest">Creat request</a></p>

</body>
</html>
