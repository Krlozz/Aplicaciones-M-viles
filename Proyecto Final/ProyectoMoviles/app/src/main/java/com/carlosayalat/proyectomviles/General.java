package com.carlosayalat.proyectomviles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.InputStream;

public class General extends AppCompatActivity {

    TextView usuario, correo;
    String url;
    Button logout;
    String idLogout;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        usuario = ((TextView) findViewById(R.id.txtName));
        usuario.setText(getIntent().getExtras().getString("idUsuario"));

        correo = ((TextView) findViewById(R.id.txtCorreo));
        correo.setText(getIntent().getExtras().getString("idCorreo"));
        url = getIntent().getExtras().getString("idFoto");
        idLogout = getIntent().getExtras().getString("id");

        new General.imagenPerfil((ImageView)findViewById(R.id.imgFoto)).execute(url);

        logout = (Button) findViewById(R.id.btnLogout);
    }

    public void salir(View view) {
        if (idLogout.equals("1")) {
            dialogLogoutGmail();
        } if (idLogout.equals("2")) {
            dialogLogoutFB();
        }
    }

    public void testSpeech(View view) {
        Intent intent = new Intent(getApplicationContext(),Speech.class);
        startActivity(intent);
    }

    public void testOPENCV(View view) {
        Intent intent = new Intent(getApplicationContext(),testOpenCV.class);
        startActivity(intent);
    }

    public static class imagenPerfil extends AsyncTask<String, Void, Bitmap> {
        ImageView urlImagen;

        public imagenPerfil(ImageView urlImagen){
            this.urlImagen = urlImagen;
        }

        protected Bitmap doInBackground(String... urls){
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            }catch (Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result){
            urlImagen.setImageBitmap(result);
        }

    }

    public void dialogLogoutGmail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_cerrar_gmail, null));

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            Toast.makeText(getApplicationContext(),"por fin...!!!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            finish();
                        }
                    });

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

    public void dialogLogoutFB() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_cerrar_fb, null));

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Si...",Toast.LENGTH_LONG).show();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

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

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }
}
