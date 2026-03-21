
package Modelos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente {
 
    private String dpi, nombre_cliente, telefono, email, nacionalidad;
    private Date fecha_nacimiento;

    public Cliente(String dpi, String nombre_cliente,  Date fecha_nacimiento, String telefono, String email, String nacionalidad) {
        this.dpi = dpi;
        this.nombre_cliente = nombre_cliente;
        this.telefono = telefono;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Cliente() {
    }
    
    

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    
    
    
}
