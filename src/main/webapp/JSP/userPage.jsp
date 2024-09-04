<%--
  Created by IntelliJ IDEA.
  User: Gamingcalle_Mark-3
  Date: 2024-08-21
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.UserBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="user" scope="session" class="model.UserBean" />

<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<h1>Welcome, <c:out value="${user.fName}"/> <c:out value="${user.lName}"/>!</h1>
<p>You are logged in as a <c:out value="${user.userType}"/> with <c:out value="${user.privilageType}"/> privileges.</p>

<!-- For Students -->
<c:if test="${user.userType == 'student'}">
    <h2>Your Courses</h2>
    <ul>
        <c:forEach var="course" items="${user.courses}">
            <li><c:out value="${course.name}"/> - <c:out value="${course.description}"/></li>
        </c:forEach>
    </ul>
</c:if>

<!-- For Teachers -->
<c:if test="${user.userType == 'teacher'}">
    <h2>All Courses</h2>
    <ul>
        <c:forEach var="course" items="${user.courses}">
            <li><c:out value="${course.name}"/> - <c:out value="${course.description}"/></li>
        </c:forEach>
    </ul>
</c:if>

<!-- Admin Options -->
<c:if test="${user.privilageType == 'admin'}">
    <h2>Admin Options</h2>
    <ul>
        <li><a href="register.jsp">Register new Student or Teacher</a></li>
        <li><a href="assignCourse.jsp">Assign Teachers to Courses</a></li>
        <li><a href="enrollStudent.jsp">Enroll Students to Courses</a></li>
    </ul>
</c:if>

<jsp:include page="Fragments/footer.jsp" />
</body>
</html>
