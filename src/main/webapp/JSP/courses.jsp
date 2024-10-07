<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'TEACHER'}">
        <%@include file="Fragments/navBarTeacher.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'STUDENT'}">
        <%@include file="Fragments/navBarStudent.jsp"%>
    </c:when>
</c:choose>



<div class="container">
    <h2>Available Courses1</h2>


    <c:if test="${empty courseData}">
        <p>No courses found.</p>
    </c:if>

    <c:forEach var="course" items="${courseData}">
        <table class="table">
            <thead>
            <tr>
                <th>Course Name</th>
                <th>YHP</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${course.name}</td>
                <td>${course.yhp}</td>
                <td>${course.description}</td>
            </tr>
            </tbody>
        </table>
    </c:forEach>
</div>
<div class="container">
    <h2>Available Courses2</h2>


    <%@ include file="Fragments/courseListFragment.jsp" %>

</div>

<div>

    <li> <a href="<%request.getContextPath(); %>"> </a></li>
</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
