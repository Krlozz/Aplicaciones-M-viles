package com.carlosayalat.taller1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import controlador.ListaProductos;
import modelo.Producto;

public class ProductosUsuario extends AppCompatActivity {

    ListView listaProductos;
    ArrayAdapter<Producto> adapter;
    //Producto [] productos;
    ArrayList<Producto> productos;

    String usuario,contraseña;
    ListaProductos controlador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_usuario);

        usuario = getIntent().getExtras().getString("idUsuario");
        contraseña = getIntent().getExtras().getString("idContraseña");

        controlador = new ListaProductos();
        //controlador.escribirArchivo(new Producto().cargarProductos(),"ficheroProdUsuarios.txt");
        productos = controlador.leerArchivo("ficheroProdUsuarios.txt");

        listaProductos = (ListView) findViewById(R.id.ProductosUsuarios);
        //cargarProductos();
        adapter = new ArrayAdapter<>(getApplication(),R.layout.support_simple_spinner_dropdown_item,productos);
        listaProductos.setAdapter(adapter);

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getApplicationContext(),Detalle.class);
                intent.putExtra("id",productos[position]);
                intent.putExtra("idUsuario", usuario);
                intent.putExtra("idContraseña", contraseña);
                startActivity(intent);*/
                abrirDetUser(view,productos.get(position),position);
            }
        });


        // LogOut
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);

        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLogOut:
                FirebaseAuth
        }
        return true;
    }*/

    public void abrirDetUser(final View view, final Producto producto, final int posicion) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.verUser:
                        Intent intent = new Intent(getApplicationContext(),Detalle.class);
                        intent.putExtra("id",producto);
                        intent.putExtra("idUsuario", usuario);
                        intent.putExtra("idContraseña", contraseña);
                        startActivity(intent);
                        return true;
                    case R.id.compUser:
                        //Toast.makeText(getApplicationContext(), "Artículo comprado con éxito",Toast.LENGTH_LONG).show();
                        dialogCompr(producto);
                        return true;
                    case R.id.modUser:
                        //Toast.makeText(getApplicationContext(),"Modificar....!!!",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(getApplicationContext(),ModificarProducto.class);
                        intent2.putExtra("idModificar", producto);
                        intent2.putExtra("posicion", posicion);
                        startActivity(intent2);
                        return true;
                    case R.id.elimProd:
                        /*controlador.eliminar(posicion,"ficheroProdUsuarios.txt");
                        adapter.remove(producto);*/
                        dialogEliminar(producto,posicion);


                    default: return false;
                }
                //return false;   // retorna esto cuando no haga nada
            }
        });

        popupMenu.inflate(R.menu.menu_usuario); // permite colocar cual menu desplegar
        popupMenu.show();
    }

    /*private void cargarProductos() {
        productos = new Producto().cargarProductos();
    }*/

    public void buscarQR(View view){
        Intent intent = new Intent(getApplicationContext(),EscanerQR.class);
        intent.putExtra("idUsuario", usuario);
        intent.putExtra("idContraseña", contraseña);
        startActivity(intent);
    }

    public void crearProducto (View view) {
        Intent intent = new Intent(getApplicationContext(), CrearProducto.class);
        startActivity(intent);
    }

    public void dialogCompr(final Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_comprar, null));

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Artículo comprado con éxito",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Detalle.class);
                intent.putExtra("id",producto);
                intent.putExtra("idUsuario", usuario);
                intent.putExtra("idContraseña", contraseña);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"Cancelar",Toast.LENGTH_LONG).show();
            }
        });

        builder.setCancelable(true);

        builder.create();
        builder.show();
    }

    public void dialogEliminar(final Producto producto, final int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_eliminar, null));

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Artículo eliminado con éxito",Toast.LENGTH_LONG).show();
                controlador.eliminar(posicion,"ficheroProdUsuarios.txt");
                adapter.remove(producto);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"Cancelar",Toast.LENGTH_LONG).show();
            }
        });

        builder.setCancelable(true);

        builder.create();
        builder.show();
    }


}
