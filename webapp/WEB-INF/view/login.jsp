<%@ page language="java"
         contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8"
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Login Page</title>
</head>

<body>

<a href="${pageContext.request.contextPath}/view/language/login?language=EN">
    EN
</a>
<a href="${pageContext.request.contextPath}/view/language/login?language=UA">
    UA
</a>

<form action="${pageContext.request.contextPath}/view/login" method="post">
    <fmt:message key="loginForm"/>
<p>
    <label>
        <input class="profileEditorFields loginField" type="text" required
               placeholder=Login
               name="login">
    </label>
</p>

    <fmt:message key="passForm"/>
<p>
    <label>
        <input class="profileEditorFields loginField" type="password" required
               placeholder=Password
               name="password">
    </label>
</p>

<a href=${pageContext.request.contextPath}/view/login>
    <button><fmt:message key="login_button"/></button>
</a>


<c:if test="${requestScope.notFound}">
    <div class="w3-container">
        <p><fmt:message key="invalidData"/></p>
    </div>
</c:if>

<c:if test="${requestScope.wrongData}">
    <div class="w3-container">
        <fmt:message key="incorrectData"/>
    </div>
</c:if>
</form>
</body>
</html>
