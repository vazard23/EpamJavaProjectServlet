<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>


<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>This is main page</h1>

<a href=${pageContext.request.contextPath}/view/login>
    <button>Sign In</button>
</a>
<a href=${pageContext.request.contextPath}/view/registration>
    <button>Sign Up</button>
</a>
</body>
</html>
