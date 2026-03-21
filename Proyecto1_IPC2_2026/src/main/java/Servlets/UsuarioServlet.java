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

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {//doPost para poder hacer el login de los usuarios

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json; charset=UTF-8");

        Usuario entrante = objectMapper.readValue(request.getInputStream(), Usuario.class);

        Usuario usuario = usuarioDao.login(entrante.getNombre_usuario(), entrante.getPassword());

        if (usuario == null) {

            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Credenciales incorrectas\"}");

        } else {
            String json = objectMapper.writeValueAsString(usuario);
            response.getWriter().print(json);
        }
    }

}
