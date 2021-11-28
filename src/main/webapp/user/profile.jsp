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
<a href="${pageContext.request.contextPath}/logout">LogOut</a>
<p>First Name: <c:out value="${user.firstName}"/></p>
<p>Last name: <c:out value="${user.lastName}"/></p>
<p>Username: <c:out value="${user.login}"/></p>
<p>Email: <c:out value="${user.email}"/></p>
<p>Phone number: <c:out value="${user.phoneNumber}"/></p>
<p>Account: <c:out value="${user.account}"/></p>
<p><a href="${pageContext.request.contextPath}/user/account">Top up account</a></p>
<p><a href="${pageContext.request.contextPath}/user/request">Creat request</a></p>
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
            <td><c:out value="${request.totalCost}"/> грн.</td>
            <td><c:out value="${request.userLogin}"/></td>
            <td><c:out value="${request.compilationStatusId}"/></td>
            <td><c:out value="${request.paymentStatusId}"/></td>
            <c:choose>
                <c:when test="${request.paymentStatusId == 1}">
                    <form action="${pageContext.request.contextPath}/user/change/description" method="get">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <button name="changeDescrip">Change description</button>
                        </td>
                    </form>

                    <form action="${pageContext.request.contextPath}/user/pay" method="post">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <input type="hidden" name="totalCost" value="${request.totalCost}">
                            <input type="hidden" name="userLogin" value="${request.userLogin}">
                            <input type="hidden" name="account" value="${user.account}">
                            <button name="pay">Pay</button>
                        </td>
                    </form>
                    <form action="${pageContext.request.contextPath}/user/cancel" method="post">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <button name="cancel">Cancel</button>
                        </td>
                    </form>
                </c:when>
                <c:when test="${request.paymentStatusId == 2 && request.compilationStatusId == 2}">
                    <form action="${pageContext.request.contextPath}/user/leave/feedback" method="get">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <input type="hidden" name="loginMaster" value="${request.master}">
                            <button name="feedback">Feedback</button>
                        </td>
                    </form>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
