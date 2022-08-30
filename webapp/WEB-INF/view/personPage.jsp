<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
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
<h1> <fmt:message key="hello"/>, <%= session.getAttribute("name") %> </h1>

<h1><fmt:message key="balance"/>: <%= session.getAttribute("funds") %> ₴</h1>


<form action="${pageContext.request.contextPath}/view/logout">
    <a>
        <button>Logout</button>
    </a>
</form>

<form action="${pageContext.request.contextPath}/view/deposit">
    <a>
        <button>Deposit</button>
    </a>
</form>

<form action="${pageContext.request.contextPath}/view/offerListPerson">
    <a>
        <button>Our Offers</button>
    </a>
</form>

<table class="tableService sortable">
    <thead>
    <tr>
        <th>Name of the offer</th>
        <th>Description</th>
        <th>Price in UAH</th>
        <th>Category of offer
        <th>Status of Offer</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${offers}" var="a">
        <tr>
            <td>${a.name}</td>
            <td>${a.description}</td>
            <td>${a.price}₴</td>
            <td>
                <c:if test="${a.category_id == 1}">
                    <c:out value="TV" />
                </c:if>
                <c:if test="${a.category_id == 2}">
                    <c:out value="Web" />
                </c:if>
                <c:if test="${a.category_id == 3}">
                    <c:out value="Telephone" />
                </c:if>
            <td>
                Placeholder for status
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
