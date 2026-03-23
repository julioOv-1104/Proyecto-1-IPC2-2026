package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultasOperacionesDAO {

    ConexionDB conexion = new ConexionDB();

    public ArrayList<PaquetesDisponibles> obtenerPaquetesDisponibles(String destino) {
        //Consulta para obtener paquetes disponibles por destino

        ArrayList<PaquetesDisponibles> disponibles = new ArrayList<>();

        try (Connection conn = conexion.conectar()) {

            String sql = "SELECT "
                    + "    p.*, "
                    + "    d.nombre_destino AS destino "
                    + "FROM paquete p "
                    + "INNER JOIN destino d  "
                    + "    ON p.id_destino = d.id_destino "
                    + "WHERE d.nombre_destino = ? "
                    + "AND p.estado = 1";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, destino);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PaquetesDisponibles nuevo = new PaquetesDisponibles(rs.getString("nombre_paquete"), rs.getInt("duracion"),
                        rs.getString("descripcion"), rs.getDouble("precio_publico"), rs.getInt("capacidad"),
                        rs.getString("destino"));

                disponibles.add(nuevo);

            }

            return disponibles;

        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER PAQUETES POR DESTINO DESDE DAO " + e.getMessage());
        }

        return null;
    }

}
