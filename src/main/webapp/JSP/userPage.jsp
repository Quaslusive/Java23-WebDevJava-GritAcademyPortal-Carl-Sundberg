<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="/JSP/Fragments/navbar.jsp" %>


<div class="container">
    <h2>Welcome, ${user.username}!</h2>
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

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
