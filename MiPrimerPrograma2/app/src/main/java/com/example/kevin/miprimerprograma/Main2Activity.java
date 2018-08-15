package com.example.kevin.miprimerprograma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import modelo.Persona;

public class Main2Activity extends AppCompatActivity {

    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        texto = (TextView) findViewById(R.id.textoVista);
        texto.setText(getIntent().getExtras().getString("idTexto"));

        Persona person = (Persona) getIntent().getExtras().getSerializable("idPersona");
        ((TextView) findViewById(R.id.nombre)).setText(person.getNombre());
        ((TextView) findViewById(R.id.apellido)).setText(person.getApellido());
        ((TextView) findViewById(R.id.correo)).setText(person.getCorreo());
        ((TextView) findViewById(R.id.cedula)).setText(person.getCedula());
        //Toast miToast = Toast.makeText(getApplicationContext(),getIntent().getExtras().getString("idTexto"),Toast.LENGTH_LONG);
        //getIntent().getExtras().getString("idTexto");
        //miToast.show();

    }
}
