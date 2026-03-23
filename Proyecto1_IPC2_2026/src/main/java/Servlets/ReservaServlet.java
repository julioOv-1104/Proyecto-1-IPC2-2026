package Servlets;

import DAOs.*;
import Modelos.*;
import Servicios.CancelacionReserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {

    private ReservaDAO reservaDao = new ReservaDAO();
    private CancelacionReserva cancelacion = new CancelacionReserva();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        ArrayList<Reserva> reservas = reservaDao.obtenerTodasReservas();

        if (reservas.isEmpty()) {
            //no hay reservas
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay reservas\"}");

        } else {

            String json = om.writeValueAsString(reservas);
            response.getWriter().print(json);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                case "registrar":
                    registrarReserva(request, response, om);
                    break;

                case "cancelar":
                    cancelarReserva(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void registrarReserva(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {

            Reserva entrante = om.readValue(request.getInputStream(), Reserva.class);

            Reserva nueva = reservaDao.registrarReserva(entrante);

            if (nueva == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error al registrar la reserva\"}");

            } else {
                String json = om.writeValueAsString(nueva);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
        }

    }

    private void cancelarReserva(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {

            Reserva aCancelar = om.readValue(request.getInputStream(), Reserva.class);

            if (cancelacion.cancelarReserva(aCancelar)) {

                String json = om.writeValueAsString(aCancelar);
                response.getWriter().print(json);

            } else {
                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error al registrar la reserva\"}");

            }
        } catch (Exception e) {
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPut para vincular cliente y reserva

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        Map<String, Object> datos = om.readValue(request.getInputStream(), Map.class);

        String numero_reserva = (String) datos.get("numero_reserva");
        String dpi = (String) datos.get("dpi");


        if (reservaDao.vincularReservaCliente(numero_reserva, dpi)) {

            response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"cliente y reserva vinculados\"}");

        } else {

            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error al vincular cliente y reserva\"}");
        }

    }

}
