package DAOs;

import Modelos.HistorialReservaciones;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistorialReservacionesDAO {

    ConexionDB conexion = new ConexionDB();

    public ArrayList<HistorialReservaciones> obtenerTodasReservas(String dpi) {

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

}
