<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 23.11.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local"/>

<html>
<head>
    <fmt:message key="master.table_requests.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/table/requests" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="master.table_requests.logout" var="logout"/>
<a href="${pageContext.request.contextPath}/logout">${logout}</a>
<fmt:message key="master.table_requests.head" var="head"/>
<h1>${head}</h1>
<table>
    <thead>
    <tr>
        <fmt:message key="master.table_requests.id" var="id"/>
        <td>${id}</td>
        <fmt:message key="master.table_requests.user" var="user"/>
        <td>${user}</td>
        <fmt:message key="master.table_requests.description" var="description"/>
        <td>${description}</td>
        <fmt:message key="master.table_requests.total_cost" var="total_cost"/>
        <td>${total_cost}</td>
        <fmt:message key="master.table_requests.date" var="date"/>
        <td>${date}</td>
        <fmt:message key="master.table_requests.payment_status" var="payment_status"/>
        <td>${payment_status}</td>
        <fmt:message key="master.table_requests.work_status" var="work_status"/>
        <td>${work_status}</td>
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
                            <fmt:message key="master.table_requests.refresh" var="refresh"/>
                            <button name="refresh">${refresh}</button>
                        </td>
                    </c:when>
                </c:choose>
            </form>
            <form action="${pageContext.request.contextPath}/master/choose/request" method="get">
                <td>
                    <c:if test="${request.master.equals(\"Not assigned\")}">
                        <input type="hidden" name="id" value="${request.id}">
                        <fmt:message key="master.table_requests.choose" var="choose"/>
                        <button>${choose}</button>
                    </c:if>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
