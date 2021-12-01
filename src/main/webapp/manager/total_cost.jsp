<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 20.11.2021
  Time: 20:17
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
    <fmt:message key="manager.total_cost.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/manager/change/totalCost" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="manager.total_cost.header" var="header"/>
<h1>${header}</h1>
<form method="post" action="${pageContext.request.contextPath}/manager/change/totalCost">
    <input type="hidden" name="id" value="${id}">
    <fmt:message key="manager.total_cost" var="total_cost"/>
    <p>${total_cost}: <input name="totalCost" required/></p>
    <input class="button" type="submit" value="Change"/>
</form>
<fmt:message key="manager.total_cost.back" var="back"/>
<p><a href="${pageContext.request.contextPath}/manager/users/requests">${back}</a></p>
</body>
</html>
