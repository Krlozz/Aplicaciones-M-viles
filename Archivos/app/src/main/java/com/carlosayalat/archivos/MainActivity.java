package com.carlosayalat.archivos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import control.leerEscribirArchivos;
import modelo.Persona;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    leerEscribirArchivos leerEscribirArchivos = new leerEscribirArchivos();

    public void crearArchivo (View view) {
        //escribirArchivoPlano();
        leerEscribirArchivos.escribirArchivo(new Persona("Carlos"," 23235346","Ayala"),"persona.txt");
    }

    public void escribirArchivoPlano () {
        OutputStreamWriter escribir = null;
        try {
            escribir = new OutputStreamWriter(openFileOutput("fichero.txt", Context.MODE_PRIVATE));
            escribir.write("texto prueba");
        } catch (FileNotFoundException e) {
            Log.e( "error archivo: ",e.toString());
        } catch (IOException e) {
            Log.e( "error escritura: ",e.toString());
        } finally {
            try {
                if(escribir!=null) {
                    escribir.close();
                }
            } catch (IOException e) {
                Log.e( "error cerrar: ",e.toString());
            }
        }
    }

    public void leerArchivo (View view) {
        //leerArchivo();
        Persona p = leerEscribirArchivos.leerArchivo("persona.txt");
        Toast.makeText(getApplicationContext(),p.toString(),Toast.LENGTH_LONG).show();
    }

    private void leerArchivo() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(openFileInput("fichero.txt"));
            br = new BufferedReader(isr);
            String texto = br.readLine();
            while (texto!=null){
                Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
                texto = br.readLine();
            }

        } catch (FileNotFoundException e) {
            Log.e( "error not found: ",e.toString());
        } catch (IOException e) {
            Log.e( "error IO: ",e.toString());
        }
    }
}
