package servlet;

//importamos las librerias necesarias
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//definimos el servlet y su mapeo
@WebServlet("/cabeceros-request")
//creamos la clase que extiende de HttpServlet
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //obtenemos la informacion del request
        String metodoHttp = req.getMethod();
        String requestURI = req.getRequestURI().toString();//obtenemos la URI
        String requestURL = req.getRequestURL().toString();//obtenemos la URL
        String contextPath = req.getContextPath();//obtenemos el contexto
        String servletPath = req.getServletPath();//obtenemos el servlet
        String ip = req.getRemoteAddr();//obtenemos la ip
        int port = req.getServerPort();

        try (PrintWriter out = resp.getWriter()) { //abrimos el flujo de salida
            //generar plantilla
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Manejo de cabeceros 2025-2026</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Manejo de cabeceros</h1>");
            out.println("<ul>");
            out.println("<li>Obteniendo el método de petición: " + metodoHttp + "</li>");
            out.println("<li>Obtniendo la URI: " + requestURI + "</li>");
            out.println("<li>Obteniendo la URL: " + requestURL + "</li>");
            out.println("<li>Obteniendo el contexto: " + contextPath + "</li>");
            out.println("<li>Obteniendo el servlet; "+ servletPath + "</li>");
            out.println("<li>Obteniendo la ip: " + ip + "</li>");
            out.println("<li>Obteniendo el port: " + port + "</li>");
            Enumeration<String> headersNames = req.getHeaderNames();
            //iteramos los headers
            while(headersNames.hasMoreElements()) { //mientras haya mas elementos que se muestren en formla de lista
                String cabecera = headersNames.nextElement();
                out.println("<li>" + cabecera + " : " + req.getHeader(cabecera) + "</li>");
            }

            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }

    }
}
