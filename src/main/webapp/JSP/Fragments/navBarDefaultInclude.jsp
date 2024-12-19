<nav class="navbar">
        <a href="${pageContext.request.contextPath}/index.html">Home</a>
        <c:if test="${not empty user}">
        <%--        <a href=${pageContext.request.contextPath}/userPage>User Page</a>--%>
                <a href=${pageContext.request.contextPath}/courses>Courses</a>
            <a href=${pageContext.request.contextPath}/logout>Logout</a>
        </c:if>
</nav>

