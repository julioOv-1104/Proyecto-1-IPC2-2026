package Servlets;

import DAOs.DestinoDAO;
import Modelos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DestinoServlet", urlPatterns = {"/DestinoServlet"})
public class DestinoServlet extends HttpServlet {

    DestinoDAO destinoDao = new DestinoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        ArrayList<Destino> destinos = destinoDao.ObtenerDestinos();

        if (destinos.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No hay destinos\"}");
            
        } else {
            String json = om.writeValueAsString(destinos);
            response.getWriter().print(json);

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPut para crear un cliente o para editarlo

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        //Accion define si se está creando editado destino o si se va a editar
        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {
                case "crear":
                    crearDestino(request, response, om);
                    break;

                case "editar":
                    editarDestino(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void crearDestino(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            Destino entrante = om.readValue(request.getInputStream(), Destino.class);

            Destino nuevo = destinoDao.registrarDestino(entrante);

            if (nuevo == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"El destino ya esta registrado\"}");
            } else {

                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Destino registrado con exito");

            }

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR DESTINO DESDE SERVLET " + e.getMessage());
        }

    }

    private void editarDestino(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            Destino entrante = om.readValue(request.getInputStream(), Destino.class);

            Destino editado = destinoDao.editarDestino(entrante);

            if (editado == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Error al editar el destino\"}");
            } else {

                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Destino editado con exito}");

            }

        } catch (Exception e) {
            System.out.println("ERROR AL EDITAR DESTINO DESDE SERVLET " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPost paraeliminar destino

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        Destino entrante = om.readValue(request.getInputStream(), Destino.class);

        if (destinoDao.eliminarDestino(entrante)) {//si todo sale bien

            response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Destino eliminado con exito}");

        } else {

            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Error al editar el destino\"}");
        }

    }

}
