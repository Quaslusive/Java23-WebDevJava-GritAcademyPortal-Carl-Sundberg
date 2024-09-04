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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userTypeParam = request.getParameter("userType");

        UserType userType = UserType.valueOf(userTypeParam.toUpperCase());

        UserBean newUser = new UserBean();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setUserType(userType);

        Database db = Database.getInstance();
     //   db.addUser(newUser);

        response.sendRedirect("login.jsp");
    }
}
