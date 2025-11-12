package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.LoginService;
import service.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    //  Credenciales fijas
    final static String USUARIO = "admin";
    final static String PASSWORD = "12345";

    // -------------------------------------------------------------------------
    // MÉTODO GET → muestra el mensaje de bienvenida si hay sesión activa
    // -------------------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsermane(request);

        //  Verifica si el usuario tiene sesión activa
        if (usernameOptional.isPresent()) {
            HttpSession session = request.getSession(false);
            Integer contador = (Integer) session.getAttribute("contador");

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='es'>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("<title>Bienvenido</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + ", has iniciado sesión con éxito!</h1>");
                out.println("<p>Te has logueado <strong>" + contador + "</strong> veces.</p>");
                out.println("<p><a href='" + request.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("<p><a href='" + request.getContextPath() + "/logout'>Cerrar sesión</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Si no hay sesión, vuelve al formulario de login
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    // -------------------------------------------------------------------------
    // MÉTODO POST → procesa el formulario y maneja el contador con cookies
    // -------------------------------------------------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //  Valida usuario y contraseña
        if (USUARIO.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = request.getSession();

            //  Buscar cookie existente del contador
            int contador = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("contadorLogin".equals(c.getName())) {
                        try {
                            contador = Integer.parseInt(c.getValue());
                        } catch (NumberFormatException e) {
                            contador = 0; // por si la cookie está dañada
                        }
                    }
                }
            }

            //  Incrementar el contador
            contador++;

            //  Guardar contador en la cookie (dura 1 día)
            Cookie cookieContador = new Cookie("contadorLogin", String.valueOf(contador));
            cookieContador.setMaxAge(24 * 60 * 60); // 1 día en segundos
            response.addCookie(cookieContador);

            //  También guardar en la sesión actual
            session.setAttribute("username", username);
            session.setAttribute("contador", contador);

            //  Redirigir al servlet GET
            response.sendRedirect(request.getContextPath() + "/login.html");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales incorrectas");
        }
    }
}


