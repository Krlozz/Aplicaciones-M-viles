package com.carlosayalat.proyectomviles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class General extends AppCompatActivity {

    TextView usuario, correo;
    String url;
    ImageView foto;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        usuario = ((TextView) findViewById(R.id.txtName));
        usuario.setText(getIntent().getExtras().getString("idUsuario"));

        correo = ((TextView) findViewById(R.id.txtCorreo));
        correo.setText(getIntent().getExtras().getString("idCorreo"));
        url = getIntent().getExtras().getString("idFoto");

        foto = (ImageView) findViewById(R.id.imgFoto);
        bitmap = getBitmapFromURL("url");
        foto.setImageBitmap(bitmap);

    }

    public void testSpeech(View view) {
        Intent intent = new Intent(getApplicationContext(),Speech.class);
        startActivity(intent);
    }

    public void testOPENCV(View view) {
        Intent intent = new Intent(getApplicationContext(),testOpenCV.class);
        startActivity(intent);
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
