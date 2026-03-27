
package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class Pago {
    
    private  String numero_reserva;
    private double monto;
    private int metodo_pago;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecha;
    private String estado = "PARCIAL";

    public Pago() {
    }

     public Pago(String numero_reserva, double monto, int metodo_pago, Date fecha) {
        this.numero_reserva = numero_reserva;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
    }
    
    public Pago(String numero_reserva, double monto, int metodo_pago, Date fecha, String estado) {
        this.numero_reserva = numero_reserva;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
        this.estado = estado;
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

    public int getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(int metodo_pago) {
        this.metodo_pago = metodo_pago;
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
    
    
    
}
