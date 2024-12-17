<%--
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
        <%@include file="JSP/Fragments/navBarTeacherInclude.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'STUDENT'}">
        <%@include file="JSP/Fragments/navBarStudentInclude.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.stateType == 'ANONYMOUS'}">
        <%@include file="JSP/Fragments/navBarGuestInclude.jsp"%>
    </c:when>
</c:choose>

<div class="container">
    <c:choose>
        <c:when test="${user.stateType == 'ANONYMOUS'}">
            <h2>Welcome, Guest!</h2>
            <p>You are logged in as a guest. To access more features, please log in with a registered account.</p>
        </c:when>
        <c:when test="${user.stateType == 'CONFIRMED'}">
            <h2>Welcome, ${user.username}!</h2>
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'STUDENT'}">
            <%@ include file="JSP/students/studentUserPage.jsp" %>
        </c:when>
        <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'TEACHER'}">
            <%@ include file="JSP/teachers/teacherUserPage.jsp" %>
        </c:when>
    </c:choose>

</div>

<%@ include file="/JSP/Fragments/footerInclude.jsp" %>
</body>
</html>
--%>
