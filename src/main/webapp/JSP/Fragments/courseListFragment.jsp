<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table-container">
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>YHP</th>
        <th>Description</th>
    </tr>
    <c:forEach  items="${coursesData}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.name}</td>
            <td>${course.yhp}</td>
            <td>${course.description}</td>
        </tr>
    </c:forEach>
</table>
