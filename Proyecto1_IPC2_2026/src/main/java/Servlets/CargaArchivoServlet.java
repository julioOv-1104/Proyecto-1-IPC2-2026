
package Servlets;

import Servicios.CargarArchivoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class CargaArchivoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part archivo = request.getPart("archivo");

        InputStream input = archivo.getInputStream();

        CargarArchivoService service = new CargarArchivoService();
        service.procesarArchivo(input);

        response.setContentType("application/json");
        response.getWriter().write("{\"mensaje\":\"Archivo procesado\"}");
    }
    
}
