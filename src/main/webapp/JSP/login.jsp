<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="userType">User Type:</label>
        <select name="userType" id="userType">
            <option value="STUDENT">Student</option>
            <option value="TEACHER">Teacher</option>
        </select><br>

        <input type="submit" value="Login">
    </form>

    <p style="color:red;"><%= request.getParameter("error") %></p>
</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
