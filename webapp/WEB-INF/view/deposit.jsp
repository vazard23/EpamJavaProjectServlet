<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title>Deposit Money</title>
</head>
<body>

<h1>Enter the amount of money you want to deposit</h1>
<form action="${pageContext.request.contextPath}/view/deposit" method="post">



<p>
    <label>
        <input class="profileEditorFields loginField" type="number" min="50" max="5000" required
               name="depositField">
    </label>
</p>

<a href=${pageContext.request.contextPath}/view/deposit>
        <button name="btn" value="Deposit">Deposit</button>
</a>
<a href=${pageContext.request.contextPath}/view/personPage>
    <button>To person page</button>
</a>
</form>
</body>
</html>
