<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 14.11.2021
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
<table>
    <tr>
        <th>First Name</th>
        <th>Last name</th>
        <th>Username</th>
        <th>Email</th>
        <th>Phone number</th>
        <th>Account</th>
    </tr>
    <tr>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.phoneNumber}"/></td>
        <td><c:out value="${user.account}"/></td>
    </tr>
</table>
</body>
</html>
