package Servlets;

import DAOs.ConsultasOperacionesDAO;
import DAOs.PaqueteDAO;
import Modelos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ConsultasOperacionesServlet", urlPatterns = {"/ConsultasOperacionesServlet"})
public class ConsultasOperacionesServlet extends HttpServlet {

    ConsultasOperacionesDAO consultaDao = new ConsultasOperacionesDAO();
    PaqueteDAO paqueteDao = new PaqueteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // doGet para consultas de Operaciones

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {
                case "disponibles":
                    mostrarPaquetesDisponibles(request, response, om);
                    break;

                case "detalle":
                    System.out.println("entro");
                    mostrarDetallePaquete(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void mostrarPaquetesDisponibles(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            response.setContentType("application/json; charset=UTF-8");

            ArrayList<PaquetesDisponibles> disponibles = new ArrayList<>();

            PaquetesDisponibles entrante = om.readValue(request.getInputStream(), PaquetesDisponibles.class);

            disponibles = consultaDao.obtenerPaquetesDisponibles(entrante.getDestino());

            if (disponibles.isEmpty()) {
                //no hay detalle
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay paquetes disponibles\"}");

            } else {

                String json = om.writeValueAsString(disponibles);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }
    }

    private void mostrarDetallePaquete(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {
        try {
            response.setContentType("application/json; charset=UTF-8");

            
            DetallePaquete entrante = om.readValue(request.getInputStream(), DetallePaquete.class);
            DetallePaquete detalle = paqueteDao.obtenerDetallePaquetes(entrante);

            if (detalle == null) {
                //no existe ese id_paquete
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No existe este paquete\"}");

            } else {

                String json = om.writeValueAsString(detalle);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }

    }

}
