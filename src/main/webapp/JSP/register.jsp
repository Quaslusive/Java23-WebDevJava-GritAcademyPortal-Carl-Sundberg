<%--
  Created by IntelliJ IDEA.
  User: Gamingcalle_Mark-3
  Date: 2024-08-21
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h2>Register New User</h2>
    <form action="register" method="post">
        <label for="fName">First Name:</label>
        <input type="text" id="fName" name="fName" required>
        <br>
        <label for="lName">Last Name:</label>
        <input type="text" id="lName" name="lName" required>
        <br>
        <label for="town">Town:</label>
        <input type="text" id="town" name="town">
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone">
        <br>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <label for="privilage_type">Privilage Type:</label>
        <select id="privilage_type" name="privilage_type">
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select>
        <br>
        <button type="submit">Register</button>
    </form>
</div>

<%@ include file="Fragments/footer.jsp" %>
