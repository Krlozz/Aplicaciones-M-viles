package com.carlosayalat.taller2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import modelo.Persona;

public class MainRegitro extends AppCompatActivity {

    TextView user;
    TextView correo;
    TextView pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regitro);
    }

    public void abrirMain(View view) {
        user = ((TextView) findViewById(R.id.user));
        user.getText().toString();
        correo = ((TextView) findViewById(R.id.user));
        correo.getText().toString();
        pass = ((TextView) findViewById(R.id.user));
        pass.getText().toString();


        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }



}
