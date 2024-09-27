<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Welcome, <%= session.getAttribute("user").getUsername() %>!</h2>
    <h3>Your Courses</h3>

    <c:if test="${empty courses}">
        <p>You are not enrolled in any courses.</p>
    </c:if>
    <c:forEach var="course" items="${courses}">
        <p><b>Course:</b> ${course.name}</p>
        <p><b>Description:</b> ${course.description}</p>
    </c:forEach>
</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
