package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import service.LoginService;
import service.LoginServiceSessionImpl;
import service.ProductoService;
import service.ProductoServiceImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static java.lang.System.out;

//Implementamos la anotación
@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsermane(request);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<title>Listado de productos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de productos</h1>");
            if (usernameOptional.isPresent()) {
                out.println("<div style='color: blue;'>hola " + usernameOptional.get() + " ha iniciado sesión</div>");
            }
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            if (usernameOptional.isPresent()) {
            out.println("<th>precio</th>");}
        }
        out.println("</tr>");
        productos.forEach(p -> {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getNombre() + "</td>");
            out.println("<td>" + p.getTipo() + "</td>");
            if (usernameOptional.isPresent()) {
            out.println("<td>" + p.getPrecio() + "</td>");
            }
            out.println("</tr>");
        });
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        }
   }
