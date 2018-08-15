package com.carlosayalat.taller1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import modelo.Producto;

public class Detalle extends AppCompatActivity {

    TextView usuario;
    String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);


        usuario = ((TextView) findViewById(R.id.detUsuario));
        usuario.setText(getIntent().getExtras().getString("idUsuario"));

        contraseña = getIntent().getExtras().getString("idContraseña");

        Producto producto = (Producto) getIntent().getExtras().getSerializable("id");
        ((TextView) findViewById(R.id.detDescripcion)).setText(producto.getDescripcion());
        ((TextView) findViewById(R.id.detPrecio)).setText(String.valueOf(producto.getPrecio()));
    }

    /*public void btnComprar(View view) {
        if (usuario.getText().toString().equals("Carlos") && contraseña.equals("12345")) {
            Toast.makeText(getApplicationContext(), "Artículo comprado con éxito", Toast.LENGTH_LONG).show();
        } //else if (usuario.getText().toString().equals("Invitado")){
            //Toast.makeText(getApplicationContext(), "Usuario no loggueado", Toast.LENGTH_LONG).show();
        //}
        else {
            Toast.makeText(getApplicationContext(), "Usuario no loggueado", Toast.LENGTH_LONG).show();
        }
    }*/
}
