package com.carlosayalat.exameniib;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ListaMaterias extends AppCompatActivity {

    private static String NAMESPACE = "http://ws.demostracion.cuscungo.com/";
    private static String URL = "http://192.168.22.55:8080/Demostracion/LoginServices?wsdl";

    private String respuesta;
    Materias materias;

    String datos[];
    ListView miLista;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_materias);

        miLista = (ListView) findViewById(R.id.listMaterias);
        cargarLista();
        adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,datos);
        miLista.setAdapter(adapter);

    }

    public void cargarLista() {
        AsyncVerifica task = new AsyncVerifica();
        task.execute();
    }

    private void consultarService() {
        String METHOD_NAME = "verMaterias";
        String SOAP_ACTION = "materiasAction";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        try {
            transporte.call(SOAP_ACTION, soapEnvelope);
            SoapObject response = (SoapObject) soapEnvelope.getResponse();
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
    }


}
