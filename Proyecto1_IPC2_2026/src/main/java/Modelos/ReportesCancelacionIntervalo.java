package Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class ReportesCancelacionIntervalo {

    private String numero_reserva;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_cancelacion;
    private double monto_reembolsado;
    private double perdida;

    public ReportesCancelacionIntervalo() {
    }

    public ReportesCancelacionIntervalo(String numero_reserva, Date fecha_cancelacion, double monto_reembolsado, double perdida) {
        this.numero_reserva = numero_reserva;
        this.fecha_cancelacion = fecha_cancelacion;
        this.monto_reembolsado = monto_reembolsado;
        this.perdida = perdida;
    }

    public String getNumero_reserva() {
        return numero_reserva;
    }

    public void setNumero_reserva(String numero_reserva) {
        this.numero_reserva = numero_reserva;
    }

    public Date getFecha_cancelacion() {
        return fecha_cancelacion;
    }

    public void setFecha_cancelacion(Date fecha_cancelacion) {
        this.fecha_cancelacion = fecha_cancelacion;
    }

    public double getMonto_reembolsado() {
        return monto_reembolsado;
    }

    public void setMonto_reembolsado(double monto_reembolsado) {
        this.monto_reembolsado = monto_reembolsado;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

}
