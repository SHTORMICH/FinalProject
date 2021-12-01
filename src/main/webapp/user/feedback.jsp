<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 21.11.2021
  Time: 12:17
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
    <fmt:message key="user.feedback.header" var="header"/>
    <title>${header}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/leave/feedback" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<form method="post" action="${pageContext.request.contextPath}/user/leave/feedback">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="loginMaster" value="${loginMaster}">
    <fmt:message key="user.feedback.add_feedback" var="add_feedback"/>
    <p>${add_feedback}: </p>
    <p><textarea style="resize:none" maxlength="10000" cols="40" rows="10" name="feedback" required></textarea></p>
    <input class="button" type="submit" value="Send"/>
</form>
<fmt:message key="user.feedback.back" var="back"/>
<p><a href="${pageContext.request.contextPath}/user/profile">${back}</a></p>
</body>
</html>
