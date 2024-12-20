<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Teacher Admin Dashboard</h1>
    <!-- Form for fetching all data -->
    <form action="${pageContext.request.contextPath}/userPage" method="POST">
        <button type="submit" name="action" value="viewStudents">View All Students</button>
        <button type="submit" name="action" value="viewCourses">View All Courses</button>
        <button type="submit" name="action" value="viewStudentsEnrollment">View All Teachers</button>
    </form>

    <!-- Display Students -->
    <c:if test="${not empty allStudents}">
        <h2>All Students</h2>
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
            <c:forEach var="student" items="${allStudents}">
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

    <!-- Display Courses -->
    <c:if test="${not empty allCourses}">
        <h2>All Courses</h2>
        <table>
            <thead>
            <tr>
                <th>Course Name</th>
                <th>Description</th>
                <th>YHP</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${allCourses}">
                <tr>
                    <td>${course.name}</td>
                    <td>${course.description}</td>
                    <td>${course.yhp}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Display student Courses -->
    <c:if test="${not empty studentCourses}">
        <h2>All Students</h2>
        <table>
            <thead>
            <tr>
                <th>Student Name</th>
                <th>Course Name</th>
                <th>Description</th>
                <th>Teacher</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${studentCourses}">
                <tr>
                    <td>${student.studentName}</td>
                    <td>${student.courseName}</td>
                    <td>${student.courseDescription}</td>
                    <td>${student.teacherName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>
<%@include file="/JSP/Fragments/footerInclude.jsp"%>

</body>
</html>