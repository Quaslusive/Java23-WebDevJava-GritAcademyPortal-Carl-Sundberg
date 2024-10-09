<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${empty courseData}">
    <p>No courses found.</p>
    </c:if>

    <c:forEach var="course" items="${courseData}">
    <table class="table">
        <thead>
        <tr>
            <th>Course Name</th>
            <th>YHP</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${course.name}</td>
            <td>${course.yhp}</td>
            <td>${course.description}</td>
        </tr>
        </tbody>
    </table>
    </c:forEach>
