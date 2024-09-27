package servlets;

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

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private Database db;

    @Override
    public void init() throws ServletException {
        // Initialize the Database instance directly
        db = new Database(); // No need for a connection from ServletContext
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward to the login page (JSP)
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Fetch the user from the database
        UserBean user = db.findUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            // Create a session and store the user object in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Determine the user type and redirect accordingly
            if (user.getUserType() == UserType.TEACHER) {
                response.sendRedirect("/JSP/TeacherPage.jsp");
            } else if (user.getUserType() == UserType.STUDENT) {
                response.sendRedirect("/JSP/StudentPage.jsp");
            } else {
                response.sendRedirect("/JSP/login.jsp?error=Invalid user type");
            }
        } else {
            // Redirect back to login page with an error message
            response.sendRedirect("/JSP/login.jsp?error=Invalid username or password");
        }
    }
}
