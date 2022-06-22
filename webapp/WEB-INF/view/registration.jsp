<%--
  Created by IntelliJ IDEA.
  User: DIO
  Date: 20.06.2022
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/registration">
    Enter your name:
    <p>
        <label>
            <input class="profileEditorFields loginField" type="text" required
                   placeholder=Name
                   name="name">
        </label>
    </p>
    Enter your login:
    <p>
        <label>
            <input class="profileEditorFields loginField" type="text" required
                   placeholder=Login
                   name="login">
        </label>
    </p>
    Enter your password:
    <p>
        <label>
            <input class="profileEditorFields loginField" type="password" required
                   placeholder=Password
                   name="password">
        </label>
    </p>
    Enter your e-mail:
    <p>
        <label>
            <input class="profileEditorFields loginField" type="text" required
                   placeholder=E-mail
                   name="email">
        </label>
    </p>

    <a href=${pageContext.request.contextPath}/view/registration>
            <button>Register</button>
    </a>
</form>
</body>
</html>
