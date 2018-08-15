package com.carlosayalat.taller1;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;

import controlador.ListaProductos;
import modelo.Producto;
import modelo.Usuario;

public class CrearProducto extends AppCompatActivity {

    private EditText descripcion;
    private EditText codigoQR;
    private EditText precio;

    ListaProductos registroProduct = new ListaProductos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        descripcion = (EditText) findViewById(R.id.descripcion);
        codigoQR = (EditText) findViewById(R.id.codigoQr);
        precio = (EditText) findViewById(R.id.precio);

    }

    public void crearNuevo (View view) {
        registroProduct.crearArchivo(new Producto(
                descripcion.getText().toString(),
                codigoQR.getText().toString(),
                Double.parseDouble(precio.getText().toString())),"ficheroProdUsuarios.txt");
        Intent intent = new Intent(getApplicationContext(),ProductosUsuario.class);
        intent.putExtra("idNuevo", registroProduct);
        startActivity(intent);
    }

}
