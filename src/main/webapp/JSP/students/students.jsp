
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="/JSP/Fragments/navBarStudentInclude.jsp"%>
<h2 style="text-align: center">Welcome ${user.fname} ${user.lname}!</h2>


<%@include file="/JSP/students/studentUserPage.jsp"%>

<%--
<%@ include file="/JSP/Fragments/courseListFragment.jsp" %>
--%>

</body>
</html>
