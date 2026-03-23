package Servlets;

import DAOs.*;
import Modelos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PaqueteServlet", urlPatterns = {"/PaqueteServlet"})
public class PaqueteServlet extends HttpServlet {

    PaqueteDAO paquetedao = new PaqueteDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        ArrayList<Paquete> paquetes = paquetedao.obtenerPaquetes();

        if (paquetes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error con obtener paquetes\"}");
            
        } else {
            String json = om.writeValueAsString(paquetes);
            response.getWriter().print(json);

        }

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPost para crear y editar paquetes

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        
        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {
                case "crear":
                    crearPaquete(request, response, om);
                    break;

                case "editar":
                    editarPaquete(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        
    }
    
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPut para crear un cliente o para editarlo

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        Paquete entrante = om.readValue(request.getInputStream(), Paquete.class);

        if (paquetedao.desactivarActivarPaquete(entrante)) {//si todo sale bien

            response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Paquete activado/desactivado con exito}");

        } else {

            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Error al activar/desactivar el paquete\"}");
        }

    }

    private void crearPaquete(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            Paquete entrante = om.readValue(request.getInputStream(), Paquete.class);

            Paquete nuevo = paquetedao.registrarPaquete(entrante);

            if (nuevo == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Erro al registrar paquete\"}");
            } else {

                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Paquete registrado con exito}");

            }

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR PAQUETE DESDE SERVLET " + e.getMessage());
        }

    }

    private void editarPaquete(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            Paquete entrante = om.readValue(request.getInputStream(), Paquete.class);

            Paquete editado = paquetedao.editarPaquete(entrante);

            if (editado == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Error al editar el paquete\"}");
            } else {

                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"paquete editado con exito}");

            }

        } catch (Exception e) {
            System.out.println("ERROR AL EDITAR PAQUETE DESDE SERVLET " + e.getMessage());
        }

    }

    

}
