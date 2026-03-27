package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class ReportesVentasIntervalo {

    private String paquete;
    private String pasajero;
    private String agente;
    private double costoTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecha_creacion;

    public ReportesVentasIntervalo(String paquete, String pasajero, String agente, double costoTotal, Date fecha_creacion) {
        this.paquete = paquete;
        this.pasajero = pasajero;
        this.agente = agente;
        this.costoTotal = costoTotal;
        this.fecha_creacion = fecha_creacion;
    }

    public ReportesVentasIntervalo() {
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}
