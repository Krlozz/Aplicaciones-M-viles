package com.example.krlozz.examenib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import controlador.EventosIniciales;
import controlador.ListaEventosDisponibles;
import modelo.Evento;

public class EventosDisponibles extends AppCompatActivity {

    ListView listaEventos;
    ArrayAdapter<String> adapter;
    Evento [] eventos;
    ArrayList<Evento> evento;

    ListaEventosDisponibles controlador = new ListaEventosDisponibles();

    String [] namEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_disponibles);

        listaEventos = (ListView) findViewById(R.id.idListEventIni);

        controlador.escribirArchivo(new EventosIniciales().listaEventIni(), "ficheroEventosDsponibles.txt");
        evento = controlador.leerArchivo("ficheroEventosDsponibles.txt");

        adapter = new ArrayAdapter<>(getApplication(),R.layout.support_simple_spinner_dropdown_item,descripcion());
        listaEventos.setAdapter(adapter);

        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getApplicationContext(),Detalle.class);
                intent.putExtra("id",prod[position]);
                intent.putExtra("idUsuario", invitado);
                startActivity(intent);*/
                //registrarEvento(view,evento.get(position));
            }
        });

    }

    public String[] descripcion() {
        namEvent = new String[evento.size()];
        for(int i =0; i<namEvent.length; i++){
            namEvent [i] = evento.get(i).getNombreEvento();
        }
        return namEvent;
    }


    /*public void registrarEvento(final View view, final Evento evento) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.registrar:
                        Intent intent = new Intent(getApplicationContext(),EventosRegistrados.class);
                        startActivity(intent);

                        return true;
                    default: return false;
                }
                //return false;   // retorna esto cuando no haga nada
            }
        });

        popupMenu.inflate(R.menu.menu_registrar); // permite colocar cual menu desplegar
        popupMenu.show();
    }*/




}
