<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/JSP/Fragments/navbar.jsp" %>

<div class="container">
    <h2>Welcome, ${user.username}!</h2>
    <h3>Courses You're Teaching</h3>

    <c:if test="${empty courseData}">
        <p>No courses assigned to you.</p>
    </c:if>

    <c:forEach var="course" items="${courseData}">
        <div class="course-item">
            <p><strong>Course:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
            <p><strong>YHP:</strong> ${course.yhp}</p>
        </div>
    </c:forEach>
</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
