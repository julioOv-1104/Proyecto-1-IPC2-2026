
package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Reserva {
    
    private String numero_reserva;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_creacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_viaje;
    
    
    private int id_paquete;
    private int id_usuario;
    private int cantidad_personas;
    private double costo_total;
    private String estado = "PENDIENTE";//es pendiente por defecto
    
    private Cliente[] pasajeros;

    public Reserva() {
    }

    public Reserva(String numero_reserva, Date fecha_creacion, Date fecha_viaje, int id_paquete, int id_usuario, int cantidad_personas, double costo_total, String estado) {
        this.numero_reserva = numero_reserva;
        this.fecha_creacion = fecha_creacion;
        this.fecha_viaje = fecha_viaje;
        this.id_paquete = id_paquete;
        this.id_usuario = id_usuario;
        this.cantidad_personas = cantidad_personas;
        this.costo_total = costo_total;
        this.estado = estado;
        
        this.pasajeros = new Cliente[cantidad_personas];
    }

    public String getNumero_reserva() {
        return numero_reserva;
    }

    public void setNumero_reserva(String numero_reserva) {
        this.numero_reserva = numero_reserva;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(Date fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }

    public int getId_paquete() {
        return id_paquete;
    }

    public void setId_paquete(int id_paquete) {
        this.id_paquete = id_paquete;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getCantidad_personas() {
        return cantidad_personas;
    }

    public void setCantidad_personas(int cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
        
}
