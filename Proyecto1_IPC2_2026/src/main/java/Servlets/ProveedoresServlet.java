package Servlets;

import DAOs.ProveedorDAO;
import Modelos.Proveedor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProveedoresServlet", urlPatterns = {"/ProveedoresServlet"})
public class ProveedoresServlet extends HttpServlet {

    private ProveedorDAO proveedorDao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        ArrayList<Proveedor> proveedores = proveedorDao.obtenerProveedores();

        if (proveedores.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Aun no hay proveedores\"}");

        } else {

            String json = om.writeValueAsString(proveedores);
            response.getWriter().print(json);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        Proveedor entrante = om.readValue(request.getInputStream(), Proveedor.class);

        Proveedor nuevo = proveedorDao.registrarProveedor(entrante);
        
        if (nuevo == null) {

                response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"Ocurrio un error al registrar el paquete\"}");

            } else {
                String json = om.writeValueAsString(nuevo);
                response.getWriter().print(json);
            }

    }

}
