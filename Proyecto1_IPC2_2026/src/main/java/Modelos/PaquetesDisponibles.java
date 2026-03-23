
package Modelos;

public class PaquetesDisponibles {
   
    private String nombre_paquete;
    private int duracion;
    private String descripcion;
    private double precio_publico;
    private int capacidad;
    private String destino;

    public PaquetesDisponibles(String nombre_paquete, int duracion, String descripcion, double precio_publico, int capacidad, String destino) {
        this.nombre_paquete = nombre_paquete;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.precio_publico = precio_publico;
        this.capacidad = capacidad;
        this.destino = destino;
    }

    public PaquetesDisponibles() {
    }
    
    

    public String getNombre_paquete() {
        return nombre_paquete;
    }

    public void setNombre_paquete(String nombre_paquete) {
        this.nombre_paquete = nombre_paquete;
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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    

    
}
