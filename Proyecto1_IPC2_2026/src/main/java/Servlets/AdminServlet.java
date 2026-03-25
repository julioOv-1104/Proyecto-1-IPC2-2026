package Servlets;

import DAOs.UsuarioDAO;
import Modelos.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private UsuarioDAO usuarioDao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
                    registrarUsuario(request, response, om);
                    break;

                case "activar/cancelar":
                    cancelarActivar(request, response, om);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {

        try {
            response.setContentType("application/json; charset=UTF-8");

            Usuario entrante = om.readValue(request.getInputStream(), Usuario.class);

            Usuario nuevo = usuarioDao.registrarUsuario(entrante);

            if (nuevo == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error al registrar usuario\"}");

            } else {

                String json = om.writeValueAsString(nuevo);
                response.getWriter().print(json);
            }
        } catch (Exception e) {
            System.out.println("ERROR AL INGRESAR USUARIO DESDE SERVLET");
        }

    }

    private void cancelarActivar(HttpServletRequest request, HttpServletResponse response, ObjectMapper om) {
        
        try {
            response.setContentType("application/json; charset=UTF-8");

            Usuario entrante = om.readValue(request.getInputStream(), Usuario.class);

            boolean cancelacion = usuarioDao.cancelarActivarUsuario(entrante);

            if (cancelacion) {
                //todo bien
                response.getWriter().print("{\"status\":\"exito\",\"mensaje\":\"Se cambio el estado del usuario\"}");

            } else {

                  response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error al cambiar el estado del usuario\"}");

            }
        } catch (Exception e) {
            System.out.println("ERROR AL CANCELAR/ACTIVAR USUARIO DESDE SERVLET");
        }

    }

}
