<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/JSP/Fragments/NavBarMain.jsp"%>

<div class="top-titel fadeInAnimation" >
    <h1>Welcome to Grit Academy Portal version!</h1>
</div>
<main>
    <div>
        <h2>Please log in</h2>
        <form action="http://localhost:8080/login" method="POST">

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="John" required><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Cardholder" required><br><br>

            <label for="type">Login as:</label>
            <select id="type" name="type">
                <option value="teacher">Teacher</option>
                <option value="student" selected>Student</option>
                <option value="admin">Administrator</option>
            </select>
            <div>
                <button type="submit">Login</button>
                <a href="login?type=guest"><button type="button">Login as Guest</button></a>
            </div>
        </form>
        <p style="color:red">${error}</p> <!-- Display error message -->
    </div>
</main>
<%@include file="/JSP/Fragments/footer.jsp"%>
</body>
</html>
