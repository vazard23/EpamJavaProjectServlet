<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Add Offer</title>
    <style>
        th, td {
            border-style: groove;
        }
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/view/addOffer" method="post">
Offer add form

<table class="tableService sortable">
    <thead>
    <tr>
        <th>Name of the offer</th>
        <th>Description</th>
        <th>Price in UAH</th>
        <th>Category of offer
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <label>
                <input type="text" required
                       placeholder=Name
                       name="offer_name">
            </label>
        </td>

        <td>
            <label>
                <input type="text" required
                       placeholder=Description
                       name="offer_desc">
            </label>
        </td>

        <td>
            <label>
                <input type="number" min="50" max="5000" required
                       placeholder=Price
                       name="offer_price">
            </label>
        </td>

        <td>
            <label>
                <input type="text" required
                       placeholder=Category
                       name="offer_category">
            </label>
        </td>
    </tr>
    </tbody>
</table>

<a href=${pageContext.request.contextPath}/view/addOffer>
    <button name = "btn" value="Add">Add new offer</button>
</a>

    <c:if test="${requestScope.name_exists}">
        <div class="w3-container">
            <fmt:message key="name_exists"/>
        </div>
    </c:if>

</form>
</body>
</html>
