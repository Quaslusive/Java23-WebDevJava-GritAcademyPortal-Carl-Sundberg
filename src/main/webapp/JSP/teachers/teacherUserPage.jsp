<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="/JSP/Fragments/navBarTeacherInclude.jsp"%>
<div class="container">
    <h1>Welcome, ${user.username}</h1>

    <!-- Form for fetching students and courses -->
    <form action="${pageContext.request.contextPath}/userPage" method="POST">
        <button type="submit" name="action" value="allStudents">View All Students</button>
        <button type="submit" name="action" value="allCourses">View All Courses</button>
    </form>

    <!-- Display All Students -->
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

    <!-- Display All Courses -->
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
</div>
</body>
</html>









<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Teacher Page</title>
</head>
<body>
<!-- Main Content -->
<div class="container">
    <h1 style="text-align:center; color:#333;">Welcome, ${user.username}</h1>

<div class="container">
    <h2>Available Courses</h2>
    <%@ include file="/JSP/Fragments/courseListFragment.jsp" %>
</div>

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
</div>

--%>



<%--

<h2>Student List</h2>
<table border="1">
    <thead>
    <tr>
        <th>Student ID</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${allStudents}">
        <tr>
            <td>${student.id}</td>
            <td>${student.fName}</td>
            <td>${student.lName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Student-Course Relationships</h2>
<table border="1">
    <thead>
    <tr>
        <th>Student</th>
        <th>Course</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="record" items="${studentCourses}">
        <tr>
            <td>${record}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>--%>
</body>
</html>





<%--
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
--%>
