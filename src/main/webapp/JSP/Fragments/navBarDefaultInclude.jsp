<nav class="navbar">
        <a href="${pageContext.request.contextPath}/index.html">Home</a>

        <c:if test="${not empty user}">
                <a href="${pageContext.request.contextPath}/userPage">User Page</a>
                <a href="${pageContext.request.contextPath}/courses">Courses</a>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>
</nav>




<%--

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    // Assuming userRole is a variable indicating the user's role,
    // which you need to set after authenticating the user
    String userRole = (String) session.getAttribute("userRole");
%>

<html>
<head>
    <title>Dashboard: Parameter</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<!-- Context based navbar place here -->
<%@include file="Fragments"%>


<nav>
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/about">About</a></li>

        <!-- Show admin-specific link if user is admin -->
        <jsp:useBean id="userType" scope="session"/>
        <c:if test="${userType eq 'admin'}">
            <li><a href="/admin">Admin Panel</a></li>
        </c:if>

        <!-- Show user-specific link if user is logged in -->
        <c:if test="${not empty userRole}">
            <li><a href="/profile">Profile</a></li>
        </c:if>

        <!-- Show login/register links if user is not logged in -->
        <c:if test="${empty userRole}">
            <li><a href="/login">Login</a></li>
            <li><a href="/register">Register</a></li>
        </c:if>
    </ul>
</nav>


<div class="top-titel fadeInAnimation" >
    <h1>Welcome to Grit Academy Portal version!</h1>
</div>

<main>
    <div style="align-content: center">
    <h2>Welcome to the dashboard "insert username"!</h2>
        <h3>What would you like to do today?</h3>
    </div>

</main>

</body>
</html>
--%>

