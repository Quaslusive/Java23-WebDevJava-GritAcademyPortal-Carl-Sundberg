<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty studentCourses}">
    <p>No courses found for students .</p>
</c:if>



<c:forEach var="student" items="${studentCourses}">
    <table class="table">
        <thead>
        <tr>
            <th>Student Name</th>
            <th>Course Name</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${student.studentName}</td>
            <td>${student.courseName}</td>
            <td>${student.courseDescription}</td>
        </tr>
        </tbody>
    </table>

</c:forEach>
