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
<jsp:include page="Fragments/navbar.jsp" />
<c:choose>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'TEACHER'}">
        <%@include file="Fragments/navBarTeacher.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'STUDENT'}">
        <%@include file="Fragments/navBarStudent.jsp"%>
    </c:when>
</c:choose>


<div class="container">
    <h2>Available Courses</h2>
    <%@ include file="Fragments/courseListFragment.jsp" %>
</div>


<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
