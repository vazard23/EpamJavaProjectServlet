<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Our Offers</title>
    <style>
        th, td {
            border-style: groove;
        }
    </style>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <script src="${pageContext.request.contextPath}/js/download.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>
</head>
<body>

<div class="col-md-12 text-right mb-3">
    <button class="btn btn-primary" id="download">DOWNLOAD LOL</button>
</div>

<div class="my-5 page" size="A4" id="invoice">
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
                <a href=${pageContext.request.contextPath}/view/checkOfferAccept?id=${a.id}&button=accept>Accept</a>
                <input type="hidden" name="offer_id" value="${a.id}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<c:if test="${requestScope.hasPlan}">
    <div class="w3-container">
        <fmt:message key="hasPlan"/>
    </div>
</c:if>

<c:if test="${requestScope.notEnoughMoney}">
    <div class="w3-container">
        <fmt:message key="notEnoughMoney"/>
    </div>
</c:if>

</body>
</html>
