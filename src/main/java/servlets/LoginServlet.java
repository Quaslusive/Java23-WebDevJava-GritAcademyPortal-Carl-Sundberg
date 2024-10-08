package servlets;

import model.Course;
import model.Database;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private Database db;

    @Override
    public void init() throws ServletException {
        db = new Database();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/JSP/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        UserBean user = db.findUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user.getUserType() == UserType.TEACHER) {
                response.sendRedirect(request.getContextPath() + "/JSP/userPage.jsp");
            } else if (user.getUserType() == UserType.STUDENT) {
                response.sendRedirect(request.getContextPath() + "/JSP/userPage.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/JSP/login.jsp?error=Invalid user type");
            }

            System.out.println("username " + username + ", password " + password);
            System.out.println("User in session: " + user.getUsername());
            System.out.println("User type: " + user.getUserType());

        } else {
            response.sendRedirect(request.getContextPath() + "/JSP/login.jsp?error=Invalid username or password");
        }
    }
}
