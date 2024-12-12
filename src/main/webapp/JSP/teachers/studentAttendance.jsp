<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Attendance</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Student Attendance</h2>


    <c:if test="${empty studentData}">
        <p>No students found.</p>
    </c:if>


    <table class="table">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentData}">
            <tr>
                <td>${student.fname}</td>
                <td>${student.lname}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
