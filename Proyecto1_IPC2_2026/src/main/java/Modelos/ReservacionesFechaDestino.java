
package Modelos;

public class ReservacionesFechaDestino {
    
    private String nombre_paquete;
    private int capacidad, reservados, disponibles;

    public ReservacionesFechaDestino() {
    }

    public ReservacionesFechaDestino(String nombre_paquete, int capacidad, int reservados, int disponibles) {
        this.nombre_paquete = nombre_paquete;
        this.capacidad = capacidad;
        this.reservados = reservados;
        this.disponibles = disponibles;
    }

    public String getNombre_paquete() {
        return nombre_paquete;
    }

    public void setNombre_paquete(String nombre_paquete) {
        this.nombre_paquete = nombre_paquete;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getReservados() {
        return reservados;
    }

    public void setReservados(int reservados) {
        this.reservados = reservados;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }
    
    
    
}
