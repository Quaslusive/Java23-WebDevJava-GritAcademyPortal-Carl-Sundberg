<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'TEACHER'}">
        <%@include file="/JSP/Fragments/navBarTeacherInclude.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.userType == 'STUDENT'}">
        <%@include file="/JSP/Fragments/navBarStudentInclude.jsp"%>
    </c:when>
    <c:when test="${sessionScope.user != null && sessionScope.user.stateType == 'ANONYMOUS'}">
        <%@include file="/JSP/Fragments/navBarGuestInclude.jsp"%>
    </c:when>
</c:choose>
<div class="container">
    <h2 style="color: red">Error</h2>
    <p>${message}</p>
    <img src="${pageContext.request.contextPath}/assets/DKke_error.gif" alt="confused dino">
</div>
<%@include file="/JSP/Fragments/footerInclude.jsp"%>

</body>
</html>
