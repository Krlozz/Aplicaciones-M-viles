package com.carlosayalat.tareasasincronashilos;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bejecutar;
    private ProgressBar progressBar;

    private Button ejecutarHilo;

    private Button bAsyncrono;
    private Button bCancelar;
    //private TareaAsync tareaAsync;

    private ProgressDialog progressDialog;
    private Button bAsyncDialog;
    private TareaAsyncDialog tareaAsyncDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bejecutar = (Button) findViewById(R.id.bejecutar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ejecutarHilo = (Button) findViewById(R.id.ejecutarHilo);

        bAsyncrono = (Button) findViewById(R.id.bAsyncrono);
        bCancelar = (Button) findViewById(R.id.bCancelar);

        bAsyncDialog = (Button) findViewById(R.id.bAsyncDialog);

        /* Ejecutar normal
        bejecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setMax(1000);
                progressBar.setProgress(0);
                for (int i = 1; i <=100; i++) {
                    ejecutarTarea();
                    progressBar.incrementProgressBy(10);

                }
                Toast.makeText(MainActivity.this,"Finalizó",Toast.LENGTH_LONG).show;
            }
        });
        */

        /* HILOS
        ejecutarHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(0);
                            }
                        });
                        for (int i = 1; i <=100; i++) {
                            ejecutarTarea();
                            progressBar.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.incrementProgressBy(1);
                                }
                            });
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Finalizó",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();
            }
        });
        */


        // ASYNCRONO
        /*bAsyncrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tareaAsync = new TareaAsync();
                tareaAsync.execute();
            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tareaAsync.cancel(true);
            }
        });*/

        // tarea Asyncrona con dialogos
        bAsyncDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("Conectando...");
                progressDialog.setCancelable(true);
                progressDialog.setMax(100);

                // LLamar a la tarea asyncrona
                tareaAsyncDialog = new TareaAsyncDialog();
                tareaAsyncDialog.execute();
            }
        });

    }


    public void ejecutarTarea() {
        try {
            Thread.sleep(175);       // 12.5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Tarea asyncrona
    /*
    public class TareaAsync extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {        // hacerlo en segundo plano
            for (int i = 1; i <=100; i++) {
                ejecutarTarea();
                publishProgress(i);               // actualiza el avance de la tarea
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {   // mientras que se ejecute se modifiquen los valores
            int progreso = values[0].intValue();   // para continuar desde donde se cancela se le pone en una variable global y se guarda el estado de la variable
            progressBar.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {   // antes de ejecutar
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Toast.makeText(MainActivity.this,"Finalizó",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this,"Cancelado",Toast.LENGTH_LONG).show();
        }
    }*/

    // Tarea Ayncrona con diálogos
    public class TareaAsyncDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {        // hacerlo en segundo plano
            for (int i = 1; i <=100; i++) {
                ejecutarTarea();
                publishProgress(i);               // actualiza el avance de la tarea
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {   // mientras que se ejecute se modifiquen los valores
            int progreso = values[0].intValue();   // para continuar desde donde se cancela se le pone en una variable global y se guarda el estado de la variable
            progressDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {   // antes de ejecutar
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    TareaAsyncDialog.this.cancel(true);
                }
            });

            progressDialog.setProgress(0);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Finalizó",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this,"Cancelado",Toast.LENGTH_LONG).show();
        }
    }

}
