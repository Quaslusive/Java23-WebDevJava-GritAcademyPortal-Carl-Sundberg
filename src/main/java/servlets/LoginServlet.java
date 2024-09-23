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
import java.sql.Connection;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private Database db;

    @Override
    public void init() throws ServletException {
        // Hämta DBConnection från ServletContext
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        // Kontrollera om anslutningen är null
        if (conn != null) {
            // Skapa en instans av Database med den hämtade anslutningen
            db = new Database(conn);
        } else {
            throw new ServletException("Database connection not initialized properly.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBean user = db.findUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            // Determine the user type and redirect accordingly
            if (user.getUserType() == UserType.TEACHER) {
                response.sendRedirect("TeacherPage.jsp");
            } else if (user.getUserType() == UserType.STUDENT) {
                response.sendRedirect("StudentPage.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Invalid user type");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid username or password");
        }
    }
}
