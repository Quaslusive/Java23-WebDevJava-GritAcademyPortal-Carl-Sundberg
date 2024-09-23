package servlets;

import model.Database;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userTypeParam = request.getParameter("userType");

        // Convert userTypeParam to an enum
        UserType userType = UserType.valueOf(userTypeParam.toUpperCase());

        // Create a new UserBean object
        UserBean newUser = new UserBean();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setUserType(userType);

        // Retrieve the database connection from the servlet context
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        if (conn == null) {
            throw new ServletException("Database connection not initialized properly.");
        }

        // Create a Database instance using the connection
        Database db = new Database(conn);

        // Add the new user to the database
        boolean success = db.addUser(newUser);  // Implement addUser method in Database

        // Redirect the user based on whether registration was successful
        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp?error=Registration failed");
        }
    }
}
