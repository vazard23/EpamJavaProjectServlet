<%--
  Created by IntelliJ IDEA.
  User: DIO
  Date: 24.08.2022
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
