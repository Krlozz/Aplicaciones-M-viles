package com.example.kevin.miprimerprograma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import modelo.Persona;

public class MainActivity extends AppCompatActivity {

    EditText escribir;
    ListView lista;
    ArrayAdapter<Persona> adapter; //<T> generico
    Persona [] datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMsj = (Button) findViewById(R.id.butonMensaje);
        //Button open2 = (Button) findViewById(R.id.abrir2);
        escribir = (EditText)findViewById(R.id.escribe);
        lista = (ListView) findViewById(R.id.listaNueva);
        cargarLista();
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,datos);
        lista.setAdapter(adapter);
        //evento a la lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),datos[position].toString(),Toast.LENGTH_LONG).show();

                abrirPop(view,datos[position]);





                /*Intent intent = new Intent(getApplicationContext(),Main2Activity.class); //getaplicacioncontens se obtinetodo el main
                //pasar parametros
                intent.putExtra("idTexto",escribir.getText().toString());
                intent.putExtra("idPersona",datos[position]);
                startActivity(intent);*/   // comentado para los menus
            }
        });

        btnMsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast msj = Toast.makeText(getApplicationContext(), escribir.getText().toString() , Toast.LENGTH_LONG);
                msj.setGravity(Gravity.CENTER_VERTICAL,0,0); //posiciones
                msj.show();
            }



        });

    }

    public void abrirPop(View view, Persona persona) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.ver:
                        Toast.makeText(getApplicationContext(), "Ver producto",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.comprar:
                        Toast.makeText(getApplicationContext(), "Comprar",Toast.LENGTH_LONG).show();
                        return true;
                    default: return false;
                }
                //return false;   // retorna esto cuando no haga nada
            }
        });

        popupMenu.inflate(R.menu.pop_menu_lista); // permite colocar cual menu desplegar
        popupMenu.show();
    }

    private  void cargarLista(){
        datos = new Persona[] {new Persona("Kevin","Jimenez","kevin.jimenez@epn.edu.ec","1723945380"),
                new Persona("Kevin2","Jimenez","kevin.jimenez@epn.edu.ec","1723945380"),
                new Persona("Kevin3","Jimenez","kevin.jimenez@epn.edu.ec","1723945380"),
                new Persona("Kevin4","Jimenez","kevin.jimenez@epn.edu.ec","1723945380"),
                new Persona("Kevin5","Jimenez","kevin.jimenez@epn.edu.ec","1723945380")};
    }

    public  void abrir2(View v){
                                                //donde estoy, a donde voy
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class); //getaplicacioncontens se obtinetodo el main
        //pasar parametros
        intent.putExtra("idTexto",escribir.getText().toString());
        intent.putExtra("idPersona",new Persona("Kevin","Jimenez","kevin.jimenez@epn.edu.ec","1723945380"));
        startActivity(intent);
    }
}
