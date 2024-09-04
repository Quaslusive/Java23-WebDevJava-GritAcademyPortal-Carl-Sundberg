<%--
  Created by IntelliJ IDEA.
  User: Gamingcalle_Mark-3
  Date: 2024-08-21
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>

