
package Modelos;

public class Servicio {
    
    int id_servicio;
    int id_proveedor;
    String descripcion;

    public Servicio() {
    }

    public Servicio(int id_servicio, int id_proveedor, String descripcion) {
        this.id_servicio = id_servicio;
        this.id_proveedor = id_proveedor;
        this.descripcion = descripcion;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
