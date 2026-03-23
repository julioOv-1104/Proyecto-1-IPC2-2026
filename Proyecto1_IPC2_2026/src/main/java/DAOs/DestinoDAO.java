package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DestinoDAO {

    ConexionDB conexion = new ConexionDB();

    public Destino registrarDestino(Destino nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "INSERT INTO destino (nombre_destino, pais, descripcion, mejor_epoca) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_destino());
            stm.setString(2, nuevo.getPais());
            stm.setString(3, nuevo.getPais());
            stm.setString(4, nuevo.getMejor_epoca());

            stm.executeUpdate();

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR DESTINO DESDE DAO" + e.getMessage());
        }

        return null;
    }
    
    
    public ArrayList<Destino> ObtenerDestinos() {

        ArrayList<Destino> destinos = new ArrayList<>();
        
        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT * FROM destino";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                
                Destino nuevo = new Destino(rs.getInt("id_destino"), rs.getString("nombre_destino"),
                rs.getString("pais"), rs.getString("descripcion"), rs.getString("mejor_epoca"));
                
                destinos.add(nuevo);
            }

           

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER DESTINOS DESDE DAO" + e.getMessage());
        }

         return destinos;
    }
    

    public Destino editarDestino(Destino nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = "UPDATE destino SET nombre_destino = ?, pais=?, descripcion=?, mejor_epoca=? "
                    + " WHERE id_destino = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_destino());
            stm.setString(2, nuevo.getPais());
            stm.setString(3, nuevo.getDescripcion());
            stm.setString(4, nuevo.getMejor_epoca());
            stm.setInt(5, nuevo.getId_destino());

            stm.executeUpdate();

            return nuevo;

        } catch (SQLException e) {
            System.out.println("ERROR AL EDITAR DESTINO DESDE DAO " + e.getMessage());
        }

        return null;
    }

    public boolean eliminarDestino(Destino nuevo) {

        try (Connection conn = conexion.conectar()) {

            String sql = " DELETE FROM destino WHERE id_destino = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, nuevo.getId_destino());

            stm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("ERROR AL ELIMINAR DESTINO DESDE DAO " + e.getMessage());
        }

        return false;
    }

}
