<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 23.11.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/logout">LogOut</a>
<h1>Table of requests</h1>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>User</td>
        <td>Description</td>
        <td>Total cost</td>
        <td>Date</td>
        <td>Payment status</td>
        <td>Work status</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requestScope.requests}" step="1">
        <tr>
            <td><c:out value="${request.id}"/></td>

            <td><c:out value="${request.userLogin}"/></td>

            <td><c:out value="${request.description}"/></td>

            <td><c:out value="${request.totalCost}"/> грн</td>

            <td><c:out value="${request.date}"/></td>

            <c:forEach var="paymentStatus" items="${requestScope.paymentStatus}" step="1">
                <c:if test="${request.paymentStatusId == paymentStatus.key}">
                    <td><c:out value="${paymentStatus.value}"/></td>
                </c:if>
            </c:forEach>

            <form action="${pageContext.request.contextPath}/master/refresh" method="post">
                <input type="hidden" name="id" value="${request.id}">

                <c:choose>
                    <c:when test="${request.paymentStatusId == 1 || request.paymentStatusId == 3 || request.compilationStatusId == 2}">
                        <td>
                            <select disabled name="compilationStatus">
                                <c:forEach var="compilationStatus" items="${requestScope.compilationStatuses}" step="1">
                                    <option value="${compilationStatus.key}" ${request.compilationStatusId == compilationStatus.key ? 'selected' : ''}>${compilationStatus.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </c:when>
                    <c:when test="${request.paymentStatusId == 2}">
                        <td>
                            <select name="compilationStatus">
                                <c:forEach var="compilationStatus" items="${requestScope.compilationStatuses}" step="1">
                                    <option value="${compilationStatus.key}" ${request.compilationStatusId == compilationStatus.key ? 'selected' : ''}>${compilationStatus.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <button name="refresh">Refresh</button>
                        </td>
                    </c:when>
                </c:choose>
            </form>
            <form action="${pageContext.request.contextPath}/master/choose/request" method="get">
                <td>
                    <c:if test="${request.master.equals(\"Not assigned\")}">
                        <input type="hidden" name="id" value="${request.id}">
                        <button>Choose</button>
                    </c:if>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
