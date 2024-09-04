
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<jsp:useBean id="courses" class="model.Database" scope="request" />
--%>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h2>Available Courses</h2>
    <ul>
        <c:forEach var="course" items="${courses}">
            <li><strong>${course.name}</strong>: ${course.description}</li>
        </c:forEach>
    </ul>
</div>

<%@ include file="Fragments/footer.jsp" %>
