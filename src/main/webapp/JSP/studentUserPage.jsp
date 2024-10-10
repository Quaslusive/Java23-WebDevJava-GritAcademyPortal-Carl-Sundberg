<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/JSP/Fragments/navbarDefaultInclude.jsp" %>

<div class="container">
    <h2>Courses for Student: ${student.fname}</h2>
    <c:if test="${empty studentCourses}">
        <p>This student is not enrolled in any courses.</p>
    </c:if>
    <c:forEach var="course" items="${studentCourses}">
        <div class="course-item">
            <p><strong>Course:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
        </div>
    </c:forEach>
</div>
<div>
<c:if test="${empty studentData}">
    <p>No student found.</p>
</c:if>

<c:forEach var="student" items="${studentData}">
    <table class="table">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>town</th>
            <th>email</th>
            <th>phone</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${student.fname}</td>
            <td>${student.lName}</td>
            <td>${student.town}</td>
            <td>${student.email}</td>
            <td>${student.phone}</td>
        </tr>
        </tbody>
    </table>
    </div>
<div>
</c:forEach> <form action="${pageContext.request.contextPath}/studentCourses" method="post">
    <label for="selectedCourseId">Select a Course:</label>
    <select id="selectedCourseId" name="selectedCourseId">
        <c:forEach var="course" items="${courseData}">
            <option value="${course.id}">${course.name}</option>
        </c:forEach>
    </select>
    <button type="submit" name="showStudents">Show Classmates</button>
</form>
</div>

<div>
    <c:forEach var="course" items="${courseData}">
        <div class="course-item">
            <p><strong>Course Name:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
        </div>
    </c:forEach>

</div>

<div class="container">
    <h2>Courses for Student: ${user.username}</h2>
    <c:if test="${empty studentCourses}">
        <p>You are not enrolled in any courses.</p>
    </c:if>

    <c:forEach var="course" items="${studentCourses}">
        <div class="course-item">
            <p><strong>Course:</strong> ${course.name}</p>
            <p><strong>Description:</strong> ${course.description}</p>
            <p><strong>Teacher:</strong> ${course.teacherName}</p>
        </div>
    </c:forEach>
</div>

<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>