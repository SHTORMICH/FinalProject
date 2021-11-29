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
<%--User info--%>
<body>
<a href="${pageContext.request.contextPath}/logout">LogOut</a>
<h1>Profile</h1>
<p>First Name: <c:out value="${user.firstName}"/></p>
<p>Last name: <c:out value="${user.lastName}"/></p>
<p>Username: <c:out value="${user.login}"/></p>
<p>Email: <c:out value="${user.email}"/></p>
<p>Phone number: <c:out value="${user.phoneNumber}"/></p>
<p>Account: <c:out value="${user.account}"/></p>
<p><a href="${pageContext.request.contextPath}/user/account">Top up account</a></p>
<p><a href="${pageContext.request.contextPath}/user/request">Creat request</a></p>

<%--Search--%>
<table>
    <form action="${pageContext.request.contextPath}/user/profile" method="get">
        <td>
            <select name="mastersFilter">
                <c:forEach var="name" items="${requestScope.masterNames}" step="1">
                    <option value="${name.key}" ${name.key.equals(nameOfMasterFilter) ? 'selected="selected"' : ''}>${name.value}</option>
                </c:forEach>
            </select>
        </td>

        <td>
            <select name="compilationStatusFilter">
                <c:forEach var="compilationStatus" items="${requestScope.compilationStatus}" step="1">
                    <option value="${compilationStatus.key}" ${compilationStatus.key == compilationStatusFilter ? 'selected' : ''}>${compilationStatus.value}</option>
                </c:forEach>
            </select>
        </td>

        <td>
            <select name="paymentStatusFilter">
                <c:forEach var="paymentStatus" items="${requestScope.paymentStatus}" step="1">
                    <option value="${paymentStatus.key}" ${paymentStatus.key == paymentStatusFilter ? 'selected' : ''}>${paymentStatus.value}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <button name="search">Search</button>
        </td>
    </form>
    <form action="${pageContext.request.contextPath}/user/profile" method="get">
        <td>
            <button name="drop">Drop filter</button>
        </td>
    </form>
</table>

<%--User requests--%>
<table>
    <thead>
    <tr>
        <form action="${pageContext.request.contextPath}/user/profile" method="get">
            <input type="hidden" name="column" value="id">
            <input type="hidden" name="mastersFilter" value="${nameOfMasterFilter}">
            <input type="hidden" name="compilationStatusFilter" value="${compilationStatusFilter}">
            <input type="hidden" name="paymentStatusFilter" value="${paymentStatusFilter}">

            <c:choose>
                <c:when test="${sortType == null || sortType == 'DESC'}">
                    <input type="hidden" name="sortType" value="ASC">
                </c:when>
                <c:when test="${sortType == 'ASC'}">
                    <input type="hidden" name="sortType" value="DESC">
                </c:when>
            </c:choose>
            <td>
                <button>Order</button>
            </td>
        </form>
        <td>Description</td>
        <td>Master</td>
        <form action="${pageContext.request.contextPath}/user/profile" method="get">
            <input type="hidden" name="column" value="date">
            <input type="hidden" name="mastersFilter" value="${nameOfMasterFilter}">
            <input type="hidden" name="compilationStatusFilter" value="${compilationStatusFilter}">
            <input type="hidden" name="paymentStatusFilter" value="${paymentStatusFilter}">
            <c:choose>
                <c:when test="${sortType == null || sortType == 'DESC'}">
                    <input type="hidden" name="sortType" value="ASC">
                </c:when>
                <c:when test="${sortType == 'ASC'}">
                    <input type="hidden" name="sortType" value="DESC">
                </c:when>
            </c:choose>
            <td>
                <button>Date</button>
            </td>
        </form>
        <form action="${pageContext.request.contextPath}/user/profile" method="get">
            <input type="hidden" name="column" value="total_cost">
            <input type="hidden" name="mastersFilter" value="${nameOfMasterFilter}">
            <input type="hidden" name="compilationStatusFilter" value="${compilationStatusFilter}">
            <input type="hidden" name="paymentStatusFilter" value="${paymentStatusFilter}">
            <c:choose>
                <c:when test="${sortType == null || sortType == 'DESC'}">
                    <input type="hidden" name="sortType" value="ASC">
                </c:when>
                <c:when test="${sortType == 'ASC'}">
                    <input type="hidden" name="sortType" value="DESC">
                </c:when>
            </c:choose>
            <td>
                <button>Total cost</button>
            </td>
        </form>
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
            <c:forEach var="nameMaster" items="${requestScope.masterNames}" step="1">
                <c:if test="${request.master == nameMaster.key}">
                    <td><c:out value="${nameMaster.value}"/></td>
                </c:if>
            </c:forEach>
            <td><c:out value="${request.date}"/></td>
            <td><c:out value="${request.totalCost}"/> грн.</td>
            <c:forEach var="compilationStatus" items="${requestScope.compilationStatus}" step="1">
                <c:if test="${request.compilationStatusId == compilationStatus.key}">
                    <td><c:out value="${compilationStatus.value}"/></td>
                </c:if>
            </c:forEach>
            <c:forEach var="paymentStatus" items="${requestScope.paymentStatus}" step="1">
                <c:if test="${request.paymentStatusId == paymentStatus.key}">
                    <td><c:out value="${paymentStatus.value}"/></td>
                </c:if>
            </c:forEach>
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
<form action="${pageContext.request.contextPath}/user/profile">
    <input type="hidden" name="offset" value="${offset}">
    <input type="hidden" name="column" value="${column}">
    <input type="hidden" name="sortType" value="${sortType}">
    <input type="hidden" name="mastersFilter" value="${nameOfMasterFilter}">
    <input type="hidden" name="compilationStatusFilter" value="${compilationStatusFilter}">
    <input type="hidden" name="paymentStatusFilter" value="${paymentStatusFilter}">
    <button name="prev" value="3">Prev</button>
    <button name="next" value="3">Next</button>
</form>
</body>
</html>
