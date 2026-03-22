package Servlets;

import DAOs.HistorialReservacionesDAO;
import Modelos.Cliente;
import Modelos.HistorialReservaciones;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HistorialAtencionClienteServlet", urlPatterns = {"/HistorialAtencionClienteServlet"})
public class HistorialAtencionClienteServlet extends HttpServlet {

   private HistorialReservacionesDAO historialDao = new HistorialReservacionesDAO();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPost paracrear una reserva 

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {
                case "reservaciones": //Consultar el historial de reservaciones de un cliente
                    MostrarReservacionesCliente(request, response, om);
                    break;

                case "pagos":
                    // cancelarReserva(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void MostrarReservacionesCliente(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            response.setContentType("application/json; charset=UTF-8");

            ArrayList<HistorialReservaciones> historial = new ArrayList<>();
            
            Cliente entrante = om.readValue(request.getInputStream(), Cliente.class);

            historial = historialDao.obtenerTodasReservas(entrante.getDpi());

            if (historial.isEmpty()) {
                //no hay reservaciones
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay reservaciones\"}");

            } else {

                String json = om.writeValueAsString(historial);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }

    }

}
