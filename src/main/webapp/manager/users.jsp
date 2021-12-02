<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 12:00
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
    <fmt:message key="manager.users.user" var="user"/>
    <title>${user}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/manager/users" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="manager.users.user" var="user"/>
<h1>${user}</h1>
<fmt:message key="manager.users.logout" var="logout"/>
<a href="${pageContext.request.contextPath}/logout">${logout}</a>
<fmt:message key="manager.users.user_requests" var="user_requests"/>
<p><a href="${pageContext.request.contextPath}/manager/users/requests">${user_requests}</a></p>
<table>
    <thead>
    <tr>
        <fmt:message key="manager.users.firstName" var="firstName"/>
        <td><p>${firstName}</p></td>
        <fmt:message key="manager.users.lastName" var="lastName"/>
        <td><p>${lastName}</p></td>
        <fmt:message key="manager.users.login" var="login"/>
        <td><p>${login}</p></td>
        <fmt:message key="manager.users.email" var="email"/>
        <td><p>${email}</p></td>
        <fmt:message key="manager.users.phoneNumber" var="phoneNumber"/>
        <td><p>${phoneNumber}</p></td>
        <fmt:message key="manager.users.account" var="account"/>
        <td><p>${account}</p></td>
        <fmt:message key="manager.users.role" var="role"/>
        <td><p>${role}</p></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.users}" step="1">
        <c:choose>
            <c:when test="${user.login != 'Not assigned'}">
                <tr>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.phoneNumber}"/></td>
                    <td><c:out value="${user.account}"/></td>
                    <td>
                        <c:forEach var="role" items="${requestScope.roles}" step="1">
                            <c:if test="${user.roleId == role.key}">
                                <c:out value="${role.value}"/>
                            </c:if>
                        </c:forEach>
                    </td>
                    <form action="${pageContext.request.contextPath}/manager/change/user/account" method="get">

                        <td>
                            <fmt:message key="manager.users.update_account" var="update_account"/>
                            <button name="update">${update_account}</button>
                            <input type="hidden" name="login" value="${user.login}">
                        </td>
                    </form>
                    <form action="${pageContext.request.contextPath}/manager/user/delete" method="post">
                        <td>
                            <fmt:message key="manager.users.delete" var="delete"/>
                            <input type="hidden" name="login" value="${user.login}">
                            <button name="delete">${delete}</button>
                        </td>
                    </form>
                </tr>
            </c:when>
        </c:choose>
    </c:forEach>
    </tbody>
</table>
</body>
</html>





