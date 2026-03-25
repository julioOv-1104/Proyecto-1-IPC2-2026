
package Modelos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReporteAgenteMasVentas {
    
    private int id_usuario;
    private String nombre_usuario;
    private int total_ventas;
    private double monto_total;
    private double total_ganancia;

    public ReporteAgenteMasVentas() {
    }

    public ReporteAgenteMasVentas(int id_usuario, String nombre_usuario, int total_ventas, double monto_total) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.total_ventas = total_ventas;
        this.monto_total = monto_total;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getTotal_ventas() {
        return total_ventas;
    }

    public void setTotal_ventas(int total_ventas) {
        this.total_ventas = total_ventas;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public double getTotal_ganancia() {
        return total_ganancia;
    }

    public void setTotal_ganancia(double total_ganancia) {
        this.total_ganancia = total_ganancia;
    }
    
    
}
