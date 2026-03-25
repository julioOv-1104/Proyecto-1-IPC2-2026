package Servicios;

import Modelos.*;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ExportarReportesAdmin {

    public void exportarVentasIntervalo(ArrayList<ReportesVentasIntervalo> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReportesVentasIntervalo.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Ventas en un intervalo de tiempo----------"));

            for (ReportesVentasIntervalo reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("Paquete: " + reporte.getPaquete()));
                document.add(new Paragraph("Pasajero: " + reporte.getPaquete()));
                document.add(new Paragraph("Agente: " + reporte.getAgente()));
                document.add(new Paragraph("Costo Total: " + reporte.getCostoTotal()));
                document.add(new Paragraph("Fecha: " + reporte.getFecha_creacion()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
     public void exportarCancelacionIntervalo(ArrayList<ReportesCancelacionIntervalo> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReportesCancelacionIntervalo.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Cancelaciones en un intervalo de tiempo----------"));

            for (ReportesCancelacionIntervalo reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("Numero de reserva: " + reporte.getNumero_reserva()));
                document.add(new Paragraph("Fecha cancelacion: " + reporte.getFecha_cancelacion()));
                document.add(new Paragraph("Monto Reembolsado: " + reporte.getMonto_reembolsado()));
                document.add(new Paragraph("Perdida: " + reporte.getPerdida()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }    
     
     public void exportarGananciasIntervalo(ArrayList<ReporteGananciasIntervalo> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReporteGananciasIntervalo.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Ganancias en un intervalo de tiempo----------"));

            for (ReporteGananciasIntervalo reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("Ganancias Brutas: " + reporte.getGanancias_brutas()));
                document.add(new Paragraph("Total Reembolsos: " + reporte.getTotal_reembolsos()));
                document.add(new Paragraph("Ganancias Neta: " + reporte.getGanancias_neta()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     
     public void exportarAgenteMasVentas(ArrayList<ReporteAgenteMasVentas> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReporteAgenteMasVentas.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Agente con mas ventas en un intervalo de tiempo----------"));

            for (ReporteAgenteMasVentas reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("ID Usuario: " + reporte.getId_usuario()));
                document.add(new Paragraph("Nombre Usuario: " + reporte.getNombre_usuario()));
                document.add(new Paragraph("Total Ventas: " + reporte.getTotal_ventas()));
                document.add(new Paragraph("Monto Total: " + reporte.getMonto_total()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     
     public void exportarAgenteMasGanancias(ArrayList<ReporteAgenteMasVentas> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReporteAgenteGanancias.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Agente con mas ganancias en un intervalo de tiempo----------"));

            for (ReporteAgenteMasVentas reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("ID Usuario: " + reporte.getId_usuario()));
                document.add(new Paragraph("Nombre Usuario: " + reporte.getNombre_usuario()));
                document.add(new Paragraph("Total Ventas: " + reporte.getTotal_ganancia()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     
     public void exportarPaqueteMasVendido(ArrayList<ReportePaqueteVendido> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReportePaqueteMenosVendido.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Paquete menos vendido en un intervalo de tiempo----------"));

            for (ReportePaqueteVendido reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("Paquete : " + reporte.getPaquete()));
                document.add(new Paragraph("Numero Reserva: " + reporte.getNumero_reserva()));
                document.add(new Paragraph("Fecha Creacion : " + reporte.getFecha_reserva()));
                document.add(new Paragraph("Costo Total: " + reporte.getCosto_total()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     
     public void exportarPaqueteMenosVendido(ArrayList<ReportePaqueteVendido> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReportePaqueteMasVendido.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Paquete mas vendido en un intervalo de tiempo----------"));

            for (ReportePaqueteVendido reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("Paquete : " + reporte.getPaquete()));
                document.add(new Paragraph("Numero Reserva: " + reporte.getNumero_reserva()));
                document.add(new Paragraph("Fecha Creacion : " + reporte.getFecha_reserva()));
                document.add(new Paragraph("Costo Total: " + reporte.getCosto_total()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     
     public void exportarOcupacionDestino(ArrayList<ReporteOcupacionDestino> reportes) {

        Document document = new Document();

        try {
            String ruta = "ReporteOcupacionDestino.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(ruta));

            document.open();
            document.add(new Paragraph("----------Ocupacion por destino en un intervalo de tiempo----------"));

            for (ReporteOcupacionDestino reporte : reportes) {//va llenando el documento

                document.add(new Paragraph("Destino : " + reporte.getNombre_destino()));
                document.add(new Paragraph("Cantidad de reservaciones : " + reporte.getCantidad_reservaciones()));
                
                document.add(new Paragraph("------------------------------------------------"));

            }
            
            document.close();

            Desktop.getDesktop().open(new File(ruta));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
}
