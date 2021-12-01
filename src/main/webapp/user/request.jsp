<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 18.11.2021
  Time: 18:47
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
    <fmt:message key="user.request.create_request" var="create_request"/>
    <title>${create_request}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/request" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<form method="post" action="${pageContext.request.contextPath}/user/request" >
    <fmt:message key="user.request.your_description" var="your_description"/>
    <p>${your_description}: </p>
    <p><textarea style="resize:none" maxlength="10000" cols="40" rows="10" name="description" required></textarea></p>
    <fmt:message key="user.request.cost" var="cost"/>
    <p>${cost}: <input name="total_cost"/></p><br>
    <input class="button" type="submit" value="Send"/>
</form>
<fmt:message key="user.request.back" var="back"/>
<p><a href="${pageContext.request.contextPath}/user/profile">${back}</a></p>
</body>
</html>
