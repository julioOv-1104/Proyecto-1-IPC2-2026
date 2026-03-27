
package Servicios;

import java.io.BufferedReader;
import java.io.*;

public class CargarArchivoService {
    
    InterpretadorComandos interprete = new InterpretadorComandos();
    
    public void procesarArchivo(InputStream archivo){
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                procesarLinea(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void procesarLinea(String linea) {

        if (linea.startsWith("USUARIO")) {
            interprete.procesarUsuario(linea);
        }
        else if (linea.startsWith("DESTINO")) {
            interprete.procesarDestino(linea);
        }
        else if (linea.startsWith("PROVEEDOR")) {
            interprete.procesarProveedor(linea);
        }
        else if (linea.startsWith("PAQUETE")) {
            interprete.procesarPaquete(linea);
        }
        else if (linea.startsWith("SERVICIO_PAQUETE")) {
            //interprete.procesarServicioPaquete(linea);
        }
        else if (linea.startsWith("CLIENTE")) {
            interprete.procesarCliente(linea);
        }
        else if (linea.startsWith("RESERVACION")) {
            //ParserComandos.procesarReservacion(linea);
        }
        else if (linea.startsWith("PAGO")) {
            interprete.procesarPago(linea);
        }
    }
    
}
