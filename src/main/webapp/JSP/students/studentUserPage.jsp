<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<!-- Main Content -->
<div class="container">
    <h1 style="text-align:center; color:#333;">Welcome, ${user.username}</h1>


    <div class="container">
        <h2>Available Courses fasf</h2>
        <%@ include file="/JSP/Fragments/studentCoursesFragment.jsp" %>
    </div>




    <!-- Student Course List -->
    <h2>Your Enrolled Courses</h2>
    <c:if test="${empty courseData}">
        <p>You are not enrolled in any courses.</p>
    </c:if>
    <c:if test="${not empty courseData}">
        <table>
            <thead>
            <tr>
                <th>Course Name</th>
                <th>Description</th>
                <th>Teacher</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courseData}">
                <tr>
                    <td>${course.name}</td>
                    <td>${course.description}</td>
                    <td>${course.teacherName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Student's Classmates Section -->
    <h2>Find Your Classmates</h2>
    <form action="${pageContext.request.contextPath}/userPage" method="post">
        <label for="selectedCourseId">Select a Course:</label>
        <select id="selectedCourseId" name="selectedCourseId">
            <c:forEach var="course" items="${courseData}">
                <option value="${course.id}">${course.name}</option>
            </c:forEach>
        </select>
        <button type="submit" name="showStudents">Show Classmates</button>
    </form>

    <!-- Classmate Data -->
    <c:if test="${not empty studentData}">
        <h2>Your Classmates</h2>
        <table>
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Town</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${studentData}">
                <tr>
                    <td>${student.fname}</td>
                    <td>${student.lname}</td>
                    <td>${student.town}</td>
                    <td>${student.email}</td>
                    <td>${student.phone}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<!-- Footer -->
<div class="footer">
    <span>&copy; 2024 Grit Academy Portal</span>
    <span>Contact: support@gritacademy.com</span>
</div>
</body>
</html>
