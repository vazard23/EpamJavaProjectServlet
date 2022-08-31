<%@ page language="java"
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/loginStyle.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
</head>

<body>

    <div class="login-div">
        <form action="${pageContext.request.contextPath}/view/login" method="post">
        <div class="title">Login</div>
        <div class="sub-title">Enter your page</div>
        <div class="fields">
            <div class="username">
                <span class="material-icons-sharp">person</span>
                <input type="username" class="user-input" placeholder="Username" name="login">
            </div>
            <div class="password">
                <span class="material-icons-sharp">lock</span>
                <input type="password" class="pass-input" placeholder="Password" name="password">
            </div>
        </div>
        <button class="signin-button"><fmt:message key="login_button"/></button>
        <div class="link">
            <a href="#">Forgot password</a> or
            <a href="${pageContext.request.contextPath}/view/registration">Sign up</a>
        </div>
        </form>
        <div class="language-switch">
            <a href="${pageContext.request.contextPath}/view/language/login?language=EN">
            <button>English</button>
            </a>
            <a href="${pageContext.request.contextPath}/view/language/login?language=UA">
                <button>Ukrainian</button>
            </a>
        </div>
    </div>
</body>
</html>


<%--
<form action="${pageContext.request.contextPath}/view/login" method="post">
    Please enter your username
    <p>
        <label>
            <input class="profileEditorFields loginField" type="text" required
                   placeholder=Login
                   name="login">
        </label>
    </p>

    Please enter your password
    <p>
        <label>
            <input class="profileEditorFields loginField" type="password" required
                   placeholder=Password
                   name="password">
        </label>
    </p>

    <a href=${pageContext.request.contextPath}/view/login>
        <button>Login</button>
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
</form>--%>