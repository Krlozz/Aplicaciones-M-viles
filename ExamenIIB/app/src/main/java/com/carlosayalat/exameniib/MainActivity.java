package com.carlosayalat.exameniib;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private static String NAMESPACE = "http://ws.demostracion.cuscungo.com/";
    private static String URL = "http://192.168.22.55:8080/Demostracion/LoginServices?wsdl";

    private String respuesta;
    Usuario usuario;

    private TextView numMatri;
    private TextView pass;
    private Button login;

    private String userPass;
    private String userNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numMatri = (TextView) findViewById(R.id.numMatri);
        pass = (TextView) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);

    }

    public void login(View view) {
        userPass = pass.getText().toString();
        userNum = numMatri.getText().toString();

        if(userPass.isEmpty()){
            Toast.makeText(this,"EL CAMPO DE CONTRASEÑA ES OBLIGATORIO", Toast.LENGTH_SHORT).show();
        }
        else if(userNum.isEmpty()){
            Toast.makeText(this,"EL CAMPO DE NUMERO DE MATRICULA ES OBLIGATORIO",Toast.LENGTH_SHORT).show();
        }
        else {
            AsyncVerifica task = new AsyncVerifica();
            task.execute();
        }
    }

    private void consultarService() {
        String METHOD_NAME = "autenticarUsuario";
        String SOAP_ACTION = "autenticarUsuarioAction";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("numMatr", numMatri.getText().toString());
        request.addProperty("pass", pass.getText().toString());
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        try {
            transporte.call(SOAP_ACTION, soapEnvelope);
            SoapObject response = (SoapObject) soapEnvelope.getResponse();

            usuario = new Usuario();
            usuario.setNumMatr(numMatri.getText().toString());
            usuario.setPass(pass.getText().toString());
            usuario.setStatus(response.getPropertyAsString("status"));
            String resp = usuario.getStatus();

            if(resp.contains("OK")){
                Intent intent = new Intent(this,ListaMaterias.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"EL USUARIO NO EXISTE",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = "ERROR";
        }
    }

    private class AsyncVerifica extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            consultarService();
            return null;
        }

        protected void onPostExecute(Void result) {
            if(usuario.getNumMatr() == null){
                AlertDialog.Builder dialogo2 = new AlertDialog.Builder(MainActivity.this);
                dialogo2.setTitle("Resultado");
                dialogo2.setMessage("usuario");
                dialogo2.setNeutralButton("OK", null);
                dialogo2.show();
            }
            else if(usuario.getPass() == null){
                AlertDialog.Builder dialogo3 = new AlertDialog.Builder(MainActivity.this);
                dialogo3.setTitle("Resultado");
                dialogo3.setMessage("password");
                dialogo3.setNeutralButton("OK", null);
                dialogo3.show();
            }
            else{
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Resultado");
                dialogo.setMessage(usuario.toString());
                dialogo.setNeutralButton("OK", null);
                dialogo.show();

            }

        }
    }


}
