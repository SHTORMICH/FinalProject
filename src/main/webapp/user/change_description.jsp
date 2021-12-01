<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 19.11.2021
  Time: 17:33
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
    <fmt:message key="user.description.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/change/description" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<form method="post" action="${pageContext.request.contextPath}/user/change/description">
    <input type="hidden" name="id" value="${id}">
    <fmt:message key="user.description.change_description" var="change_description"/>
    <p>${change_description}: </p>
    <p><textarea style="resize:none" maxlength="10000" cols="40" rows="10" name="description" required></textarea></p>
    <input class="button" type="submit" value="Send"/>
</form>
<fmt:message key="user.description.back" var="back"/>
<p><a href="${pageContext.request.contextPath}/user/profile">${back}</a></p>
</body>
</html>
