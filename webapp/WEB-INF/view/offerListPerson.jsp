<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Our Offers</title>
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
        <th>Name of the offer</th>
        <th>Description</th>
        <th>Price in UAH</th>
        <th>Category of offer
        <th>
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
                <input type="hidden" name="person_id" value="${a.id}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
