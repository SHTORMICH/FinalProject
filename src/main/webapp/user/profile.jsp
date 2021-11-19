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
<p><a href="${pageContext.request.contextPath}/user/creatRequest">Account</a></p>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Description</td>
        <td>Master</td>
        <td>Date</td>
        <td>Total cost</td>
        <td>User</td>
        <td>Work status</td>
        <td>Payment status</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requestScope.userRequests}" step="1">
        <tr>
            <td><c:out value="${request.id}"/></td>
            <td><c:out value="${request.description}"/></td>
            <td><c:out value="${request.master}"/></td>
            <td><c:out value="${request.date}"/></td>
            <td><c:out value="${request.totalCost}"/></td>
            <td><c:out value="${request.userLogin}"/></td>
            <td><c:out value="${request.compilationStatusId}"/></td>
            <td><c:out value="${request.paymentStatusId}"/></td>
            <td><button>Pay</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
