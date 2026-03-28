package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    ConexionDB conexion = new ConexionDB();

    public Usuario registrarUsuario(Usuario nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO usuario (nombre_usuario, password, rol) VALUES (?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_usuario());
            stm.setString(2, nuevo.getPassword());
            stm.setInt(3, nuevo.getRol());

            stm.executeUpdate();

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR USUARIO DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public Usuario login(String nombre_usuario, String password) {

        String nombre = nombre_usuario.trim();//le quita los espacios sobrantes
        String contra = password.trim();

        Usuario user = null;

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND password = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, contra);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                user = new Usuario(rs.getInt("id_usuario"), nombre, contra, rs.getInt("rol"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<Usuario> obtenerUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT id_usuario, nombre_usuario, rol FROM usuario";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Usuario nuevo = new Usuario();
                nuevo.setId_usuario(rs.getInt("id_usuario"));
                nuevo.setNombre_usuario(rs.getString("nombre_usuario"));
                nuevo.setRol(rs.getInt("rol"));

                usuarios.add(nuevo);
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER USUARIOS DESDE DAO" + e.getMessage());
        }

        return usuarios;
    }

    public boolean obtenerClientePorDPI(String dpi) {

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT 1 FROM cliente WHERE dpi = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, dpi);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                return true;

            }

            return false;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER CLIENTE CON DPI DESDE DAO " + e.getMessage());
        }

        return false;
    }

    public boolean cancelarActivarUsuario(Usuario user) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE usuario SET activo = ?, rol = ? WHERE nombre_usuario = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, user.isActivo());
            stm.setInt(2, user.getRol());
            stm.setString(3, user.getNombre_usuario());

            stm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL EDITAR USUARIO DESDE DAO " + e.getMessage());
        }

        return false;
    }

}
