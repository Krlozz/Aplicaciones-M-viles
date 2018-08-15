package com.carlosayalat.taller1;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

import controlador.ListaUsuarios;
import modelo.Usuario;

public class Logueo extends AppCompatActivity {

    private GoogleApiClient googleApiClient;
    private final int CODERC = 9001;
    EditText usuario;
    EditText contraseña;

    private File file = Environment.getExternalStorageDirectory();
    private String ruta = file.getAbsolutePath() + File.separator;
    private File aux;
    private ListaUsuarios registroUsuarios = new ListaUsuarios();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        SignInButton botongoogle = (SignInButton) findViewById(R.id.loginGoogle);
        botongoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logueoGmail();
            }
        });

        usuario = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contraseña);
    }

    public void logueoGmail() {
        if(googleApiClient!=null){
            //desconectando
            googleApiClient.disconnect();
        }
        //solicitar correo para iniciar sesión
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //igual el cliente con el logeo
        googleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
        //abrir ventana de google
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent,CODERC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CODERC) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()) {
                GoogleSignInAccount acc = result.getSignInAccount();

                String token = acc.getIdToken();

                Log.e("correo: " ,acc.getEmail());
                Log.e("correo: " ,acc.getDisplayName());
                Log.e("correo: " ,acc.getId());
                if(token!=null){
                    Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this,"INGRESO CORRECTO",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ProductosUsuario.class);
                intent.putExtra("idUsuario", acc.getDisplayName());
                intent.putExtra("idContraseña", acc.getEmail());
                //Intent intent = new Intent(getApplicationContext(), EscanerQR.class); // lanza a la cámara
                startActivity(intent);
            }
        }
    }

    public void abrirUsuario(View view) {

        if(usuario.getText().toString().isEmpty() || contraseña.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Existen campos vacíos...!!!", Toast.LENGTH_LONG).show();
        }

        if(registroUsuarios.leerArchivo("ficheroUsuarios.txt").containsKey(usuario.getText().toString())) {
            Usuario usuarioRegistrado = (Usuario) registroUsuarios.leerArchivo("ficheroUsuarios.txt").get(usuario.getText().toString());
            if(contraseña.getText().toString().equals(usuarioRegistrado.getPass())) {
                aux = new File(ruta + "ficheroUsuarios.txt");
                if (aux.exists()) {
                    Intent intents = new Intent(getApplicationContext(), ProductosUsuario.class);
                    //intents.putExtra("idUsuario", usuario.getText().toString());
                    intents.putExtra("idUsuario", usuarioRegistrado.getUser());
                    intents.putExtra("idContraseña", contraseña.getText().toString());
                    startActivity(intents);
                } else {
                    registroUsuarios = (ListaUsuarios) getIntent().getExtras().getSerializable("id");
                    Intent intents = new Intent(getApplicationContext(), ProductosUsuario.class);
                    intents.putExtra("idUsuario", usuario.getText().toString());
                    intents.putExtra("idContraseña", contraseña.getText().toString());
                    startActivity(intents);
                }
            } else {
                Toast.makeText(getApplicationContext(),"Contraseña incorrecta",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
        }

        /*if (usuario.getText().toString().equals("Carlos") && contraseña.getText().toString().equals("12345")) {
            Intent intent = new Intent(getApplicationContext(),ProductosUsuario.class);
            //intent.putExtra("idUsuario", "ProductosUsuario: " + usuario.getText().toString());
            intent.putExtra("idUsuario", usuario.getText().toString());
            intent.putExtra("idContraseña", contraseña.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "ProductosUsuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
        }*/
    }

    public void abrirInvitado(View view) {
        Intent intent = new Intent(getApplicationContext(),ProductosInvitado.class);
        intent.putExtra("idUsuario2", "Invitado");
        startActivity(intent);
    }

    public void abrirRegistro(View view) {
        Intent intent = new Intent(getApplicationContext(),Registro.class);
        startActivity(intent);
    }
}
