
package Modelos;

public class ReporteGananciasIntervalo {
    
    private double ganancias_brutas, total_reembolsos, ganancias_neta;

    public ReporteGananciasIntervalo() {
    }

    public ReporteGananciasIntervalo(double ganancias_brutas, double total_reembolsos, double ganancias_neta) {
        this.ganancias_brutas = ganancias_brutas;
        this.total_reembolsos = total_reembolsos;
        this.ganancias_neta = ganancias_neta;
    }

    public double getGanancias_brutas() {
        return ganancias_brutas;
    }

    public void setGanancias_brutas(double ganancias_brutas) {
        this.ganancias_brutas = ganancias_brutas;
    }

    public double getTotal_reembolsos() {
        return total_reembolsos;
    }

    public void setTotal_reembolsos(double total_reembolsos) {
        this.total_reembolsos = total_reembolsos;
    }

    public double getGanancias_neta() {
        return ganancias_neta;
    }

    public void setGanancias_neta(double ganancias_neta) {
        this.ganancias_neta = ganancias_neta;
    }
    
    
    
}
