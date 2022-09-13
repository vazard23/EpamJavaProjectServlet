<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>You have been blocked</title>
</head>
<body>


<c:if test="${requestScope.noMoney}">
<form action="${pageContext.request.contextPath}/view/blockedPage" method="post">
    <fmt:message key="blockedMessage"/>
    <h1><%= session.getAttribute("moneyToPay")%>
    </h1>
    <p>
        <label>
            <input class="profileEditorFields loginField" type="number" min="50" max="5000" required
                   name="depositField">
        </label>
    </p>

    <a href=${pageContext.request.contextPath}/view/blockedPage>
        <button name="accept" value="accept"><fmt:message key="acceptBlocked_button"/></button>
    </a>

    <form action="${pageContext.request.contextPath}/view/logout">
        <a>
            <button><fmt:message key="logout_button"/></button>
        </a>
    </form>


    <c:if test="${requestScope.notEnough}">
        <fmt:message key="notEnoughForUnblock"/>
    </c:if>

</form>
</c:if>

<c:if test="${requestScope.blockedByAdmin}">
    <fmt:message key="adminBlocked"/>

    <form action="${pageContext.request.contextPath}/view/logout">
        <a>
            <button><fmt:message key="logout_button"/></button>
        </a>
    </form>


</c:if>

</body>
</html>
