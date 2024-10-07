<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Student, model.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="students" class="model.Database" scope="request" />
<jsp:useBean id="courses" class="model.Database" scope="request" />

<html>
<head>
    <title>Enroll Student in Course</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<jsp:include page="Fragments/navbar.jsp" />

<h2>Enroll Student in Course</h2>

<form action="EnrollStudentServlet" method="post">
    <label for="student">Select Student:</label>
    <select name="studentId" id="student">
        <c:forEach var="student" items="${students.allStudents}">
            <option value="${student.id}">${student.fName} ${student.lName}</option>
        </c:forEach>
    </select>

    <label for="course">Select Course:</label>
    <select name="courseId" id="course">
        <c:forEach var="course" items="${courses.allCourses}">
            <option value="${course.id}">${course.name}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Enroll">
</form>

<jsp:include page="Fragments/footer.jsp" />
</body>
</html>
