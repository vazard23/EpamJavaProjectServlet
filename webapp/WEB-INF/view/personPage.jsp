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
    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
</head>
<body>
<h1> <fmt:message key="hello"/>, <%= session.getAttribute("name") %> </h1>

<h1><fmt:message key="balance"/>: <%= session.getAttribute("funds") %> ₴</h1>


<form action="${pageContext.request.contextPath}/view/logout">
    <a>
        <button><fmt:message key="logout_button"/></button>
    </a>
</form>

<form action="${pageContext.request.contextPath}/view/deposit">
    <a>
        <button><fmt:message key="deposit_button"/></button>
    </a>
</form>

<form action="${pageContext.request.contextPath}/view/offerListPerson">
    <a>
        <button><fmt:message key="ourOffers_button"/></button>
    </a>
</form>
<c:if test="${requestScope.offerList_notEmpty}">
    <div class="w3-container">
        <table class="tableService sortable" id = "sortTable" data-filter-control="false" data-show-search-clear-button="false">>
            <thead>
            <tr>
                <th data-sortable="true"><fmt:message key="offerName_table"/></th>
                <th data-sortable="false"><fmt:message key="offerDesc_table"/></th>
                <th data-sortable="true"><fmt:message key="offerPrice_table"/></th>
                <th data-sortable="false"><fmt:message key="offerCategory_table"/></th>
                <th data-sortable="false"><fmt:message key="offerStatus_table"/></th>
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
        <script>$('#sortTable').DataTable({searching: false, info: false});</script>
    </div>
</c:if>

<c:if test="${requestScope.offerList_notEmpty}">
</c:if>

</body>
</html>
