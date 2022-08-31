<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h1><fmt:message key="hello"/>, <%= session.getAttribute("name") %> </h1>

<a href=${pageContext.request.contextPath}/view/addOffer>
    <button><fmt:message key="addOffer_button"/> </button>
</a>

<a href=${pageContext.request.contextPath}/view/addPerson>
    <button><fmt:message key="registerUser_button"/> </button>
</a>

<a href=${pageContext.request.contextPath}/view/adminChangeOffer>
    <button><fmt:message key="adminChange_button"/></button>
</a>

<a href=${pageContext.request.contextPath}/view/adminUserPage>
    <button><fmt:message key="adminUserPage_button"/></button>
</a>

</body>
</html>
