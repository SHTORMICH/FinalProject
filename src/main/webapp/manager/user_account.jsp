<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 14:38
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
    <fmt:message key="manager.user_account.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/manager/change/user/account" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="manager.user_account.header" var="header"/>
<h1>${header}</h1>
<form method="post" action="${pageContext.request.contextPath}/manager/change/user/account">
    <input type="hidden" name="login" value="${login}">
    <fmt:message key="manager.user_account.top_up_the_account" var="top_up_the_account"/>
    <p>${top_up_the_account}: <input name="account" required/></p>
    <input class="button" type="submit" value="Update"/>
</form>
<fmt:message key="manager.user_account.back" var="back"/>
<p><a href="${pageContext.request.contextPath}/manager/users">${back}</a></p>
</body>
</html>
