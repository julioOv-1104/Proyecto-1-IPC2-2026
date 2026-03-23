package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistorialAtencionClienteDAO {

    ConexionDB conexion = new ConexionDB();

    public ArrayList<HistorialReservaciones> obtenerTodasReservasCliente(String dpi) {
        //Consulta para obtener el reservasHoy de reservaciones de un cliente

        ArrayList<HistorialReservaciones> historial = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    c.nombre_cliente AS cliente, "
                    + "    r.numero_reserva, "
                    + "    p.nombre_paquete AS paquete, "
                    + "    r.fecha_viaje, "
                    + "    r.estado, "
                    + "    r.costo_total "
                    + "FROM Cliente c "
                    + "JOIN Reserva_Cliente rc ON c.dpi = rc.dpi "
                    + "JOIN Reserva r ON rc.numero_reserva = r.numero_reserva "
                    + "JOIN Paquete p ON r.id_paquete = p.id_paquete "
                    + "WHERE c.dpi = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, dpi);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                HistorialReservaciones nuevo = new HistorialReservaciones(rs.getString("cliente"),
                        rs.getString("numero_reserva"), rs.getString("paquete"), rs.getDate("fecha_viaje"),
                        rs.getString("estado"), rs.getDouble("costo_total"));

                historial.add(nuevo);

            }

            return historial;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER HISTORIAL DE RESERVAS DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public ArrayList<HistorialPagosReserva> obtenerPagosDeReserva(String numero_reserva) {
        //Consulta para obtener el reservasHoy de pagos de una reservación

        ArrayList<HistorialPagosReserva> historial = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    r.numero_reserva, "
                    + "    p.monto, "
                    + "    p.fecha, "
                    + "    p.estado, "
                    + "    CASE p.metodo_pago "
                    + "        WHEN 1 THEN 'Efectivo' "
                    + "        WHEN 2 THEN 'Tarjeta' "
                    + "        WHEN 3 THEN 'Transferencia' "
                    + "    END AS metodo "
                    + "FROM Pago p "
                    + "JOIN Reserva r ON p.numero_reserva = r.numero_reserva "
                    + "WHERE r.numero_reserva = ? "
                    + "ORDER BY p.fecha";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, numero_reserva);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                HistorialPagosReserva nuevo = new HistorialPagosReserva(numero_reserva, rs.getDouble("monto"),
                        rs.getDate("fecha"), rs.getString("estado"), rs.getString("metodo"));

                historial.add(nuevo);

            }

            return historial;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER HISTORIAL DE PAGOS DE UNA RESERVACION DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public ArrayList<ReservacionesFechaDestino> obtenerReservacionesDisponibles(Date fecha, String destino) {
        //Consulta para obtener reservaciones disponibles para una fecha y destino específicos

        ArrayList<ReservacionesFechaDestino> historial = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    p.nombre_paquete, "
                    + "    p.capacidad, "
                    + "    COALESCE(SUM(r.cantidad_personas), 0) AS reservados, "
                    + "    (p.capacidad - COALESCE(SUM(r.cantidad_personas), 0)) AS disponibles "
                    + "FROM Paquete p "
                    + "JOIN Destino d ON p.id_destino = d.id_destino "
                    + "LEFT JOIN Reserva r  "
                    + "    ON p.id_paquete = r.id_paquete "
                    + "    AND r.fecha_viaje = ? "
                    + "    AND r.estado != 'Cancelada' "
                    + "WHERE d.nombre_destino = ? "
                    + "GROUP BY p.id_paquete "
                    + "HAVING disponibles > 0";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha);
            stm.setString(2, destino);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ReservacionesFechaDestino nuevo = new ReservacionesFechaDestino(rs.getString("nombre_paquete"),
                        rs.getInt("capacidad"), rs.getInt("reservados"), rs.getInt("disponibles"));

                historial.add(nuevo);

            }

            return historial;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER RESERVACIONES DISPONIBLES DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Reserva> obtenerReservacionesDelDia() {
        //Consulta para obtener reservaciones del dia

        ArrayList<Reserva> reservasHoy = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    numero_reserva, "
                    + "    fecha_creacion, "
                    + "    estado, "
                    + "    costo_total "
                    + "FROM Reserva "
                    + "WHERE DATE(fecha_creacion) = CURDATE()";

            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Reserva nueva = new Reserva();
                nueva.setNumero_reserva(rs.getString("numero_reserva"));
                nueva.setFecha_creacion(rs.getDate("fecha_creacion"));
                nueva.setEstado(rs.getString("estado"));
                nueva.setCosto_total(rs.getDouble("costo_total"));

                reservasHoy.add(nueva);

            }

            return reservasHoy;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER RESERVACIONES DE HOY DESDE DAO " + e.getMessage());
        }

        return null;
    }

}
