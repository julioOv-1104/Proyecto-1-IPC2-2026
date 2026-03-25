package Servlets;

import DAOs.ReportesAdminDAO;
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

@WebServlet(name = "ReportesAdminServlet", urlPatterns = {"/ReportesAdminServlet"})
public class ReportesAdminServlet extends HttpServlet {

    private ReportesAdminDAO adminDao = new ReportesAdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // doGet para ver los reportes del administrador

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        Map<String, Object> datos = om.readValue(request.getInputStream(), Map.class);

        String sFecha1 = (String) datos.get("fecha1");
        java.sql.Date fecha1 = java.sql.Date.valueOf(sFecha1);
        String sFecha2 = (String) datos.get("fecha2");
        java.sql.Date fecha2 = java.sql.Date.valueOf(sFecha2);//obtiene las fechas para los intervalos

        String accionRecibida = request.getParameter("accion");

        if (accionRecibida == null) {
            response.getWriter().print("{\"error\": \"Acción no especificada\"}");
            return;
        }

        try {
            switch (accionRecibida) {

                case "ventasIntervalo":
                    mostrarVentasIntervalo(request, response, om, fecha1, fecha2);
                    break;

                case "cancelacionesIntervalo":
                    mostrarCancelacionesIntervalo(request, response, om, fecha1, fecha2);
                    break;

                case "gananciaIntervalo":
                    mostrarGananciasIntervalo(request, response, om, fecha1, fecha2);
                    break;

                case "masVentas":
                    
                    mostrarAgenteVentas(request, response, om, fecha1, fecha2);
                    break;

                case "masGanancias":
                    mostrarAgenteGanancias(request, response, om, fecha1, fecha2);
                    break;

                case "paqueteMasVendido":
                    
                    mostrarPaqueteMasVendido(request, response, om, fecha1, fecha2);
                    break;

                case "paqueteMenosVendido":
                    mostrarPaqueteMenosVendido(request, response, om, fecha1, fecha2);
                    break;

                case "ocupacionDestino":
                    mostrarOcupacionDestino(request, response, om, fecha1, fecha2);
                    break;

                default:

                    response.getWriter().print("{\"error\": \"Acción no válida\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void mostrarVentasIntervalo(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReportesVentasIntervalo> reportes = new ArrayList<>();

        reportes = adminDao.obtenerVentasEnIntervalo(fecha1, fecha2);

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes de ventas en el intervalo\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }

    private void mostrarCancelacionesIntervalo(HttpServletRequest request, HttpServletResponse response, ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReportesCancelacionIntervalo> reportes = new ArrayList<>();

        reportes = adminDao.obtenerCancelacionesEnIntervalo(fecha1, fecha2);

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes de cancelaciones en el intervalo\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }
    
     private void mostrarGananciasIntervalo(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReporteGananciasIntervalo> reportes = new ArrayList<>();

        reportes = adminDao.obtenerGananciasEnIntervalo(fecha1, fecha2);

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes de ventas en el intervalo\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }
     
     
     private void mostrarAgenteVentas(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReporteAgenteMasVentas> reportes = new ArrayList<>();

        reportes = adminDao.obtenerAgenteVentas(fecha1, fecha2);

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes del agente con mas ventas\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }

     
     
     private void mostrarAgenteGanancias(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReporteAgenteMasVentas> reportes = new ArrayList<>();

        reportes = adminDao.obtenerAgenteGanancias(fecha1, fecha2);

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes del agente con mas ganancias\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }
     
     private void mostrarPaqueteMasVendido(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReportePaqueteVendido> reportes = new ArrayList<>();

        reportes = adminDao.obtenerPaqueteMasMenosVendido(fecha1, fecha2,1);
        //el parametro 1 es para que filtre por el MAS vendido

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes del paquete mas vendido\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }
     
     private void mostrarPaqueteMenosVendido(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReportePaqueteVendido> reportes = new ArrayList<>();

        reportes = adminDao.obtenerPaqueteMasMenosVendido(fecha1, fecha2,2);
        //el parametro 2 es para que filtre por el MENOS vendido

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes del paquete menos vendido\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }
     
     
     private void mostrarOcupacionDestino(HttpServletRequest request, HttpServletResponse response,
            ObjectMapper om, Date fecha1, Date fecha2)
            throws IOException {

        ArrayList<ReporteOcupacionDestino> reportes = new ArrayList<>();

        reportes = adminDao.obtenerOcupacionDestino(fecha1, fecha2);

        if (reportes.isEmpty()) {
            response.getWriter().print("{\"status\":\"error\",\"mensaje\":\"No se econtraron "
                    + "reportes de destinos por ocupacion\"}");

        } else {
            String json = om.writeValueAsString(reportes);
            response.getWriter().print(json);

        }
    }
     
}
