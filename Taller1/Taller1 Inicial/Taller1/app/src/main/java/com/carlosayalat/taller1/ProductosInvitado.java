package com.carlosayalat.taller1;

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

import controlador.InicialesProductos;
import controlador.ListaProductos;
import modelo.Producto;

public class ProductosInvitado extends AppCompatActivity {

    String invitado;
    //Producto [] prod;

    ListView listaProdInv;
    ArrayAdapter<String> adapter2;
    ArrayList<Producto> productos;
    String [] prodInv;
    ListaProductos controlador2 = new ListaProductos();
    //ListaProductos controlador2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_invitado);

        invitado = getIntent().getExtras().getString("idUsuario2");

        /*controlador2 = new ListaProductos();
        controlador2.escribirArchivo(new Producto().cargarProductos(),"ficheroProdInvitado.txt");
        prod = (Producto[]) controlador2.leerArchivo("ficheroProdInvitado.txt");*/


        listaProdInv = (ListView) findViewById(R.id.ProductosInvitados);
        //cargarProdInv();
        //carProd();
        controlador2.escribirArchivo(new InicialesProductos().listaProductosIniciales(), "ficheroProdInvitado.txt");
        productos = controlador2.leerArchivo("ficheroProdInvitado.txt");

        adapter2 = new ArrayAdapter<>(getApplication(),R.layout.support_simple_spinner_dropdown_item,descripcion());
        listaProdInv.setAdapter(adapter2);

        listaProdInv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getApplicationContext(),Detalle.class);
                intent.putExtra("id",prod[position]);
                intent.putExtra("idUsuario", invitado);
                startActivity(intent);*/
                abrirDetalle(view,productos.get(position));
            }
        });
    }

    public void abrirDetalle(final View view, final Producto producto) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.ver:
                        Intent intent = new Intent(getApplicationContext(),Detalle.class);
                        intent.putExtra("id",producto);
                        intent.putExtra("idUsuario", invitado);
                        startActivity(intent);

                        return true;
                    case R.id.comprar:
                        Toast.makeText(getApplicationContext(), "Usuario no logueado",Toast.LENGTH_LONG).show();
                        return true;
                    default: return false;
                }
                //return false;   // retorna esto cuando no haga nada
            }
        });

        popupMenu.inflate(R.menu.menu_invitado); // permite colocar cual menu desplegar
        popupMenu.show();
    }

    public String[] descripcion() {
        //prodInv = new String[prod.length];
        prodInv = new String[productos.size()];
        for(int i =0; i<prodInv.length; i++){
            prodInv [i] = productos.get(i).getDescripcion();
        }
        return prodInv;
    }

    /*private void cargarProdInv() {
        prodInv = new Producto().descripcion();

        /*prodInv = new Producto[] {new Producto("camiseta",2.45),
                new Producto("camisa",50.26),
                new Producto("chompa",91.58),
                new Producto("zapatos",109.56),
                new Producto("zapatillas",7.09),
                new Producto("pantalon",8.79),
                new Producto("terno",245.28),
                new Producto("abrigo",54.41),
                new Producto("gorro",5.24)
        };
    }

    Producto [] prod;

    private void carProd() {
        prod = new Producto().cargarProductos();
    }*/
}
