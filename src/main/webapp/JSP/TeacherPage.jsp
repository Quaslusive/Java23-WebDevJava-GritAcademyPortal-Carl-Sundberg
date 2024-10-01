<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher</title>
</head>
<body>
<main>
<p>Luz Teacher</p>
    <div class="container">
        <h2>Available Courses</h2>

        <!-- Include the fragment to display the courses -->
        <jsp:include page="/JSP/Fragments/courseListFragment.jsp"/>

    </div>
</main>
</body>
</html>
