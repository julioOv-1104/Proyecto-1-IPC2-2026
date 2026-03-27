
package Modelos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Destino {
 
    private int id_destino;
    private String nombre_destino, pais, descripcion,imagen;
    private String mejor_epoca = "";

    public Destino() {
    }

    public Destino(String nombre_destino, String pais, String descripcion) {
        this.nombre_destino = nombre_destino;
        this.pais = pais;
        this.descripcion = descripcion;
    }
    
    

    public Destino(int id_destino, String nombre_destino, String pais, String descripcion, String mejor_epoca) {
        this.id_destino = id_destino;
        this.nombre_destino = nombre_destino;
        this.pais = pais;
        this.descripcion = descripcion;
        this.mejor_epoca = mejor_epoca;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getNombre_destino() {
        return nombre_destino;
    }

    public void setNombre_destino(String nombre_destino) {
        this.nombre_destino = nombre_destino;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMejor_epoca() {
        return mejor_epoca;
    }

    public void setMejor_epoca(String mejor_epoca) {
        this.mejor_epoca = mejor_epoca;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
}
