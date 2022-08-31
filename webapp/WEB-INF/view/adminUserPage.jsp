<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>All Users</title>
    <style>
        th, td {
            border-style: groove;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/adminUserPage" method="post">
<h1><fmt:message key="allUsers_table"/></h1>
<table class="tableService sortable">
    <thead>
    <tr>
        <th><fmt:message key="userId_table"/></th>
        <th><fmt:message key="userName_table"/></th>
        <th><fmt:message key="userLogin_table"/></th>
        <th><fmt:message key="userEmail_table"/></th>
        <th><fmt:message key="userAccessLevel_table"/></th>
        <th><fmt:message key="userFunds_table"/></th>
        <th><fmt:message key="userStatus_table"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="persons" scope="request" type="java.util.List"/>
    <c:forEach items="${persons}" var="a">
        <tr>
            <td>${a.id}</td>
            <td>${a.name}</td>
            <td>${a.login}</td>
            <td>${a.email}</td>
            <td>
                <c:if test="${a.accessLevel == 2}">
                    <fmt:message key="user_table"/>
                </c:if>
            </td>

            <td>${a.funds}</td>
            <td>
                <c:if test="${a.status == 1}">
                    <fmt:message key="user_unblocked"/>
                </c:if>

                <c:if test="${a.status == 2}">
                    <fmt:message key="user_blocked"/>
                </c:if>
            </td>

            <td>
                <c:if test="${a.status == 2}">
                    <a href=${pageContext.request.contextPath}/view/adminUserPage?person_id=${a.id}>
                        <button name="unblock"><fmt:message key="adminUnblock_button"/></button>
                    <input type="hidden" name="person_id" value="${a.id}">
                </c:if>
                <c:if test="${a.status == 1}">
                    <a href=${pageContext.request.contextPath}/view/adminUserPage?person_id=${a.id}>
                       <button name="block"><fmt:message key="adminBlock_button"/></button>
                    <input type="hidden" name="person_id" value="${a.id}">
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>

<a href=${pageContext.request.contextPath}/view/adminPage>
    <button name="home"><fmt:message key="adminPage_button"/></button>
</a>

</body>
</html>
