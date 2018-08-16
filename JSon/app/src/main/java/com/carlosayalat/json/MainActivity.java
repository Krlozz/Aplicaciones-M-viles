package com.carlosayalat.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //final ArrayList<AndroidVersion> data = new ArrayList<>();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
//                .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/query?")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                //ArrayList a =new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                //Log.e("Resultados: ", ((AndroidVersion)a.).getName() +"");
                Log.e("sdfsdfdsf",jsonResponse.getAndroid().length + "");
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error:::: ",t.getMessage());
            }
        });
       /* final String BASE_URL = "https://api.learn2crack.com" ;//http://rest-service.guides.spring.io";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GreetingService service = retrofit.create(GreetingService.class);

        Call<Greeting> call = service.getGreeting();

        call.enqueue(new Callback<Greeting>() {
            @Override
            public void onResponse(Call<Greeting> call, Response<Greeting> response) {
                Greeting greeting = response.body();

                Log.e("/////////// ", greeting+"");
            }

            @Override
            public void onFailure(Call<Greeting> call, Throwable t) {
                Log.e(" Error:::::: ", t.getMessage());
            }
        });*/
    }
}
