package modelo;

import java.io.Serializable;

public class Persona implements Serializable{


    private String nombre;
    private String apellido;
    private String correo;
    private String cedula;

    public Persona(String nombre,String apellido,String correo, String cedula) {
        this.nombre=nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
