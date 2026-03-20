
package DAOs;

import Modelos.Usuario;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    ConexionDB conexion = new ConexionDB();
    
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
    
}
