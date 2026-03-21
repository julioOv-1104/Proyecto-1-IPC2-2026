package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteDAO {

    ConexionDB conexion = new ConexionDB();

    public Cliente registrarCliente( Cliente nuevo) {      
        
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

            System.out.println("ERROR AL REGISTRAR CLIENTE DESDE DAO");
            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR CLIENTE DESDE DAO" + e.getMessage());
        }

        return null;
    }
    
     public Cliente editarCliente( Cliente nuevo) {      
        
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

}
