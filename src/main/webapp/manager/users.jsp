<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Users</h1>
<a href="${pageContext.request.contextPath}/logout">LogOut</a>
<p><a href="${pageContext.request.contextPath}/manager/users/requests">Users requests</a></p>
<table>
    <thead>
    <tr>
        <td><p>First Name</p></td>
        <td><p>Last name</p></td>
        <td><p>Username</p></td>
        <td><p>Email</p></td>
        <td><p>Phone number</p></td>
        <td><p>Account</p></td>
        <td><p>Role Id</p></td>
        <td>Update account</td>
        <td>Delete User</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.users}" step="1">
        <tr>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.phoneNumber}"/></td>
            <td><c:out value="${user.account}"/></td>
            <td><c:out value="${user.roleId}"/></td>
            <form action="${pageContext.request.contextPath}/manager/change/user/account" method="get">
            <td>
                <button name="update">Update</button>
                <input type="hidden" name="login" value="${user.login}">
            </td>
            </form>
            <form action="${pageContext.request.contextPath}/manager/user/delete" method="post">
                <td>
                    <input type="hidden" name="login" value="${user.login}">
                    <button name="delete">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>





