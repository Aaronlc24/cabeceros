package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import service.ProductoService;
import service.ProductoServiceImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Implementamos la anotación
@WebServlet({"/productos.xls","/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();

        // Verificar cookie de login
        boolean logeado = false;
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals("usuarioLogeado")) {
                    logeado = true;
                    break;
                }
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        }

        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("<title>Listado de Productos</title>");

                // 🎨 ESTILOS CSS DIRECTOS
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background: #f3f4f6; margin: 0; padding: 20px; }");
                out.println("h1 { color: #2c3e50; text-align: center; }");
                out.println("table { border-collapse: collapse; width: 80%; margin: 30px auto; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
                out.println("th, td { border: 1px solid #ccc; padding: 12px 18px; text-align: center; }");
                out.println("th { background-color: #4CAF50; color: white; font-size: 16px; }");
                out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
                out.println("tr:hover { background-color: #f1f1f1; transition: 0.3s; }");
                out.println("a { display: inline-block; margin: 5px; padding: 8px 15px; background: #3498db; color: white; text-decoration: none; border-radius: 5px; }");
                out.println("a:hover { background: #2980b9; }");
                out.println(".contenedor { text-align: center; }");
                out.println("</style>");

                out.println("</head>");
                out.println("<body>");
                out.println("<h1>📦 Listado de productos</h1>");
                out.println("<div class='contenedor'>");
                out.println("<a href='" + req.getContextPath() + "/productos.xls'>📊 Exportar a Excel</a>");
                out.println("<a href='" + req.getContextPath() + "/login.jsp'>🔐 Ir al Login</a>");
                out.println("</div>");
            }

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            if (logeado) {
                out.println("<th>Tipo</th>");
                out.println("<th>Precio</th>");
            }
            out.println("</tr>");

            for (Producto p : productos) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                if (logeado) {
                    out.println("<td>" + p.getTipo() + "</td>");
                    out.println("<td>$" + String.format("%.2f", p.getPrecio()) + "</td>");
                }
                out.println("</tr>");
            }

            out.println("</table>");
            if (!logeado) {
                out.println("<div class='contenedor'><p style='color:#e74c3c; font-weight:bold;'>⚠ Debes iniciar sesión para ver todos los detalles de los productos.</p></div>");
            }

            if (!esXls) {
                out.println("</body></html>");
            }
        }
    }
}
