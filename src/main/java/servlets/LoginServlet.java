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
            //  response.sendRedirect(request.getContextPath() + "/JSP/userPage.jsp");

            // Paul Rudd predicted the future av AI
            response.sendRedirect("https://celeryman.alexmeub.com/");
            return;
        }

        UserBean user = db.findUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            user.setStateType(StateType.CONFIRMED);
            session.setAttribute("user", user);

            // Kontrollera user type och omdirigera
            if (user.getUserType() == UserType.TEACHER) {
                response.sendRedirect(request.getContextPath() + "/JSP/teacherUserPage.jsp");
            } else if (user.getUserType() == UserType.STUDENT) {
                response.sendRedirect(request.getContextPath() + "/JSP/userPage.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/JSP/login.jsp?error=Invalid user type");
            }

            System.out.println("username: " + username + ", password: " + password);
            System.out.println("User in session: " + user.getUsername());
            System.out.println("User type: " + user.getUserType());

        } else {
            // Felaktigt användarnamn eller lösenord
            response.sendRedirect(request.getContextPath() + "/JSP/login.jsp?error=Invalid username or password");
        }
    }
}
