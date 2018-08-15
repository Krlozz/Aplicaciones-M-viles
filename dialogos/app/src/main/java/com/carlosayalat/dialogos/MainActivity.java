package com.carlosayalat.dialogos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void mostrar (View view) {
        // dialogAlert();
        // dialogoLista();
        // dialogSingleChoose();
        // dialogMultiChoose();
        dialogPers();
    }

    public void dialogAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);                       // permite construir el dialogo
        alertDialog.setTitle("Mensaje - Título");
        alertDialog.setMessage("cuerpo del mensaje");

        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Si",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"Cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setCancelable(true);       // va aparecer el diálogo y si aplasta sale del cuadro, si le pongo el false debo escoger algo del diálogo.

        alertDialog.create();
        alertDialog.show();

    }

    public void dialogoLista() {
        final CharSequence[] items = {"Articulo1","Articulo2","Articulo3","Articulo4"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);                       // permite construir el dialogo
        alertDialog.setTitle("Mensaje - Título");

        alertDialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"Cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setCancelable(true);       // va aparecer el diálogo y si aplasta sale del cuadro, si le pongo el false debo escoger algo del diálogo.

        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {        // int i -> posición de la lista
                Toast.makeText(getApplicationContext(), items[i],Toast.LENGTH_LONG).show();

            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    public void dialogSingleChoose() {   // escoger más de una opción

        final CharSequence[] items = {"Articulo1","Articulo2","Articulo3","Articulo4"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);                       // permite construir el dialogo
        alertDialog.setTitle("Mensaje - Título");

        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Si",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"Cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setCancelable(true);       // va aparecer el diálogo y si aplasta sale del cuadro, si le pongo el false debo escoger algo del diálogo.

        alertDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.create();
        alertDialog.show();

    }

    public void dialogMultiChoose() {
        final CharSequence[] items = {"Articulo1","Articulo2","Articulo3","Articulo4"};

        final ArrayList seleccionados = new ArrayList();


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);                       // permite construir el dialogo
        alertDialog.setTitle("Mensaje - Título");

        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                String a = "";
                for (int j = 0; j<seleccionados.size();j++) {
                    // a = a + y va desde items...
                    Log.e("Valor: ",items[Integer.parseInt(seleccionados.get(j).toString())].toString());
                }
                // toast
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getApplicationContext(),"Cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setCancelable(true);

        alertDialog.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean b) {   // int i -> posicion, b -> true or false
                if (b) {
                    seleccionados.add(i);

                } else {
                    seleccionados.remove(i);
                }
            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    public void dialogPers() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_personalizado, null));

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {     // int i -> son los valores que va a tomar el botón
                Toast.makeText(getApplicationContext(),"Si",Toast.LENGTH_LONG).show();
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
}
