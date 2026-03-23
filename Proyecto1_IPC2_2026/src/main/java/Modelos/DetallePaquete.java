package Modelos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetallePaquete {

    private int id_paquete;
    private String paquete;
    private String servicio;
    private double costo;
    private String proveedor;

    public DetallePaquete(String paquete, String servicio, double costo, String proveedor) {
        this.paquete = paquete;
        this.servicio = servicio;
        this.costo = costo;
        this.proveedor = proveedor;
    }

    public DetallePaquete() {
    }
    
    

    public int getId_paquete() {
        return id_paquete;
    }

    public void setId_paquete(int id_paquete) {
        this.id_paquete = id_paquete;
    }
    
    

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
}
