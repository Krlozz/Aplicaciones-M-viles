package com.carlosayalat.taller1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import controlador.ListaProductos;
import modelo.Producto;

public class ModificarProducto extends AppCompatActivity {

    private EditText descripcion;
    private EditText codigoQR;
    private EditText precio;

    private int posicion;
    ListaProductos modificar = new ListaProductos();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        Producto datos = (Producto) getIntent().getExtras().getSerializable("idModificar");
        posicion = getIntent().getExtras().getInt("posicion");


        descripcion = (EditText) findViewById(R.id.desModif);
        descripcion.setText(datos.getDescripcion());
        codigoQR = (EditText) findViewById(R.id.qrModif);
        codigoQR.setText(datos.getQr());
        precio = (EditText) findViewById(R.id.preModif);
        precio.setText(String.valueOf(datos.getPrecio()));
    }

    public void modificar (View view) {
        /*Intent intent = new Intent(getApplicationContext(), ProductosUsuario.class);
        modificar.modificar(posicion, new Producto(descripcion.getText().toString(),codigoQR.getText().toString(),Double.parseDouble(precio.getText().toString())),"ficheroProdUsuarios.txt");
        intent.putExtra("idMod", modificar);
        startActivity(intent);*/
        dialogModificar();

    }

    public void dialogModificar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_modificar, null));

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Artículo eliminado con éxito",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ProductosUsuario.class);
                modificar.modificar(posicion, new Producto(descripcion.getText().toString(),codigoQR.getText().toString(),Double.parseDouble(precio.getText().toString())),"ficheroProdUsuarios.txt");
                intent.putExtra("idMod", modificar);
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



}
