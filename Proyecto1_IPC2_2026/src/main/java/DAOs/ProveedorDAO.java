package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorDAO {

    ConexionDB conexion = new ConexionDB();

    public Proveedor registrarProveedor(Proveedor nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO proveedor (nombre_proveedor, id_tipo_servicio, pais, contacto) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_proveedor());
            stm.setInt(2, nuevo.getId_tipo_servicio());
            stm.setString(3, nuevo.getPais());
            stm.setString(4, nuevo.getContacto());
            System.out.println("SQL " + stm);
            stm.executeUpdate();

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR PROVEEDOR DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Proveedor> obtenerProveedores() {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "Select * from proveedor";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Proveedor nuevo = new Proveedor(rs.getInt("id_proveedor"), rs.getString("nombre_proveedor"),
                        rs.getInt("id_tipo_servicio"), rs.getString("pais"), rs.getString("contacto"));
                proveedores.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER PROVEEDORES DESDE DAO" + e.getMessage());
        }

        return proveedores;
    }

}
