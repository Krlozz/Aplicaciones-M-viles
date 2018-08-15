package modelo;

import java.io.Serializable;

public class Persona implements Serializable{

    private String nombre, cedula, apellido;

    public Persona(String nombre, String cedula, String apellido) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return this.nombre + ' ' + this.apellido +
                " " + this.cedula;
    }
}
