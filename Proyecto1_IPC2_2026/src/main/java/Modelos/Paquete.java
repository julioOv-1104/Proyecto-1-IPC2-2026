package Modelos;

public class Paquete {

    private int id_paquete;
    private String nombre_paquete;
    private int id_destino;
    private int duracion;
    private String descripcion = "";
    private double precio_publico;
    private int capacidad;
    private boolean estado = true;

    public Paquete() {
    }

    public Paquete(String nombre_paquete, int id_destino, int duracion, double precio_publico, int capacidad) {
        this.nombre_paquete = nombre_paquete;
        this.id_destino = id_destino;
        this.duracion = duracion;
        this.precio_publico = precio_publico;
        this.capacidad = capacidad;
    }

    
    
    public Paquete(int id_paquete, String nombre_paquete, int id_destino, int duracion, String descripcion, double precio_publico, int capacidad) {
        this.id_paquete = id_paquete;
        this.nombre_paquete = nombre_paquete;
        this.id_destino = id_destino;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.precio_publico = precio_publico;
        this.capacidad = capacidad;
    }

    public int getId_paquete() {
        return id_paquete;
    }

    public void setId_paquete(int id_paquete) {
        this.id_paquete = id_paquete;
    }

    public String getNombre_paquete() {
        return nombre_paquete;
    }

    public void setNombre_paquete(String nombre_paquete) {
        this.nombre_paquete = nombre_paquete;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_publico() {
        return precio_publico;
    }

    public void setPrecio_publico(double precio_publico) {
        this.precio_publico = precio_publico;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
