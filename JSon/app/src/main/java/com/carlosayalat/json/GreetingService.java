package com.carlosayalat.json;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GreetingService {
    @GET("android/jsonandroid/")
    Call<Greeting> getGreeting();
}
