<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Teacher, model.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="teachers" class="model.Database" scope="request" />
<jsp:useBean id="courses" class="model.Database" scope="request" />

<html>
<head>
    <title>Assign Teacher to Course</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="Fragments/navbar.jsp" />

<h2>Assign Teacher to Course</h2>

<form action="AssignCourseServlet" method="post">
    <label for="teacher">Select Teacher:</label>
    <select name="teacherId" id="teacher">
        <c:forEach var="teacher" items="${teachers.allTeachers}">
            <option value="${teacher.id}">${teacher.fName} ${teacher.lName}</option>
        </c:forEach>
    </select>

    <label for="course">Select Course:</label>
    <select name="courseId" id="course">
        <c:forEach var="course" items="${courses.allCourses}">
            <option value="${course.id}">${course.name}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Assign">
</form>

<jsp:include page="Fragments/footer.jsp" />
</body>
</html>

