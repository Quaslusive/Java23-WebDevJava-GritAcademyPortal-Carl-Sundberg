<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/JSP/Fragments/navbarDefaultInclude.jsp" %>
<div class="container">
    <h2>Login</h2>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" class="form-control">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control">
        </div>

        <!-- Checkbox för gästinloggning -->
        <div class="form-group">
            <input type="checkbox" id="guestLogin" name="guestLogin" value="true">
            <label for="guestLogin">Log in as Guest</label>
        </div>

        <div class="form-group">
            <input type="submit" value="Login" class="btn btn-primary">
        </div>

        <c:if test="${param.error != null}">
            <p class="error">${param.error}</p>
        </c:if>
    </form>

    <h4>Teacher: Bob Berger</h4>
    <p>Username: test </p>
    <p>Password: test</p>

    <h4>Student: Marie Curie</h4>
    <p>Username: pass </p>
    <p>Password: pass</p>
</div>

<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
