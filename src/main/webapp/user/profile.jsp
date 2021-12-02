<%--
  Created by IntelliJ IDEA.
  User: olesu
  Date: 14.11.2021
  Time: 14:53
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
    <fmt:message key="user.head" var="head"/>
    <title>${head}</title>
</head>
<%--User info--%>
<body>
<form action="${pageContext.request.contextPath}/user/profile" method="get">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
    </select>
</form>
<fmt:message key="user.logOut" var="logOut"/>
<a href="${pageContext.request.contextPath}/logout">${logOut}</a>
<fmt:message key="user.your_profile" var="your_profile"/>
<h1>${your_profile}</h1>
<fmt:message key="user.firstName" var="firstName"/>
<p>${firstName}: <c:out value="${user.firstName}"/></p>
<fmt:message key="user.lastName" var="lastName"/>
<p>${lastName}: <c:out value="${user.lastName}"/></p>
<fmt:message key="user.login" var="login"/>
<p>${login}: <c:out value="${user.login}"/></p>
<fmt:message key="user.email" var="email"/>
<p>${email}: <c:out value="${user.email}"/></p>
<fmt:message key="user.phoneNumber" var="phoneNumber"/>
<p>${phoneNumber}: <c:out value="${user.phoneNumber}"/></p>
<fmt:message key="user.accounts" var="accounts"/>
<p>${accounts}: <c:out value="${user.account}"/></p>
<fmt:message key="user.top_up_account" var="top_up_account"/>
<a href="${pageContext.request.contextPath}/user/account">${top_up_account}</a>

<fmt:message key="user.your_requests" var="your_requests"/>
<h1>${your_requests}</h1>
<fmt:message key="user.create_request" var="create_request"/>
<p><a href="${pageContext.request.contextPath}/user/request">${create_request}</a></p>
<%--Search--%>
<table>
    <form action="${pageContext.request.contextPath}/user/profile" method="get">
        <td>
            <select name="mastersFilter">
                <c:forEach var="name" items="${requestScope.masterNames}" step="1">
                    <option value="${name.key}" ${name.key.equals(nameOfMasterFilter) ? 'selected="selected"' : ''}>${name.value}</option>
                </c:forEach>
            </select>
        </td>

        <td>
            <select name="compilationStatusFilter">
                <c:forEach var="compilationStatus" items="${requestScope.compilationStatus}" step="1">
                    <option value="${compilationStatus.key}" ${compilationStatus.key == compilationStatusFilter ? 'selected' : ''}>${compilationStatus.value}</option>
                </c:forEach>
            </select>
        </td>

        <td>
            <select name="paymentStatusFilter">
                <c:forEach var="paymentStatus" items="${requestScope.paymentStatus}" step="1">
                    <option value="${paymentStatus.key}" ${paymentStatus.key == paymentStatusFilter ? 'selected' : ''}>${paymentStatus.value}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <fmt:message key="user.search" var="search"/>
            <button name="search">${search}</button>
        </td>
    </form>
    <form action="${pageContext.request.contextPath}/user/profile" method="get">
        <td>
            <fmt:message key="user.drop_filter" var="drop_filter"/>
            <button name="drop">${drop_filter}</button>
        </td>
    </form>
</table>

<%--User requests--%>
<table>
    <thead>
    <tr>
        <form action="${pageContext.request.contextPath}/user/profile" method="get">
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
                <fmt:message key="user.order" var="order"/>
                <button>${order}</button>
            </td>
        </form>
        <fmt:message key="user.description" var="description"/>
        <td>${description}</td>
        <fmt:message key="user.master" var="master"/>
        <td>${master}</td>
        <form action="${pageContext.request.contextPath}/user/profile" method="get">
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
                <fmt:message key="user.date" var="date"/>
                <button>${date}</button>
            </td>
        </form>
        <form action="${pageContext.request.contextPath}/user/profile" method="get">
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
                <fmt:message key="user.total_cost" var="total_cost"/>
                <button>${total_cost}</button>
            </td>
        </form>
        <fmt:message key="user.work_status" var="work_status"/>
        <td>${work_status}</td>
        <fmt:message key="user.payment_status" var="payment_status"/>
        <td>${payment_status}</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${requestScope.userRequests}" step="1">
        <tr>
            <td><c:out value="${request.id}"/></td>
            <td><c:out value="${request.description}"/></td>
            <c:forEach var="nameMaster" items="${requestScope.masterNames}" step="1">
                <c:if test="${request.master == nameMaster.key}">
                    <td><c:out value="${nameMaster.value}"/></td>
                </c:if>
            </c:forEach>
            <td><c:out value="${request.date}"/></td>
            <td><c:out value="${request.totalCost}"/> грн.</td>
            <c:forEach var="compilationStatus" items="${requestScope.compilationStatus}" step="1">
                <c:if test="${request.compilationStatusId == compilationStatus.key}">
                    <td><c:out value="${compilationStatus.value}"/></td>
                </c:if>
            </c:forEach>
            <c:forEach var="paymentStatus" items="${requestScope.paymentStatus}" step="1">
                <c:if test="${request.paymentStatusId == paymentStatus.key}">
                    <td><c:out value="${paymentStatus.value}"/></td>
                </c:if>
            </c:forEach>
            <c:choose>
                <c:when test="${request.paymentStatusId == 1}">
                    <form action="${pageContext.request.contextPath}/user/change/description" method="get">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <fmt:message key="user.change_description" var="change_description"/>
                            <button name="changeDescrip">${change_description}</button>
                        </td>
                    </form>

                    <form action="${pageContext.request.contextPath}/user/pay" method="post">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <input type="hidden" name="totalCost" value="${request.totalCost}">
                            <input type="hidden" name="userLogin" value="${request.userLogin}">
                            <input type="hidden" name="account" value="${user.account}">
                            <fmt:message key="user.pay" var="pay"/>
                            <button name="pay">${pay}</button>
                        </td>
                    </form>
                    <form action="${pageContext.request.contextPath}/user/cancel" method="post">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <fmt:message key="user.cancel" var="cancel"/>
                            <button name="cancel">${cancel}</button>
                        </td>
                    </form>
                </c:when>
                <c:when test="${request.paymentStatusId == 2 && request.compilationStatusId == 2}">
                    <form action="${pageContext.request.contextPath}/user/leave/feedback" method="get">
                        <td>
                            <input type="hidden" name="id" value="${request.id}">
                            <input type="hidden" name="loginMaster" value="${request.master}">
                            <fmt:message key="user.feedback" var="feedback"/>
                            <button name="feedback">${feedback}</button>
                        </td>
                    </form>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/user/profile">
    <input type="hidden" name="offset" value="${offset}">
    <input type="hidden" name="column" value="${column}">
    <input type="hidden" name="sortType" value="${sortType}">
    <input type="hidden" name="mastersFilter" value="${nameOfMasterFilter}">
    <input type="hidden" name="compilationStatusFilter" value="${compilationStatusFilter}">
    <input type="hidden" name="paymentStatusFilter" value="${paymentStatusFilter}">
    <fmt:message key="user.prev" var="prev"/>
    <button name="prev" value="3">${prev}</button>
    <fmt:message key="user.next" var="next"/>
    <button name="next" value="3">${next}</button>
</form>
</body>
</html>
