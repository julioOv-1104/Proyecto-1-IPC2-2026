package Servlets;

import DAOs.ClienteDAO;
import Modelos.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "AtencionClienteServlet", urlPatterns = {"/AtencionClienteServlet"})
public class AtencionClienteServlet extends HttpServlet {

    private ClienteDAO clienteDao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //doPut para crear un cliente o para editarlo

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        //Accion define si se está creando nuevo cleinte o si se va a editar
        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {
                case "crear":
                    crearCliente(request, response, om);
                    break;

                case "editar":
                    editarCliente(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void crearCliente(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om) {

        try {
            Cliente entrante = om.readValue(request.getInputStream(), Cliente.class);

            Cliente nuevo = clienteDao.registrarCliente(entrante);

            if (nuevo == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"El DPI ya esta registrado\"}");
            } else {

                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Cliente registrado con exito}");

            }

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR CLIENTE DESDE SERVLET " + e.getMessage());
        }

    }
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om) {

        try {
            Cliente entrante = om.readValue(request.getInputStream(), Cliente.class);

            Cliente nuevo = clienteDao.editarCliente(entrante);

            if (nuevo == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"ocurrio un error editando el cliente\"}");
            } else {

                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Cliente editado con exito}");

            }

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR CLIENTE DESDE SERVLET " + e.getMessage());
        }

    }

}
