<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local"/>

<html >
<head>
    <fmt:message key="manager.users_requests.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="manager.users_requests.headUR" var="headUR"/>
<h1>${headUR}</h1>
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
            <fmt:message key="manager.users_requests.search" var="search"/>
            <button name="search">${search}</button>
        </td>
    </form>
    <form action="${pageContext.request.contextPath}/manager/users/requests" method="get">
        <td>
            <fmt:message key="manager.users_requests.drop" var="drop"/>
            <button name="drop">${drop}</button>
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
                <fmt:message key="manager.users_requests.order" var="order"/>
                <button>${order}</button>
            </td>
        </form>
        <fmt:message key="manager.users_requests.user" var="user"/>
        <td>${user}</td>
        <fmt:message key="manager.users_requests.description" var="description"/>
        <td>${description}</td>
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
                <fmt:message key="manager.users_requests.total_cost" var="total_cost"/>
                <button>${total_cost}</button>
            </td>
        </form>
        <fmt:message key="manager.users_requests.master" var="master"/>
        <td>${master}</td>
        <fmt:message key="manager.users_requests.work_status" var="work_status"/>
        <td>${work_status}</td>
        <fmt:message key="manager.users_requests.payment_status" var="payment_status"/>
        <td>${payment_status}</td>
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
                <fmt:message key="manager.users_requests.date" var="date"/>
                <button>${date}</button>
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
                    <fmt:message key="manager.users_requests.refresh" var="refresh"/>
                    <button name="refresh">${refresh}</button>
                </td>

            </form>

            <td>
                <form action="${pageContext.request.contextPath}/manager/change/totalCost" method="get">
                    <input type="hidden" name="id" value="${request.id}">
                    <fmt:message key="manager.users_requests.edit_total_cost" var="edit_total_cost"/>
                    <button name="totalCost">${edit_total_cost}</button>
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
    <fmt:message key="manager.users_requests.prev" var="prev"/>
    <button name="prev" value="3">${prev}</button>
    <fmt:message key="manager.users_requests.next" var="next"/>
    <button name="next" value="3">${next}</button>
</form>
</body>
</html>
