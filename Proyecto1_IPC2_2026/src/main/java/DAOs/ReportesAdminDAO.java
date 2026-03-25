package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportesAdminDAO {

    ConexionDB conexion = new ConexionDB();

    public ArrayList<ReportesVentasIntervalo> obtenerVentasEnIntervalo(Date fecha1, Date fecha2) {

        ArrayList<ReportesVentasIntervalo> ventas = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    p.nombre_paquete AS paquete, "
                    + "    c.nombre_cliente AS pasajero, "
                    + "    u.nombre_usuario AS agente, "
                    + "    r.costo_total, "
                    + "    r.fecha_creacion "
                    + "FROM Reserva r "
                    + "JOIN Paquete p ON r.id_paquete = p.id_paquete "
                    + "JOIN Usuario u ON r.id_usuario = u.id_usuario "
                    + "JOIN Reserva_Cliente rc ON r.numero_reserva = rc.numero_reserva "
                    + "JOIN Cliente c ON rc.dpi = c.dpi "
                    + "WHERE r.estado = 'Confirmada' "
                    + "AND r.fecha_creacion BETWEEN ? AND ? "
                    + "ORDER BY r.numero_reserva";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReportesVentasIntervalo nuevo = new ReportesVentasIntervalo(rs.getString("paquete"),
                        rs.getString("pasajero"), rs.getString("agente"), rs.getDouble("costo_total"),
                        rs.getDate("fecha_creacion"));

                ventas.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER VENTAS POR INTERVALO DE TIEMPO DESDE DAO" + e.getMessage());
        }

        return ventas;
    }

    public ArrayList<ReportesCancelacionIntervalo> obtenerCancelacionesEnIntervalo(Date fecha1, Date fecha2) {

        ArrayList<ReportesCancelacionIntervalo> cancelaciones = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    c.numero_reserva, "
                    + "    c.fecha_cancelacion, "
                    + "    c.monto_reembolsado, "
                    + "    (r.costo_total - c.monto_reembolsado) AS perdida "
                    + "FROM Cancelacion c "
                    + "JOIN Reserva r ON c.numero_reserva = r.numero_reserva "
                    + "WHERE c.fecha_cancelacion "
                    + "BETWEEN ? AND ? ";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReportesCancelacionIntervalo nuevo = new ReportesCancelacionIntervalo(rs.getString("numero_reserva"),
                        rs.getDate("fecha_cancelacion"), rs.getDouble("monto_reembolsado"), rs.getDouble("perdida"));

                cancelaciones.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER CANCELACIONES POR INTERVALO DE TIEMPO DESDE DAO" + e.getMessage());
        }

        return cancelaciones;
    }

    public ArrayList<ReporteGananciasIntervalo> obtenerGananciasEnIntervalo(Date fecha1, Date fecha2) {

        ArrayList<ReporteGananciasIntervalo> ganancias = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    ganancias_brutas, "
                    + "    total_reembolsos, "
                    + "    (ganancias_brutas - total_reembolsos) AS ganancia_neta "
                    + "FROM ( "
                    + "    SELECT  "
                    + "        (SELECT COALESCE(SUM(costo_total),0) "
                    + "         FROM Reserva "
                    + "         WHERE estado='Confirmada' "
                    + "         AND fecha_creacion "
                    + "         BETWEEN '2026-03-01' AND '2026-03-31') AS ganancias_brutas, "
                    + ""
                    + "        (SELECT COALESCE(SUM(monto_reembolsado),0) "
                    + "         FROM Cancelacion "
                    + "         WHERE fecha_cancelacion  "
                    + "         BETWEEN ? AND ?) AS total_reembolsos) c";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReporteGananciasIntervalo nuevo = new ReporteGananciasIntervalo(rs.getDouble("ganancias_brutas"),
                        rs.getDouble("total_reembolsos"), rs.getDouble("ganancia_neta"));

                ganancias.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER GANANCIAS POR INTERVALO DE TIEMPO DESDE DAO" + e.getMessage());
        }

        return ganancias;
    }

    public ArrayList<ReporteAgenteMasVentas> obtenerAgenteVentas(Date fecha1, Date fecha2) {

        ArrayList<ReporteAgenteMasVentas> ventasAgente = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    r.id_usuario, "
                    + "    u.nombre_usuario, "
                    + "    COUNT(*) AS total_ventas, "
                    + "    SUM(r.costo_total) AS monto_total "
                    + "FROM Reserva r "
                    + "JOIN Usuario u ON r.id_usuario = u.id_usuario "
                    + "WHERE r.estado = 'Confirmada' "
                    + "AND r.fecha_creacion  "
                    + "BETWEEN ? AND ? "
                    + "GROUP BY r.id_usuario "
                    + "ORDER BY monto_total DESC "
                    + "LIMIT 1";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReporteAgenteMasVentas nuevo = new ReporteAgenteMasVentas(rs.getInt("id_usuario"), rs.getString("nombre_usuario"),
                        rs.getInt("total_ventas"), rs.getDouble("monto_total"));

                ventasAgente.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER AGENTE CON MAS VENTAS DESDE DAO" + e.getMessage());
        }

        return ventasAgente;
    }

    public ArrayList<ReporteAgenteMasVentas> obtenerAgenteGanancias(Date fecha1, Date fecha2) {

        ArrayList<ReporteAgenteMasVentas> agenteGanancia = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    u.id_usuario, "
                    + "    u.nombre_usuario AS nombre_usuario, "
                    + "    SUM(r.costo_total) AS total_ganancia "
                    + "FROM Reserva r "
                    + "JOIN Usuario u ON r.id_usuario = u.id_usuario "
                    + "WHERE r.estado = 'Confirmada' "
                    + "AND r.fecha_creacion "
                    + "BETWEEN ? AND ? "
                    + "GROUP BY u.id_usuario, u.nombre_usuario "
                    + "ORDER BY total_ganancia DESC "
                    + "LIMIT 1";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReporteAgenteMasVentas nuevo = new ReporteAgenteMasVentas();
                nuevo.setId_usuario(rs.getInt("id_usuario"));
                nuevo.setNombre_usuario(rs.getString("nombre_usuario"));
                nuevo.setTotal_ganancia(rs.getDouble("total_ganancia"));

                agenteGanancia.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER AGENTE CON MAS GANANCIAS DESDE DAO" + e.getMessage());
        }

        return agenteGanancia;
    }

    public ArrayList<ReportePaqueteVendido> obtenerPaqueteMasMenosVendido(Date fecha1, Date fecha2, int bandera) {

        String masVendido = "DESC";
        String menosVendido = "ASC";
        String parametro = "";

        switch (bandera) {
            case 1:
                parametro = masVendido;
                break;

            case 2:
                parametro = menosVendido;
                break;
        }

        ArrayList<ReportePaqueteVendido> paquete = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    p.nombre_paquete AS paquete, "
                    + "    r.numero_reserva, "
                    + "    r.fecha_creacion, "
                    + "    r.costo_total "
                    + "FROM Reserva r "
                    + "JOIN Paquete p ON r.id_paquete = p.id_paquete "
                    + "JOIN Usuario u ON r.id_usuario = u.id_usuario "
                    + "WHERE r.id_paquete = ( "
                    + "    SELECT r.id_paquete "
                    + "    FROM Reserva r "
                    + "    WHERE r.estado = 'Confirmada' "
                    + "    AND r.fecha_creacion "
                    + "    BETWEEN ? AND ? "
                    + "    GROUP BY r.id_paquete "
                    + "    ORDER BY COUNT(*)" + parametro + ""
                    + "    LIMIT 1 "
                    + ") "
                    + "AND r.estado = 'Confirmada'"
                    + "AND r.fecha_creacion "
                    + "BETWEEN ? AND ?"
                    + "ORDER BY r.fecha_creacion";

            System.out.println("BANDERA USADA = " + bandera + " Parametro: " + parametro);

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);
            stm.setDate(3, fecha1);
            stm.setDate(4, fecha2);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReportePaqueteVendido nuevo = new ReportePaqueteVendido(rs.getString("paquete"),
                        rs.getString("numero_reserva"), rs.getDate("fecha_creacion"), rs.getDouble("costo_total"));

                paquete.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER PAQUETE MAS/MENOS VENDIDO DESDE DAO" + e.getMessage());
        }

        return paquete;
    }

    public ArrayList<ReporteOcupacionDestino> obtenerOcupacionDestino(Date fecha1, Date fecha2) {

        ArrayList<ReporteOcupacionDestino> destino = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    d.nombre_destino, "
                    + "    COUNT(*) AS cantidad_reservaciones "
                    + "FROM Reserva r "
                    + "JOIN Paquete p "
                    + "    ON r.id_paquete = p.id_paquete "
                    + "JOIN Destino d "
                    + "    ON p.id_destino = d.id_destino "
                    + "WHERE r.estado = 'Confirmada' "
                    + "AND r.fecha_creacion "
                    + "BETWEEN ? AND ? "
                    + "GROUP BY d.nombre_destino "
                    + "ORDER BY cantidad_reservaciones DESC";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, fecha1);
            stm.setDate(2, fecha2);

           

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                ReporteOcupacionDestino nuevo = new ReporteOcupacionDestino(rs.getString("nombre_destino"),
                        rs.getInt("cantidad_reservaciones"));
                
                destino.add(nuevo);

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER OCUPACION DE DESTINO DESDE DAO " + e.getMessage());
        }

        return destino;
    }

}
