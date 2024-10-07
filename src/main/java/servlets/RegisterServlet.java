/*
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

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userTypeParam = request.getParameter("userType");

        try {
            UserType userType = UserType.valueOf(userTypeParam.toUpperCase());


            UserBean newUser = new UserBean();
            newUser.setUsername(username);
            newUser.setPassword(password);  // In production, hash the password before saving it
            newUser.setUserType(userType);

            Database db = new Database();

            boolean success = db.addUser(newUser);  // Ensure addUser method exists in Database

            if (success) {
                response.sendRedirect("login.jsp");  // Redirect to login if registration is successful
            } else {
                response.sendRedirect("register.jsp?error=Registration failed");
            }

        } catch (IllegalArgumentException e) {
            response.sendRedirect("register.jsp?error=Invalid user type");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Registration failed");
        }
    }
}
*/
