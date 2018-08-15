package com.carlosayalat.taller1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import controlador.ListaUsuarios;
import modelo.Usuario;

public class Registro extends AppCompatActivity {

    private EditText user;
    private EditText correo;
    private EditText pass;
    private EditText pass2;
    ListaUsuarios registro = new ListaUsuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user=(EditText)findViewById(R.id.userRegistro);
        correo=(EditText)findViewById(R.id.correoRegistro);
        pass=(EditText)findViewById(R.id.passRegistro);
        pass2=(EditText)findViewById(R.id.repeatPass);
    }

    public void abrirLogueo(View view) {
        if(pass.getText().toString().equals(pass2.getText().toString())){
            registro.creacionArchivo(new Usuario(user.getText().toString(),correo.getText().toString(),pass.getText().toString()),"ficheroUsuarios.txt");
            Intent intent = new Intent(getApplicationContext(),Logueo.class);
            intent.putExtra("id", registro);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
        }
    }

}
