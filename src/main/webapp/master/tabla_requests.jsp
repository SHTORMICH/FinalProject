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
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>User</td>
        <td>Description</td>
        <td>Total cost</td>
        <td>Date</td>
        <td>Work status</td>
        <td>Payment status</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requestScope.requests}" step="1">
        <tr>
            <td><c:out value="${request.id}"/></td>

            <td><c:out value="${request.userLogin}"/></td>

            <td><c:out value="${request.description}"/></td>

            <td><c:out value="${request.totalCost}"/> грн</td>

            <td><c:out value="${request.master}"/> грн</td>

            <form action="${pageContext.request.contextPath}/master/refresh" method="post">
                <input type="hidden" name="id" value="${request.id}">

                <td>
                    <select name="compilationStatus">
                        <c:forEach var="compilationStatus" items="${requestScope.compilationStatuses}" step="1">
                            <option value="${compilationStatus.key}" ${request.compilationStatusId == compilationStatus.key ? 'selected' : ''}>${compilationStatus.value}</option>
                        </c:forEach>
                    </select>
                </td>

                <td><c:out value="${request.date}"/></td>

                <td>
                    <button name="refresh">Refresh</button>
                </td>
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
