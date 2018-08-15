package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USRGAM on 11/05/2018.
 */

public class Persona implements Serializable {

    private String user;
    private String correo;
    private String pass;


    public Persona(String user, String correo, String pass) {
        this.user = user;
        this.correo = correo;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    List<Persona> listaUser = new ArrayList<Persona>();

    /*public listaUser listaUsers() {
        Persona user = new Persona();

        user.getUser();
        user.getCorreo();
        user.getPass();
        listaUser.add(user);


    }*/

}
