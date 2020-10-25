package py.una.pol.personas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehiculo implements Serializable {

    String tipo;
    Integer id;

    public Vehiculo(){

    }

    public Vehiculo(Integer id, String tipo) {
        this.id = id;
        this .tipo= tipo;
    }


    public String getTipo() {
        return tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}