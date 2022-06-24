<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        th, td {
            border-style: groove;
        }
    </style>
    <title>Person Page</title>
</head>
<body>
Person
<form action="${pageContext.request.contextPath}/view/logout">
<a>
    <button>Logout</button>
</a>
    <table class="tableService sortable">
        <thead>
        <tr>
            <th>Name of the offer</th>
            <th>Description</th>
            <th>Price in UAH</th>
            <th>Category of offer<th>
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
                <a href="${pageContext.request.contextPath}/view/personPage?id=${a.id}&button=offer"
                   onclick="return confirm('<fmt:message
                           key="accept_offer"/>')"><fmt:message key="offer"/></a>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
