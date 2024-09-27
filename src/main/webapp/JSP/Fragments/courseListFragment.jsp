<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="course-list">
    <!-- Check if courses are empty -->
    <c:if test="${empty courses}">
        <p>No courses found.</p>
    </c:if>

    <!-- Loop through courses and display them -->
    <c:forEach var="course" items="${courses}">
        <div class="course">
            <h3>${course.name}</h3>
            <p>${course.description}</p>
        </div>
    </c:forEach>
</div>
