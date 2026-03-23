package Servlets;

import DAOs.HistorialAtencionClienteDAO;
import Modelos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "HistorialAtencionClienteServlet", urlPatterns = {"/HistorialAtencionClienteServlet"})
public class HistorialAtencionClienteServlet extends HttpServlet {

    private HistorialAtencionClienteDAO historialDao = new HistorialAtencionClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doGet para las consultas

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {
                case "reservaciones": 
                    mostrarReservacionesCliente(request, response, om);
                    break;

                case "pagos":
                    mostrarPagosReservaciones(request, response, om);
                    break;

                case "disponibles":
                    mostrarReservasDisponibles(request, response, om);
                    break;
                case "reservasHoy":
                    mostrarReservasHoy(request, response, om);
                    break;
                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void mostrarReservacionesCliente(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            response.setContentType("application/json; charset=UTF-8");

            ArrayList<HistorialReservaciones> historial = new ArrayList<>();

            Cliente entrante = om.readValue(request.getInputStream(), Cliente.class);

            historial = historialDao.obtenerTodasReservasCliente(entrante.getDpi());

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

    private void mostrarPagosReservaciones(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            response.setContentType("application/json; charset=UTF-8");

            ArrayList<HistorialPagosReserva> historial = new ArrayList<>();

            Reserva entrante = om.readValue(request.getInputStream(), Reserva.class);

            historial = historialDao.obtenerPagosDeReserva(entrante.getNumero_reserva());

            if (historial.isEmpty()) {
                //no hay pagos
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay pagos realizados\"}");

            } else {

                String json = om.writeValueAsString(historial);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }

    }

    private void mostrarReservasDisponibles(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            response.setContentType("application/json; charset=UTF-8");

            ArrayList<ReservacionesFechaDestino> disponibles = new ArrayList<>();

            Map<String, Object> datos = om.readValue(request.getInputStream(), Map.class);

            Date fecha = (Date) datos.get("fecha");
            String destino = (String) datos.get("destino");


            disponibles = historialDao.obtenerReservacionesDisponibles(fecha, destino);

            if (disponibles.isEmpty()) {
                //no hay reservas
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay reservas disponibles\"}");

            } else {

                String json = om.writeValueAsString(disponibles);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }

    }

    private void mostrarReservasHoy(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {
        
        try {
            response.setContentType("application/json; charset=UTF-8");

            ArrayList<Reserva> historial = new ArrayList<>();

            historial = historialDao.obtenerReservacionesDelDia();

            if (historial.isEmpty()) {
                //no hay reservas hoy
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay reservas realizadas hoy\"}");

            } else {

                String json = om.writeValueAsString(historial);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }
    
    }
    

}
