
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="/JSP/Fragments/navBarTeacherInclude.jsp"%>
<h2>Welcome ${user.fname} ${user.lname}!</h2>

<%@include file="/JSP/teachers/teacherUserPage.jsp"%>
</body>
</html>
