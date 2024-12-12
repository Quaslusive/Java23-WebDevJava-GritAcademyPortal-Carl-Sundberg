<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students and Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>All Students and Their Courses</h2>

    <c:if test="${empty studentData}">
        <p>No students found.</p>
    </c:if>

    <c:forEach var="student" items="${studentData}">
        <div class="student-info">
            <h3>Student: ${student.fname} ${student.lname}</h3>
            <p>Email: ${student.email}</p>
            <p>Town: ${student.town}</p>

            <h4>Enrolled Courses:</h4>
            <ul>
                <c:forEach var="course" items="${student.courses}">
                    <li>${course.name} - ${course.description}</li>
                </c:forEach>
            </ul>
        </div>
        <hr>
    </c:forEach>
</div>
<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
