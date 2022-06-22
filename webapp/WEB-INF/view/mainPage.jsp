<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>This is main page</h1>

<a href=${pageContext.request.contextPath}/view/login>
    <button>Sign In</button>
</a>
<a href=${pageContext.request.contextPath}/view/registration>
    <button>Sign Up</button>
</a>
</body>
</html>
