<nav class="navbar">
    <h3 style="text-align:center; color:#ffffff;"> Admin: ${user.fname} ${user.lname} |
        Username: ${user.username}</h3>
    <a href=${pageContext.request.contextPath}/JSP/admin/teacherAdminPage.jsp>| Home |</a>
    <a href=${pageContext.request.contextPath}/courses>| Courses |</a>
    <%-- <a href=${pageContext.request.contextPath}/userPage>| User Page |</a> --%>
    <a href=${pageContext.request.contextPath}/logout>Logout</a>
</nav>
