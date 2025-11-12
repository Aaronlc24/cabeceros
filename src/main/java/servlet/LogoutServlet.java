package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.LoginService;
import service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsermane(request);
        if (username.isPresent()) {
           HttpSession session = request.getSession();
           session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
