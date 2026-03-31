
package Servlets;

import DAOs.ServicioDAO;
import Modelos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServicioServlet", urlPatterns = {"/ServicioServlet"})
public class ServicioServlet extends HttpServlet{
    
    private ServicioDAO servicioDao = new ServicioDAO();
    
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        ArrayList<Servicio> servicios = servicioDao.obtenerServicios();

        if (servicios.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error con obtener servicios\"}");

        } else {
            String json = om.writeValueAsString(servicios);
            response.getWriter().print(json);

        }

    }
    
}
