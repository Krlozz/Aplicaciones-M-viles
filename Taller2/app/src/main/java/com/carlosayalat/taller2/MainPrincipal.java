package com.carlosayalat.taller2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainPrincipal extends AppCompatActivity {

    TextView user;
    TextView correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        user = ((TextView) findViewById(R.id.user));
        user.setText(getIntent().getExtras().getString("idName"));

        correo = ((TextView) findViewById(R.id.pass));
        correo.setText(getIntent().getExtras().getString("idCorreo"));
    }



}
