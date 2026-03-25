package DAOs;

import Modelos.*;
import Utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaqueteDAO {
    
    ConexionDB conexion = new ConexionDB();
    
    public Paquete registrarPaquete(Paquete nuevo) {
        
        try (Connection conn = conexion.conectar()) {
            
            String sql = "INSERT INTO paquete (nombre_paquete, id_destino, duracion, descripcion, precio_publico, capacidad) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_paquete());
            stm.setInt(2, nuevo.getId_destino());
            stm.setInt(3, nuevo.getDuracion());
            stm.setString(4, nuevo.getDescripcion());
            stm.setDouble(5, nuevo.getPrecio_publico());
            stm.setInt(6, nuevo.getCapacidad());
            
            
            stm.executeUpdate();
            
            return nuevo;
            
        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR PAQUETE DESDE DAO" + e.getMessage());
        }
        
        return null;
    }
    
    public ArrayList<Paquete> obtenerPaquetes() {
        
        ArrayList<Paquete> paquetes = new ArrayList<>();
        
        try (Connection conn = conexion.conectar()) {
            
            String sql = "SELECT * FROM paquete";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                
                Paquete nuevo = new Paquete(rs.getInt("id_paquete"), rs.getString("nombre_paquete"),
                        rs.getInt("id_destino"), rs.getInt("duracion"), rs.getString("descripcion"),
                        rs.getDouble("precio_publico"), rs.getInt("capacidad"));
                
                nuevo.setEstado(rs.getBoolean("estado"));
                
                paquetes.add(nuevo);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER PAQUETES DESDE DAO" + e.getMessage());
        }
        
        return paquetes;
    }
    
    public Paquete editarPaquete(Paquete nuevo) {
        
        try (Connection conn = conexion.conectar()) {
            
            String sql = "UPDATE paquete SET nombre_paquete = ?, id_destino=?, duracion=?, descripcion=?, "
                    + " precio_publico =?, capacidad =? WHERE id_paquete = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nuevo.getNombre_paquete());
            stm.setInt(2, nuevo.getId_destino());
            stm.setInt(3, nuevo.getDuracion());
            stm.setString(4, nuevo.getDescripcion());
            stm.setDouble(5, nuevo.getPrecio_publico());
            stm.setInt(6, nuevo.getCapacidad());
            stm.setInt(7, nuevo.getId_paquete());
            
            stm.executeUpdate();
            
            return nuevo;
            
        } catch (SQLException e) {
            System.out.println("ERROR AL EDITAR PAQUETE DESDE DAO " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean desactivarActivarPaquete(Paquete aDesactivar) {
        
        try (Connection conn = conexion.conectar()) {
            
            String sql = "UPDATE paquete SET estado = NOT estado WHERE id_paquete = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, aDesactivar.getId_paquete());
            
            stm.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("ERROR AL DESACTIVAR/ACTIVAR DESDE DAO " + e.getMessage());
        }
        
        return false;
    }
    
    public DetallePaquete obtenerDetallePaquetes(DetallePaquete buscado) {
        
        try (Connection conn = conexion.conectar()) {
            
            String sql = "SELECT "
                    + "    p.nombre_paquete AS paquete, "
                    + "    s.descripcion AS servicio, "
                    + "    ps.costo AS costo_servicio, "
                    + "    pr.nombre_proveedor AS proveedor "
                    + "FROM paquete p "
                    + "JOIN paquete_servicio ps "
                    + "    ON p.id_paquete = ps.id_paquete "
                    + "JOIN servicio s "
                    + "    ON ps.id_servicio = s.id_servicio "
                    + "JOIN proveedor pr "
                    + "    ON s.id_proveedor = pr.id_proveedor "
                    + "WHERE p.id_paquete = ?";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, buscado.getId_paquete());
            
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                
                DetallePaquete nuevo = new DetallePaquete(rs.getString("paquete"), rs.getString("servicio"),
                        rs.getDouble("costo_servicio"), rs.getString("proveedor"));
                
                return nuevo;
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER DETALLE DE PAQUETE DESDE DAO" + e.getMessage());
        }
        
        return null;
    }
    
}
