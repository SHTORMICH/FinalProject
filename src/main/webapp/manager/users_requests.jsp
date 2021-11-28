<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users requests</title>
</head>
<body>
<h1>Users requests</h1>
<p><a href="${pageContext.request.contextPath}/logout">LogOut</a></p>
<p><a href="${pageContext.request.contextPath}/manager/users">Users</a></p>
<%--Request table filters--%>
<table>
    <form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
        <td>
            <select name="mastersFilter">
                <c:forEach var="name" items="${requestScope.mastersNames}" step="1">
                    <option value="${name.key}" ${name.key.equals(nameOfMasterFilter) ? 'selected="selected"' : ''}>${name.value}</option>
                </c:forEach>
            </select>
        </td>

        <td>
            <select name="compilationStatusFilter">
                <c:forEach var="compilationStatus" items="${requestScope.compilationStatuses}" step="1">
                    <option value="${compilationStatus.key}" ${compilationStatus.key == compilationStatusFilter ? 'selected' : ''}>${compilationStatus.value}</option>
                </c:forEach>
            </select>
        </td>

        <td>
            <select name="paymentStatusFilter">
                <c:forEach var="paymentStatus" items="${requestScope.paymentStatuses}" step="1">
                    <option value="${paymentStatus.key}" ${paymentStatus.key == paymentStatusFilter ? 'selected' : ''}>${paymentStatus.value}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <button name="search">Search</button>
        </td>
    </form>
    <form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
        <td>
            <button name="drop">Drop filter</button>
        </td>
    </form>
</table>
<table>
    <%--Request table header with sort buttons--%>
    <thead>
    <tr>
        <form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
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
        <td>User</td>
        <td>Description</td>
        <form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
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
        <td>Master</td>
        <td>Work status</td>
        <td>Payment status</td>
        <form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
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
    </tr>
    </thead>
    <%--Request body table--%>
    <tbody>
    <c:forEach var="request" items="${requestScope.requests}" step="1">
        <tr>
            <td><c:out value="${request.id}"/></td>

            <td><c:out value="${request.userLogin}"/></td>

            <td><c:out value="${request.description}"/></td>

            <td><c:out value="${request.totalCost}"/> грн</td>

            <form action="${pageContext.request.contextPath}/manager/refresh" method="post">
                <input type="hidden" name="id" value="${request.id}">
                <td>
                    <select name="masters">
                        <c:forEach var="name" items="${requestScope.mastersNames}" step="1">
                            <option value="${name.key}" ${request.master.equals(name.key) ? 'selected' : ''} >${name.value}</option>
                        </c:forEach>
                    </select>
                </td>

                <td>
                    <select name="compilationStatus">
                        <c:forEach var="compilationStatus" items="${requestScope.compilationStatuses}" step="1">
                            <option value="${compilationStatus.key}" ${request.compilationStatusId == compilationStatus.key ? 'selected' : ''}>${compilationStatus.value}</option>
                        </c:forEach>
                    </select>
                </td>

                <td>
                    <select name="paymentStatus">
                        <c:forEach var="paymentStatus" items="${requestScope.paymentStatuses}" step="1">
                            <option value="${paymentStatus.key}" ${request.paymentStatusId == paymentStatus.key ? 'selected' : ''}>${paymentStatus.value}</option>
                        </c:forEach>
                    </select>
                </td>

                <td><c:out value="${request.date}"/></td>

                <td>
                    <button name="refresh">Refresh</button>
                </td>

            </form>

            <td>
                <form action="${pageContext.request.contextPath}/manager/change/totalCost" method="get">
                    <button name="totalCost">Edit total cost</button>
                    <input type="hidden" name="id" value="${request.id}">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/manager/users/requests">
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
