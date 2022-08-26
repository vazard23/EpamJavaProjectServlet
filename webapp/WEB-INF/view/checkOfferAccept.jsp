<%--
  Created by IntelliJ IDEA.
  User: DIO
  Date: 26.08.2022
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accept Offer</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/view/checkOfferAccept" method="post">
  <div class="float">
    <label for="checkbox" name="checkbox" id="checkbox-label">
      <input type="checkbox" name="checkbox" id="checkbox">
      <p>I approve to top up this user account</p>
    </label>
  </div>

  <a href=${pageContext.request.contextPath}/view/checkOfferAccept>
  <button name="btn" value="accept">Accept</button>
</a>
  <input type="hidden" name="person_id" value="${param.id}">

</form>
<script src="${pageContext.request.contextPath}/WEB-INF/js/checkbox.js"></script>
</body>
</html>
