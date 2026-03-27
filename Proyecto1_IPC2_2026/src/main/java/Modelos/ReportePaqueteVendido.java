package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class ReportePaqueteVendido {

    private String paquete;
    private String numero_reserva;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecha_reserva;
    private double costo_total;

    public ReportePaqueteVendido(String paquete, String numero_reserva, Date fecha_reserva, double costo_total) {
        this.paquete = paquete;
        this.numero_reserva = numero_reserva;
        this.fecha_reserva = fecha_reserva;
        this.costo_total = costo_total;
    }

    public ReportePaqueteVendido() {
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getNumero_reserva() {
        return numero_reserva;
    }

    public void setNumero_reserva(String numero_reserva) {
        this.numero_reserva = numero_reserva;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

}
