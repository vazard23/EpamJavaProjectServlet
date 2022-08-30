<%@ page import="model.entity.Offer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Offer change form</title>
    <style>
        th, td {
            border-style: groove;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/offerChangeForm" ;>
    <table class="tableService sortable">
        <thead>
        <tr>
            <th><fmt:message key="offerName_table"/></th>
            <th><fmt:message key="offerDesc_table"/></th>
            <th><fmt:message key="offerPrice_table"/></th>
            <th><fmt:message key="offerCategory_table"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${offer.name}</td>
            <td>${offer.description}</td>
            <td>${offer.price}₴</td>
            <td>
                <c:if test="${offer.category_id == 1}">
                    <fmt:message key="tv_category"/>
                </c:if>

                <c:if test="${offer.category_id == 2}">
                    <fmt:message key="web_category"/>
                </c:if>

                <c:if test="${offer.category_id == 3}">
                    <fmt:message key="telephone_category"/>
                </c:if>
            </td>
        </tr>
        </tbody>
    </table>

    <h1><fmt:message key="redacting_table"/></h1>

    <table class="tableService sortable">
        <thead>
        <tr>
            <th><fmt:message key="offerName_table"/></th>
            <th><fmt:message key="offerDesc_table"/></th>
            <th><fmt:message key="offerPrice_table"/></th>
            <th><fmt:message key="offerCategory_table"/></th>
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
                <label for="category"></label>
                <select id="category" name="categories">
                    <option value="1">Web</option>
                    <option value="2">TV</option>
                    <option value="3">Phone</option>
                </select>
            </td>
        </tr>
        </tbody>
    </table>

    <a href=${pageContext.request.contextPath}/view/offerChangeForm>
        <button name="change"><fmt:message key="adminChange_button"/></button>
    </a>

    <a href=${pageContext.request.contextPath}/view/offerChangeForm>
        <button name="remove"><fmt:message key="adminRemove_button"/></button>
    </a>

</form>
</body>
</html>
