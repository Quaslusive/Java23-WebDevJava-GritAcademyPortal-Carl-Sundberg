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
    <h2>Available Courses</h2>
    <%@ include file="Fragments/courseListFragment.jsp" %>
</div>


<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
