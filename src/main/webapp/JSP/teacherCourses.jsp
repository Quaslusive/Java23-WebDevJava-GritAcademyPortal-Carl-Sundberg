<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/JSP/Fragments/navbarDefaultInclude.jsp" %>

<div class="container">
    <h2>Courses for Teacher: ${teacher.fname} ${teacher.lname}</h2>
    <c:if test="${empty teacherCourses}">
        <p>This teacher is not assigned to any courses.</p>
    </c:if>
    <c:forEach var="course" items="${teacherCourses}">
        <div class="course-item">
            <p><strong>Course:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
        </div>
    </c:forEach>
</div>

<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
