<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 12.11.2021
  Time: 22:04
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
    <meta charset="UTF-8">
    <fmt:message key="registration.title" var="title"/>
    <title>${title}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="registration.headReg" var="headReg"/>
<h1>${headReg}</h1><br>
<form action="${pageContext.request.contextPath}/registration" method="POST">
    <fmt:message key="registration.login" var="login"/>
    ${login}: <input name="login" required/>
    <br><br>
    <fmt:message key="registration.email" var="email"/>
    ${email}: <input name="email" required/>
    <br><br>
    <fmt:message key="registration.password" var="password"/>
    ${password}: <input name="password" required/>
    <br><br>
    <fmt:message key="registration.firstName" var="firstName"/>
    ${firstName}: <input name="firstName" required/>
    <br><br>
    <fmt:message key="registration.lastName" var="lastName"/>
    ${lastName}: <input name="lastName" required/>
    <br><br>
    <fmt:message key="registration.phoneNumber" var="phoneNumber"/>
    ${phoneNumber}: <input name="phoneNumber" required/>
    <br><br>
    <fmt:message key="registration.submit" var="submit"/>
    <input type="submit" value="${submit}" required/>
</form>
<fmt:message key="registration.logIn" var="logIn"/>
<a href="${pageContext.request.contextPath}/login">${logIn}</a>
</body>
</html>
