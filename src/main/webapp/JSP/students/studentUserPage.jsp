<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@include file="/JSP/Fragments/navBarStudentInclude.jsp"%>
<!-- Main Content -->
<div class="container">
    <h1 style="text-align:center; color:#333;">Welcome, ${user.username}</h1>

<%--
    <!-- Enrolled Courses -->
    <div class="container">
        <h2>Your Enrolled Courses</h2>
        <c:if test="${empty detailedCourses}">
            <p>You are not enrolled in any courses.</p>
        </c:if>
        <c:if test="${not empty detailedCourses}">
            <c:forEach var="course" items="${detailedCourses}">
                <div class="course-item">
                    <h3>${course.courseName}</h3>
                    <p><strong>Description:</strong> ${course.courseDescription}</p>
                    <p><strong>Teacher:</strong> ${course.teacherName}</p>
                    <h4>Classmates:</h4>
                    <ul>
                        <c:forEach var="student" items="${course.students}">
                            <li>${student.fname} ${student.lname}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
    </div>
--%>

    <!-- Select Course and Fetch Classmates -->
    <div class="select-course">
        <h2>Select Course to View Classmates</h2>
        <form action="${pageContext.request.contextPath}/userPage" method="POST">
            <label for="selectedCourseId">Select a Course:</label>
            <select id="selectedCourseId" name="selectedCourseId">
                <c:forEach var="course" items="${courseData}">
                    <option value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
            <button type="submit" name="showStudents">Show Classmates</button>
        </form>
    </div>

<%--
    <!-- Display Classmates -->
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
  --%>
</div>


<%@include file="/JSP/Fragments/footerInclude.jsp"%>
</body>
</html>
