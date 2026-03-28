package Servicios;

import DAOs.*;
import Modelos.*;
import java.text.SimpleDateFormat;

public class InterpretadorComandos {

    public void procesarUsuario(String linea) {
        
        System.out.println("ENTRA AQUI");

        String datos = linea.substring(
                linea.indexOf("(") + 1,
                linea.lastIndexOf(")")
        );

        String[] partes = datos.split(",");

        String nombre = partes[0].replace("\"", "").trim();
        String password = partes[1].replace("\"", "").trim();
        int rol = Integer.parseInt(partes[2].trim());
        Usuario nuevo = new Usuario(nombre, password, rol);

        UsuarioDAO dao = new UsuarioDAO();
        dao.registrarUsuario(nuevo);//se manda a registrar el usuario extraido del archivo

    }

    public void procesarDestino(String linea) {

        String datos = linea.substring(
                linea.indexOf("(") + 1,
                linea.lastIndexOf(")")
        );

        String[] partes = datos.split(",");

        String nombre = partes[0].replace("\"", "").trim();
        String pais = partes[1].replace("\"", "").trim();
        String descripcion = partes[2].replace("\"", "").trim();
        Destino nuevo = new Destino(nombre, pais, descripcion);

        DestinoDAO dao = new DestinoDAO();
        dao.registrarDestino(nuevo);

    }

    public void procesarProveedor(String linea) {

        String datos = linea.substring(
                linea.indexOf("(") + 1,
                linea.lastIndexOf(")")
        );

        String[] partes = datos.split(",");

        String nombre = partes[0].replace("\"", "").trim();
        int tipo = Integer.parseInt(partes[1].trim());
        String pais = partes[2].replace("\"", "").trim();
        Proveedor nuevo = new Proveedor(nombre, tipo, pais);

        ProveedorDAO dao = new ProveedorDAO();
        dao.registrarProveedor(nuevo);

    }

    public void procesarPaquete(String linea) {

        String datos = linea.substring(
                linea.indexOf("(") + 1,
                linea.lastIndexOf(")")
        );

        String[] partes = datos.split(",");

        String nombre = partes[0].replace("\"", "").trim();
        int destino = Integer.parseInt(partes[1].trim());
        int duracion = Integer.parseInt(partes[2].trim());
        double precio = Double.parseDouble(partes[1].trim());
        int capacidad = Integer.parseInt(partes[4].trim());
        Paquete nuevo = new Paquete(nombre, destino, duracion, precio, capacidad);

        PaqueteDAO dao = new PaqueteDAO();
        dao.registrarPaquete(nuevo);

    }

    public void procesarCliente(String linea) {

        String datos = linea.substring(
                linea.indexOf("(") + 1,
                linea.lastIndexOf(")")
        );

        String[] partes = datos.split(",");

        String dpi = partes[0].replace("\"", "").trim();
        String nombre = partes[1].replace("\"", "").trim();
        String fechaStr = partes[2].replace("\"", "").trim();
        String telefono = partes[3].replace("\"", "").trim();
        String eMail = partes[4].replace("\"", "").trim();
        String nacionalidad = partes[5].replace("\"", "").trim();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");//formato que recive

            java.util.Date fechaUtil = sdf.parse(fechaStr);
 
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            
            Cliente nuevo = new Cliente(dpi, nombre, fechaSQL, telefono, eMail, nacionalidad);
            ClienteDAO dao = new ClienteDAO();
            dao.registrarCliente(nuevo);
            
            
        } catch (Exception e) {
            System.out.println("ERROR AL CONVERTIR FORMATO DE FECHA CLIENTE");
        }


    }
    
    
    public void procesarPago(String linea) {

        String datos = linea.substring(
                linea.indexOf("(") + 1,
                linea.lastIndexOf(")")
        );

        String[] partes = datos.split(",");

        String reservacion = partes[0].replace("\"", "").trim();
        double monto = Double.parseDouble(partes[1].trim());
        int metodo = Integer.parseInt(partes[2].trim());
        String fechaStr = partes[3].replace("\"", "").trim();


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");//formato que recive

            java.util.Date fechaUtil = sdf.parse(fechaStr);
 
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            
            Pago nuevo = new Pago(reservacion, monto, metodo, fechaSQL);
            PagoDAO dao = new PagoDAO();
            dao.registrarPago(nuevo);
            
            
        } catch (Exception e) {
            System.out.println("ERROR AL CONVERTIR FORMATO DE FECHA PAGO");
        }


    }
   

}
