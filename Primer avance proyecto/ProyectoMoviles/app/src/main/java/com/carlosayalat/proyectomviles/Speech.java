package com.carlosayalat.proyectomviles;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class Speech extends AppCompatActivity {

    private TextToSpeech hablar;
    private EditText editarTexto;
    private SeekBar barTono;
    private SeekBar barRapidez;
    private Button btnHablar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        editarTexto = findViewById(R.id.txtTexto);
        barTono = findViewById(R.id.barTono);
        barRapidez = findViewById(R.id.barRapidez);
        btnHablar = findViewById(R.id.btnHablar);

        hablar = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // idioma
                    int result = hablar.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "No se puede hablar en ese lenguaje");
                    } else {
                        btnHablar.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "No se inicializó correctamente");
                }
            }
        });

        btnHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hablarBtn();
            }
        });

    }

    private void hablarBtn() {
        String text = editarTexto.getText().toString();
        float pitch = (float) barTono.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) barRapidez.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        hablar.setPitch(pitch);
        hablar.setSpeechRate(speed);

        hablar.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (hablar != null) {
            hablar.stop();
            hablar.shutdown();
        }
        super.onDestroy();
    }

}
