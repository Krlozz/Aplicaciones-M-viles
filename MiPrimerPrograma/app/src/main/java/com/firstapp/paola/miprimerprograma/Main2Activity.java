package com.firstapp.paola.miprimerprograma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import modelo.Persona;

public class Main2Activity extends AppCompatActivity {

    TextView pasoMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_main2);

        //Toast.makeText(getApplicationContext(),getIntent().getExtras().getString("idTexto"), Toast.LENGTH_LONG).show();

        pasoMensaje = (TextView)findViewById(R.id.textMensaje);
        pasoMensaje.setText(getIntent().getExtras().getString("idTexto"));
        Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("idTexto"), Toast.LENGTH_LONG).show();

        Persona persona = (Persona) getIntent().getExtras().get("idpersona");
        ((TextView) findViewById(R.id.nombre)).setText(persona.getNombre());
        ((TextView) findViewById(R.id.apellido)).setText(persona.getApellido());
        ((TextView) findViewById(R.id.cedula)).setText(persona.getCedula());
        ((TextView) findViewById(R.id.correo)).setText(persona.getCorreo());

    }
}
