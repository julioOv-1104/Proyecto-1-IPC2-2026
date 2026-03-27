
package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class HistorialReservaciones {
 
   private String cliente;
   private String numero_reserva;
   private String paquete;
   
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
   private Date fecha_viaje;
   private String estado;
   private double costo_total;

    public HistorialReservaciones() {
    }

    public HistorialReservaciones(String cliente, String numero_reserva, String paquete, Date fecha_viaje, String estado, double costo_total) {
        this.cliente = cliente;
        this.numero_reserva = numero_reserva;
        this.paquete = paquete;
        this.fecha_viaje = fecha_viaje;
        this.estado = estado;
        this.costo_total = costo_total;
    }
   
   

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumero_reserva() {
        return numero_reserva;
    }

    public void setNumero_reserva(String numero_reserva) {
        this.numero_reserva = numero_reserva;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public Date getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(Date fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }
   
   
    
    
}
