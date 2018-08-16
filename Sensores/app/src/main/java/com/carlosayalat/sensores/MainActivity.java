package com.carlosayalat.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor acelerometro;
    TextView txt;
    TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);
        //txt2 = findViewById(R.id.txt2);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        txt.setText("");
        // administrador siempre tiene todos los sensores
        //acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        /*
        //lista de sensores en mi dispositivo
        List<Sensor> sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i= 0; i<sensores.size(); i++) {
            Sensor s = sensores.get(i);
            txt2.append(s.getName()+" "+s.getVendor()+"\n");
        }
        */
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x,y,z;
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        txt.setText("x= "+x+", y= "+y+", z= "+z);

    }

    // para que al salir de la aplicación deje de escuchar y tomar datos
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }
}
