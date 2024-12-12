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

<div class="container">
    <h2>Welcome, ${user.username}!</h2>
    <h3>Courses You're Teaching</h3>

    <c:if test="${empty teacherCourses}">
        <p>No courses assigned to you.</p>
    </c:if>

    <c:forEach var="course" items="${teacherCourses}">
        <div class="course-item">
            <p><strong>Course:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
            <p><strong>YHP:</strong> ${course.yhp}</p>
        </div>
    </c:forEach>
</div>

<div>
    <h3>Courses</h3>
    <ul>
        <c:forEach var="course" items="${courseData}">
            <li>${course.name} - ${course.description}</li>
        </c:forEach>
    </ul>

    <h3>All Students</h3>
    <ul>
        <c:forEach var="student" items="${allStudents}">
            <li>${student.fName} ${student.lName}</li>
        </c:forEach>
    </ul>

</div>


<div class="container">
    <h2>Courses for Teacher: ${user.username}</h2>
    <c:if test="${empty teacherCourses}">
        <p>You are not assigned to any courses.</p>
    </c:if>

    <c:forEach var="course" items="${teacherCourses}">
        <div class="course-item">
            <p><strong>Course:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
            <p><strong>Students Enrolled:</strong> ${course.studentName}</p>
        </div>
    </c:forEach>
</div>

<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
