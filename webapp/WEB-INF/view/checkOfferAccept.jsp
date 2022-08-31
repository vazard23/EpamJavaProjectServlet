<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>


<html>
<head>
    <title>Accept Offer</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/checkOfferAccept" method="post">
    <h1>Do you really want to accept this offer?</h1>
    <h1>This action means that money will be charged from your account</h1>
  <a href=${pageContext.request.contextPath}/view/checkOfferAccept>
  <button name="btn" value="accept">Accept</button>
  </a>
  <input type="hidden" name="offer_id" value="${param.id}">

    <c:if test="${requestScope.hasPlan}">
        <div class="w3-container">
            <fmt:message key="hasPlan"/>
        </div>
    </c:if>

</form>
<script src="${pageContext.request.contextPath}/WEB-INF/js/checkbox.js"></script>

<a href=${pageContext.request.contextPath}/view/adminPage>
    <button name="home"><fmt:message key="adminPage_button"/></button>
</a>
</body>
</html>
