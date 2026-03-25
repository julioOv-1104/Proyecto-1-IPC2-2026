package Servicios;

import DAOs.*;
import Modelos.*;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

public class RegistrarPagoService {

    private PagoDAO pagodao = new PagoDAO();

    public Pago pagarReserva(Pago pago) {
        if (pagodao.obtenerEstadoReserva(pago.getNumero_reserva())) {
            //está pendiente aun
            return (verificarCostoYmonto(pago));

        }

        return null;
    }

    private Pago verificarCostoYmonto(Pago pago) {

        double costo = pagodao.obtenerCosto(pago);
        double pagado = pagodao.obtenerTotalPagado(pago);

        if (pago.getMonto() <= 0) {
            //no puede pagar 0 ni numeros negativos

        } else if ((pago.getMonto() + pagado) < costo) {//verifica que pagando no supere el costo total
            pago.setEstado("PARCIAL");
            pagodao.registrarPago(pago);//se registra el pago
            exportarComprobantePago(pago);
            return pago;

        } else if ((pago.getMonto() + pagado) == costo) {
            pago.setEstado("COMPLETO");
            pagodao.registrarPago(pago);
            pagodao.confirmaarReserva(pago);//se confirma la reserva
            exportarComprobantePago(pago);
            return pago;
        }
        return null;
    }

    private void exportarComprobantePago(Pago pago) {

        Document document = new Document();

        try {

            PdfWriter.getInstance(document,
                    new FileOutputStream("comprobante.pdf"));

            document.open();

            document.add(new Paragraph("----------Comprobante de Pago----------"));
            document.add(new Paragraph("Reserva: "+pago.getNumero_reserva()));
            document.add(new Paragraph("Fecha: "+pago.getFecha()));
            document.add(new Paragraph("Monto: Q"+pago.getMonto()));
            document.add(new Paragraph("Metodo: "+pago.getMetodo_pago()));

            document.close();
            
            System.out.println("SE CREÓ EL COMPROBANTE PDF");
            Desktop.getDesktop().open(new File("comprobante.pdf"));//abre el PDF

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
