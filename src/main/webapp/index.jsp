<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="JSP/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Welcome to the Grit Academy Portal. This portal is a central hub for students, teachers, and administrators.">
    <title>Grit Academy Portal - Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Welcome to Grit Academy Portal</h1>
    <p>This portal is a central hub for students, teachers, and administrators to manage resources at Grit Academy.</p>

    <div style="text-align: center;">
        <form action="${pageContext.request.contextPath}JSP/login.jsp" method="get">
            <input type="submit" value="Login">
        </form>
        <p>lol</p>
    </div>
</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
