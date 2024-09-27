<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/JSP/Fragments/courseListFragment.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Available Courses</h2>

    <!-- Include the fragment to display the courses -->
    <jsp:include page="/JSP/Fragments/courseListFragment.jsp"/>

</div>

<%@ include file="/JSP/Fragments/footer.jsp" %>
</body>
</html>
