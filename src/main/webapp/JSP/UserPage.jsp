<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Welcome, ${user.username}!</h2>
    <h3>Your Courses</h3>

    <c:if test="${empty coursesData}">
        <p>You are not enrolled in any courses.</p>
    </c:if>

    <c:forEach var="course" items="${coursesData}">
        <div class="course">
            <h4>${course.name}</h4>
            <p><b>Description:</b> ${course.description}</p>
            <p><b>YHP:</b> ${course.yhp}</p>
        </div>
    </c:forEach>
</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
