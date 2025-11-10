package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        // Login sencillo: usuario=admin, password=123
        if ("admin".equals(usuario) && "123".equals(password)) {
            Cookie cookie = new Cookie("usuarioLogeado", usuario);
            cookie.setMaxAge(60 * 2); // dura 2 minutos
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/productos.html");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<h3>Credenciales incorrectas</h3>");
            resp.getWriter().println("<a href='login.jsp'>Volver al login</a>");
        }
    }
}
