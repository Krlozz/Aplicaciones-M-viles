package controlador;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Evento;

public class EventosIniciales implements Serializable {

    private ArrayList<Evento> eventos;

    public ArrayList listaEventIni() {
        eventos = new ArrayList<>();
        eventos.add(new Evento("Ciencia","EPN","001",2018,6,8,"13","15","00"));
        eventos.add(new Evento("Matematica","EPN","002",2018,6,9,"13","15","00"));
        eventos.add(new Evento("Astronomia","EPN","003",2018,6,11,"13","15","00"));
        eventos.add(new Evento("Cultura","EPN","004",2018,6,15,"13","15","00"));
        eventos.add(new Evento("Teatro","EPN","005",2018,6,16,"13","15","00"));
        eventos.add(new Evento("Musica","EPN","006",2018,6,18,"13","15","00"));
        eventos.add(new Evento("Exposicion","EPN","007",2018,6,19,"13","15","00"));
        eventos.add(new Evento("Casas abiertas","EPN","008",2018,6,20,"13","15","00"));
        eventos.add(new Evento("Seguridad","EPN","009",2018,6,24,"13","15","00"));
        eventos.add(new Evento("Analisis","EPN","009",2018,6,29,"13","15","00"));
        return eventos;
    }

    public void ponerEvent(){
        listaEventIni();
    }


}
