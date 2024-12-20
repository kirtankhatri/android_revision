package com.example.revision;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    final private static String BASE_URL = "http://10.0.2.2/practice/";
    private static Retrofit retrofitClient;

    public static Retrofit getClient(){
        if(retrofitClient==null){
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofitClient;
        }
        return retrofitClient;
    }
}
