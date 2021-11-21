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
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>User</td>
        <td>Description</td>
        <td>Total cost</td>
        <td>Master</td>
        <td>Work status</td>
        <td>Payment status</td>
        <td>Date</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requestScope.requests}" step="1">
        <tr>
            <td><c:out value="${request.id}"/></td>

            <td><c:out value="${request.userLogin}"/></td>

            <td><c:out value="${request.description}"/></td>

            <td><c:out value="${request.totalCost}"/></td>

            <form action="${pageContext.request.contextPath}/manager/user/choose/master" method="post">
                <td>
                    <label for="masters"></label>
                    <select name="masters" id="masters">
                        <c:forEach var="name" items="${requestScope.mastersName}" step="1">
                            <option value="${name.key}">${name.value}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="id" value="${request.id}">
                </td>

                <td>
                    <select name="compilationStatus">
                        <c:forEach var="compilationStatus" items="${requestScope.compilationStatus}" step="1">
                            <option value="${compilationStatus.key}">${compilationStatus.value}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${request.paymentStatusId == 1}">
                            <c:out value="Waiting for pay"/>
                        </c:when>
                        <c:when test="${request.paymentStatusId == 2}">
                            <c:out value="Paid"/>
                        </c:when>
                        <c:when test="${request.paymentStatusId == 3}">
                            <c:out value="Canceled"/>
                        </c:when>
                    </c:choose>
                </td>
                <td><c:out value="${request.date}"/></td>

                <td>
                    <button name="refresh">Refresh</button>
                </td>
            </form>
            <c:choose>
                <c:when test="${request.totalCost == 1}">
                    <form action="${pageContext.request.contextPath}/manager/user/choose/master" method="post">
                        <td>
                            <button>Edit total cost</button>
                        </td>
                    </form>
                </c:when>
                <c:when test="${request.totalCost > 1}">
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
