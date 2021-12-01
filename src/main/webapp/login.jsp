<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 14.11.2021
  Time: 14:57
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
    <fmt:message key="login.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="login.head" var="head"/>
<h1>${head}</h1>
<div class="form">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <fmt:message key="login.login_form.login" var="login"/>
        ${login}: <input type="text" placeholder="login" name="login" required>
        <br><br>
        <fmt:message key="login.login_form.password" var="password"/>
        ${password}: <input type="password" placeholder="password" name="password" required><br><br>
        <fmt:message key="login.login_form.submit" var="submit"/>
        <input class="button" type="submit" value="${submit}">
    </form>
    <fmt:message key="login.login_form.reg_ref" var="reg"/>
    <a href="${pageContext.request.contextPath}/registration">${reg}</a>
</div>
</body>
</html>
