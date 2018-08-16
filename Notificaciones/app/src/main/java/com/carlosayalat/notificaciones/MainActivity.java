package com.carlosayalat.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificacion();

    }

    public void notificacion() {
        NotificationCompat.Builder mBuldier = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.alert_light_frame)
                .setContentTitle("Titulo de alerta")
                .setContentText("Cuerpo de alerta")
                .setTicker("Alerta!");

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
        mBuldier.setContentIntent(pendingIntent);   // espera hasta que no haga algo
        NotificationManager notificationmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationmanager.notify(1,mBuldier.build());


    }

}
