
package Modelos;

public class ReporteOcupacionDestino {
 
    private String nombre_destino;
    private int cantidad_reservaciones;

    public ReporteOcupacionDestino() {
    }

    public ReporteOcupacionDestino(String nombre_destino, int cantidad_reservaciones) {
        this.nombre_destino = nombre_destino;
        this.cantidad_reservaciones = cantidad_reservaciones;
    }

    public String getNombre_destino() {
        return nombre_destino;
    }

    public void setNombre_destino(String nombre_destino) {
        this.nombre_destino = nombre_destino;
    }

    public int getCantidad_reservaciones() {
        return cantidad_reservaciones;
    }

    public void setCantidad_reservaciones(int cantidad_reservaciones) {
        this.cantidad_reservaciones = cantidad_reservaciones;
    }
    
    
    
}
