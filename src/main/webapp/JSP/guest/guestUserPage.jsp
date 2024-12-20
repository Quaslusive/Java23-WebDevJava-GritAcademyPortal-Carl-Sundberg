<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guest Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="/JSP/Fragments/navBarGuestInclude.jsp"%>
<div class="container">
    <h1>Welcome, ${user.username}</h1>
    <p>Unfortunately there is not much to do here... 😔</p>
    <button onclick="document.location.href='https://celeryman.alexmeub.com/'">Why don't you go here instead</button>
</div>

<%@include file="/JSP/Fragments/footerInclude.jsp"%>
</body>
</html>