package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR PAGO DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public int obtenerTotalPagado(Reserva reserva) {

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT SUM(monto) AS total_pagado FROM pago WHERE numero_reserva = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, reserva.getNumero_reserva());

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                int reembolso = rs.getInt("total_pagado");
                System.out.println("REEMBOLSO EN DAO: "+reembolso);
                return reembolso;

            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER REEMBOLSO DESDE DAO " + e.getMessage());
        }
        
        return 0;

    }
    
     public boolean cancelarPago(Reserva reserva, double reembolso ) {

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
     
     public boolean registarCancelacion(Reserva reserva, double reembolso ) {

        try (Connection conn = conexion.conectar()) {

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

}
