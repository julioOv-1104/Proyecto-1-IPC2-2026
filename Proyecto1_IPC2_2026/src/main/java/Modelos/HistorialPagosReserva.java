
package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class HistorialPagosReserva {
    
    private String numero_reserva;
    private double monto;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;
    private String estado;
    private String metodo;

    public HistorialPagosReserva() {
    }

    public HistorialPagosReserva(String numero_reserva, double monto, Date fecha, String estado, String metodo) {
        this.numero_reserva = numero_reserva;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.metodo = metodo;
    }

    public String getNumero_reserva() {
        return numero_reserva;
    }

    public void setNumero_reserva(String numero_reserva) {
        this.numero_reserva = numero_reserva;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
        
    
}
