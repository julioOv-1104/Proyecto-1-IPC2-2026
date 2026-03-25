package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoDAO {

    ConexionDB conexion = new ConexionDB();

    public Pago registrarPago(Pago nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO pago (numero_reserva, monto, metodo_pago, fecha, estado) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNumero_reserva());
            stm.setDouble(2, nuevo.getMonto());
            stm.setInt(3, nuevo.getMetodo_pago());
            stm.setDate(4, nuevo.getFecha());
            stm.setString(5, nuevo.getEstado());

            stm.executeUpdate();

            /*
            FALTA AGREGAR QUE DEVUELVA EL ID DEL PAGO
             */
            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR PAGO DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public void obtenerPago(int id) {

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    id_pago,"
                    + "    numero_reserva, "
                    + "    monto, "
                    + "    fecha, "
                    + "    estado, "
                    + "    CASE metodo_pago "
                    + "        WHEN 1 THEN 'Efectivo' "
                    + "        WHEN 2 THEN 'Tarjeta' "
                    + "        WHEN 3 THEN 'Transferencia' "
                    + "    END AS metodo "
                    + "FROM Pago WHERE id_pago = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                int id_pago = rs.getInt("id_pago");
                System.out.println("id del pago: " + id_pago);

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER COMPROBANTE DE PAGO DESDE DAO " + e.getMessage());
        }

    }

    public int obtenerTotalPagadoReembolso(Reserva reserva) {

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT SUM(monto) AS total_pagado FROM pago WHERE numero_reserva = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, reserva.getNumero_reserva());

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                int reembolso = rs.getInt("total_pagado");
                System.out.println("REEMBOLSO EN DAO: " + reembolso);
                return reembolso;

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER REEMBOLSO DESDE DAO " + e.getMessage());
        }

        return 0;

    }

    public boolean cancelarReserva(Reserva reserva, double reembolso) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE reserva SET estado = 'CANCELADA' WHERE numero_reserva = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, reserva.getNumero_reserva());
            stm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CANCELAR RESERVA DESDE DAO " + e.getMessage());
        }

        return false;

    }
    
    
    public boolean confirmaarReserva(Pago pago) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE reserva SET estado = 'CONFIRMADA' WHERE numero_reserva = ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, pago.getNumero_reserva());
            stm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL CONFIRMAR RESERVA DESDE DAO " + e.getMessage());
        }

        return false;

    }

    public boolean registarCancelacion(Reserva reserva, double reembolso) {

        try (Connection conn = conexion.conectar()) {//se resgistra la cancelacion en la DB

            String sql = "INSERT INTO cancelacion (numero_reserva, monto_reembolsado) VALUES (?, ?)";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, reserva.getNumero_reserva());
            stm.setDouble(2, reembolso);
            stm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR CANCELACION RESERVA DESDE DAO " + e.getMessage());
        }

        return false;

    }

    public double obtenerCosto(Pago pago) {
        //Obtiene el costo de la reserva
        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT costo_total FROM reserva WHERE numero_reserva = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, pago.getNumero_reserva());

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                //devuelve cual es el costo
                return rs.getDouble("costo_total");

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER ESTADO DE RESERVA DESDE DAO " + e.getMessage());
        }

        return 0;

    }

    public double obtenerTotalPagado(Pago pago) {
        //Obtiene cuanto ha pagado en esa reserva
        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT SUM(monto) FROM pago WHERE numero_reserva = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, pago.getNumero_reserva());

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                //devuelve cuanto ha pagado
                return rs.getDouble("SUM(monto)");

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER ESTADO DE RESERVA DESDE DAO " + e.getMessage());
        }

        return 0;

    }

    public boolean obtenerEstadoReserva(String reserva) {
        //Verifica que la reserva no está confirmada ni cancelada
        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT 1 FROM reserva WHERE numero_reserva = ? AND estado = 'Pendiente' ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, reserva);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                //puede pagar
                return true;

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER ESTADO DE RESERVA DESDE DAO " + e.getMessage());
        }

        return false;

    }

}
