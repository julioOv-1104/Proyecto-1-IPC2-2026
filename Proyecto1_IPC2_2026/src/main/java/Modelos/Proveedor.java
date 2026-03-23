package Modelos;

public class Proveedor {

    private int id_proveedor; 
    private String nombre_proveedor; 
    private int id_tipo_servicio; 
    private String pais; 
    private String contacto; 

    public Proveedor() {
    }

    public Proveedor(int id_proveedor, String nombre_proveedor, int id_tipo_servicio, String pais, String contacto) {
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.id_tipo_servicio = id_tipo_servicio;
        this.pais = pais;
        this.contacto = contacto;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public int getId_tipo_servicio() {
        return id_tipo_servicio;
    }

    public void setId_tipo_servicio(int id_tipo_servicio) {
        this.id_tipo_servicio = id_tipo_servicio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }




}
