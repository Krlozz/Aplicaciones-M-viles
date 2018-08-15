package com.firstapp.paola.miprimerprograma;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import modelo.Persona;

public class MainActivity extends AppCompatActivity {

    EditText escribiraqui;
    EditText escribirnumeros;

    //lista -> lista adaptador datos
    ListView lista;
    ArrayAdapter<String> adapter;
    String [] datos;

    ArrayAdapter<Persona> adapter2;
    Persona [] datos2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonMensaje = (Button)findViewById(R.id.buttonMensaje);  //devuelve un objeto generico
        //Button abrirPantalla2 = (Button)findViewById(R.id.buttonPantalla2);



        escribiraqui = (EditText)findViewById(R.id.escribirAqui);
        escribirnumeros = (EditText)findViewById(R.id.escribirNumeros);


        buttonMensaje.setOnClickListener(new View.OnClickListener() {  // clase de tipo abstracto
            @Override
            public void onClick(View view) {
                //mensaje TOAST -> tiene una cola de mensajes
                    //contexto en mi aplicación
                //Toast mensaje = Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_LONG);
                //Toast mensaje = Toast.makeText(getApplicationContext(), escribirnumeros.getText().toString(),Toast.LENGTH_LONG);
                Toast mensaje = Toast.makeText(getApplicationContext(), escribirnumeros.getText().toString(),Toast.LENGTH_LONG);
                mensaje.setGravity(Gravity.TOP,0,0);

                mensaje.show();
            }
        });

        /*abrirPantalla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class); // si pongo this aquí hago referencia al OnClickListener, si le pongo fuera si puedo utilizar a athis
                intent.putExtra("idTexto", escribirnumeros.getText().toString()); // el único que permite pasar parámetros
                startActivity(intent);

                // en el manifest podemos ahi poner un modelo de navegación, manifest desde ahí configuró toda la funcionalidad
            }
        });*/

        lista = (ListView) findViewById(R.id.listP);
        //cargarLista();
        adapter2 = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,datos2);
        lista.setAdapter(adapter2);
        cargarLista2();
        //eventos a la lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),datos[i],Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),datos2[i].toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class); // si pongo this aquí hago referencia al OnClickListener, si le pongo fuera si puedo utilizar a athis
                intent.putExtra("idTexto", escribirnumeros.getText().toString()); // el único que permite pasar parámetros
                intent.putExtra("idpersona", datos2[i]);
                startActivity(intent);
            }
        });

    }
    public void abrirPantalla2(View view) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class); // si pongo this aquí hago referencia al OnClickListener, si le pongo fuera si puedo utilizar a athis
        intent.putExtra("idTexto", escribirnumeros.getText().toString()); // el único que permite pasar parámetros
        //intent.putExtra("idpersona", new Persona(datos2.toString(),datos2.toString(),datos2.toString(),datos2.toString()));
        startActivity(intent);
    }


    private void cargarLista() {
        datos = new String[] {"hello","dato2","dato3","dato4","dato5","dato6","dato7","dato8","dato9","dato10","dato11","dato12","dato13","dato14"};
    }

    private void cargarLista2() {
        datos2 = new Persona[] {new Persona("Carlos","Ayala","sdfds","234234"),
                new Persona("Carlos1","Ayala1","sdfds","234234"),
                new Persona("Carlos2","Ayala2","sdfds","234234"),
                new Persona("Carlos3","Ayala3","sdfds","234234"),
                new Persona("Carlos4","Ayala4","sdfds","234234"),};
    }


}
