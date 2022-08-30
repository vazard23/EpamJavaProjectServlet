<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Offer change</title>
    <style>
        th, td {
            border-style: groove;
        }
    </style>
</head>
<body>
<table class="tableService sortable">
    <thead>
    <tr>
        <th><fmt:message key="offerName_table"/></th>
        <th><fmt:message key="offerDesc_table"/></th>
        <th><fmt:message key="offerPrice_table"/></th>
        <th><fmt:message key="offerCategory_table"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${offers}" var="a">
        <tr>
            <td>${a.name}</td>
            <td>${a.description}</td>
            <td>${a.price}₴</td>
            <td>${a.category_id}</td>
            <td>
                <a href=${pageContext.request.contextPath}/view/adminChangeOffer?offer_id=${a.id}&button=change><fmt:message key="changeButton_table"/> </a>
                <input type="hidden" name="offer_id" value="${a.id}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
