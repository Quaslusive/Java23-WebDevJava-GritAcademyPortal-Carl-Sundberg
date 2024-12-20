package servlets;

import model.*;

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
        db = new Database();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("loginServlet doGet");
        resp.sendRedirect(req.getContextPath() + "/JSP/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String guestLogin = request.getParameter("guestLogin");

        if (guestLogin != null && guestLogin.equals("true")) {
            UserBean guestUser = new UserBean();
            guestUser.setUsername("Guest");
            guestUser.setStateType(StateType.ANONYMOUS);
            session.setAttribute("user", guestUser);
            response.sendRedirect(request.getContextPath() + "/JSP/guest/guestUserPage.jsp");
            // Paul Rudd predicted the future av AI
            // response.sendRedirect("https://celeryman.alexmeub.com/");
            return;
        }
        UserBean user = db.findUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            user.setStateType(StateType.CONFIRMED);
            session.setAttribute("user", user);

            if (user.getUserType() == UserType.TEACHER) {
                if (user.getPrivilegeType() == PrivilegeType.ADMIN) {
                    response.sendRedirect(request.getContextPath() + "/JSP/admin/teacherAdminPage.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/JSP/teachers/teacherUserPage.jsp");
                }
            } else if (user.getUserType() == UserType.STUDENT) {
                response.sendRedirect(request.getContextPath() + "/JSP/students/studentUserPage.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/JSP/login.jsp?error=Invalid user type");
            }
            System.out.println("loginServlet doPost deets");
            System.out.println("...................................................");
            System.out.println("username: " + username + ", password: " + password);
            System.out.println("User in session: " + user.getFname() + " " + user.getLname());
            System.out.println("User type: " + user.getUserType());
            System.out.println("User privilege type: " + user.getPrivilegeType());
            System.out.println("...................................................");


        } else {
            response.sendRedirect(request.getContextPath() + "/JSP/login.jsp?error=Invalid username or password");
        }
    }
}
