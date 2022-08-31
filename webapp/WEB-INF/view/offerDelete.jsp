<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Deleting offer</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/offerDelete" method="post">

<h1><fmt:message key="deletingOffer"/></h1>

<a href=${pageContext.request.contextPath}/view/offerDelete?offer_id=${offer_id}>
    <button name="remove" value="remove"><fmt:message key="adminRemove_button"/></button>
</a>
</form>
</body>
</html>
