package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicioDAO {

    ConexionDB conexion = new ConexionDB();
    
    public ArrayList<Servicio> obtenerServicios() {
        
        ArrayList<Servicio> servicios = new ArrayList<>();
        
        try (Connection conn = conexion.conectar()) {
            
            String sql = "SELECT * FROM servicio";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                
                Servicio nuevo = new Servicio(rs.getInt("id_servicio"), rs.getInt("id_proveedor"), 
                        rs.getString("descripcion"));
                
                servicios.add(nuevo);
                
            }
            
            
            
        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER SERVICIOS DESDE DAO" + e.getMessage());
        }
        
        return servicios;
    }
    
}
