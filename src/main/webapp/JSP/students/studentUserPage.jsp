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
<%@include file="/JSP/Fragments/navBarStudentInclude.jsp"%>
<!-- Main Content -->
<div class="container">
    <h1>Student Dashboard</h1>
    <!-- Form for fetching students and courses -->
    <form action="${pageContext.request.contextPath}/userPage" method="POST">
        <button type="submit" name="action" value="viewStudentsEnrollment">View All Students</button>
    </form>


    <!-- Display Students -->
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
