package Servicios;

import DAOs.PagoDAO;
import Modelos.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CancelacionReservaService {

    private PagoDAO pagoDao = new PagoDAO();

    private final String pendiente = "PENDIENTE";
    private final String confirmada = "CONFIRMADA";
    private final String cancelada = "CANCELADA";
    private final String completada = "COMPLETADA";

    public boolean cancelarReserva(Reserva reserva) {

        int reembolso = 0;

        if (reserva.getEstado().equals(pendiente) || reserva.getEstado().equals(confirmada)) {
            //si esta en alguno de esos dos estados se puede cancelar

            long restantes = (faltanSieteDiasOMas(reserva.getFecha_viaje()));//obtiene tiempo de diferencia

            reembolso = pagoDao.obtenerTotalPagadoReembolso(reserva);//obtiene cuanto ha pagado el cliente en total

            return calcularReembolso(reserva, restantes, reembolso);

        }
        return false;
    }

    public long faltanSieteDiasOMas(Date fecha) {

        // Convierte alocalDate 
        LocalDate fechaReserva = fecha.toLocalDate();

        //Obtiene la fecha de hoy
        LocalDate hoy = LocalDate.now();

        //Calcula la diferencia
        long diasDeDiferencia = ChronoUnit.DAYS.between(hoy, fechaReserva);

        System.out.println("DIAS: " + diasDeDiferencia);

        return diasDeDiferencia;
    }

    private boolean calcularReembolso(Reserva reserva, double restante, double pagado) {

        double reembolso = 0;

        if (restante > 30) {
            reembolso = pagado;//reembolso del 100%

        }
        if (restante <= 15 || restante >= 30) {
            reembolso = (pagado * 0.7);//70 %

        }
        if (restante <= 7 || restante >= 14) {
            reembolso = (pagado * 0.4);//40 %

        }

        if (pagoDao.cancelarReserva(reserva, reembolso) && pagoDao.registarCancelacion(reserva, reembolso)) {

            System.out.println("PAGADO: " + pagado);
            System.out.println("REEMBOLSO: " + reembolso);

            return true;

        }

        return false;

    }

}
