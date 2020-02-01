<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope == null || sessionScope.logged != true}">
    <% response.sendRedirect("Access"); %>
</c:if>

<h1 class="h1">Your mum</h1>
<a href="Product">See Products</a>
</body>
</html>
