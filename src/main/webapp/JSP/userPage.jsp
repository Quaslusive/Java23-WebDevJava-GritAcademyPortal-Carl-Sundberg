<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'TEACHER'}">
        <%@include file="Fragments/navBarTeacherInclude.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'STUDENT'}">
        <%@include file="Fragments/navBarStudentInclude.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.stateType == 'ANONYMOUS'}">
        <%@include file="Fragments/navBarGuestInclude.jsp"%>
    </c:when>
</c:choose>

<div class="container">
    <c:choose>
        <c:when test="${user.stateType == 'ANONYMOUS'}">
            <p>Welcome, Guest!</p>
        </c:when>
        <c:when test="${user.stateType == 'CONFIRMED'}">
            <p>Welcome, ${user.username}!</p>
        </c:when>
    </c:choose>
    <h3>Your Courses</h3>
<% if (request.getAttribute("courseData") !=null) {
    out.println(request.getAttribute("courseData"));
} %>
    <c:if test="${empty coursesData}">
        <p>You are not enrolled in any courses.</p>
    </c:if>

    <c:forEach var="course" items="${coursesData}">
        <table>
            <tr>
                <th>Course Name</th>
                <th>Description</th>
            </tr>
            <tr>
                <td>${course.name}</td>
                <td>${course.description}</td>
            </tr>
        </table>
    </c:forEach>


</div>

<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
