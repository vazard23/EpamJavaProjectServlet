<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Welcome!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/welcomeStyle.css">
</head>
<body>
<div class="hero">
    <img src="${pageContext.request.contextPath}/images/welcome_bg.png" class="background">
    <div class="content">
        <h1>Providers life</h1>
        <a href="${pageContext.request.contextPath}/view/mainPage">Explore</a>
    </div>
</div>
</body>
</html>

