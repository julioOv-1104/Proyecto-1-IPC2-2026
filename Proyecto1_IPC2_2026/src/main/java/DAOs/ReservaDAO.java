package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaDAO {

    ConexionDB conexion = new ConexionDB();

    public Reserva registrarReserva(Reserva nueva) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO reserva VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nueva.getNumero_reserva());
            stm.setDate(2, nueva.getFecha_creacion());
            stm.setDate(3, nueva.getFecha_viaje());
            stm.setInt(4, nueva.getId_paquete());
            stm.setInt(5, nueva.getId_usuario());
            stm.setInt(6, nueva.getCantidad_personas());
            stm.setDouble(7, nueva.getCosto_total());
            stm.setString(8, nueva.getEstado());

            stm.executeUpdate();

            return nueva;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR RESERVA DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public boolean vincularReservaCliente(String numero_reserva, String dpi) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO reserva_cliente VALUES (?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, numero_reserva);
            stm.setString(2, dpi);

            System.out.println("SQL: " +sql);
            
            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL VINCULAR CLIENTE CON RESERVA DESDE DAO " + e.getMessage());
        }
        return false;

    }

    public ArrayList<Reserva> obtenerTodasReservas() {

        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM reserva";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Reserva nueva = new Reserva(rs.getString("numero_reserva"), rs.getDate("fecha_creacion"),
                        rs.getDate("fecha_viaje"), rs.getInt("id_paquete"), rs.getInt("id_usuario"),
                        rs.getInt("cantidad_personas"), rs.getDouble("costo_total"), rs.getString("estado"));

                reservas.add(nueva);

            }

            return reservas;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER RESERVAS DESDE DAO " + e.getMessage());
        }

        return null;
    }
    
    
    
    
}
