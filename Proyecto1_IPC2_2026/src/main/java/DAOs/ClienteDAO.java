package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    ConexionDB conexion = new ConexionDB();

    public Cliente registrarCliente(Cliente nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO cliente (dpi, nombre_cliente, fecha_nacimiento, telefono, email, nacionalidad) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getDpi());
            stm.setString(2, nuevo.getNombre_cliente());
            stm.setDate(3, nuevo.getFecha_nacimiento());
            stm.setString(4, nuevo.getTelefono());
            stm.setString(5, nuevo.getEmail());
            stm.setString(6, nuevo.getNacionalidad());

            stm.executeUpdate();

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR CLIENTE DESDE DAO" + e.getMessage());
        }

        return null;
    }

    public Cliente editarCliente(Cliente nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE cliente SET nombre_cliente = ?, fecha_nacimiento=?, telefono=?, email=?, nacionalidad=? "
                    + " WHERE dpi = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_cliente());
            stm.setDate(2, nuevo.getFecha_nacimiento());
            stm.setString(3, nuevo.getTelefono());
            stm.setString(4, nuevo.getEmail());
            stm.setString(5, nuevo.getNacionalidad());
            stm.setString(6, nuevo.getDpi());

            stm.executeUpdate();

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL EDITAR CLIENTE DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public Cliente verCliente(String dpi) {

        Cliente nuevo = null;
        
        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM cliente WHERE dpi = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, dpi);

             ResultSet rs = stm.executeQuery();
             
             if (rs.next()) {
                nuevo = new Cliente(rs.getString("dpi"),
                        rs.getString("nombre_cliente"), rs.getDate("fecha_nacimiento"),
                        rs.getString("telefono"), rs.getString("email"), rs.getString("nacionalidad"));
            }

        } catch (Exception e) {
            System.out.println("ERROR AL VER CLIENTE CON DPI: " + dpi);
            e.getMessage();
        }
        
        return nuevo;

    }

    public ArrayList<Cliente> verTodosClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM cliente";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Cliente nuevo = new Cliente(rs.getString("dpi"),
                        rs.getString("nombre_cliente"), rs.getDate("fecha_nacimiento"),
                        rs.getString("telefono"), rs.getString("email"), rs.getString("nacionalidad"));
                
                clientes.add(nuevo);//guarda los clientes en la lista
            }
            
            

        } catch (Exception e) {
            System.out.println("ERROR AL VER INFORMACION DE CLIENTES");
            e.getMessage();
        }
        
        return clientes;

    }

}
