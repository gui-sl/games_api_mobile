package com.example.gamesapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiList {

    private static Retrofit retrofitInstance;

    public static Retrofit getInstance(){
        if(retrofitInstance==null){
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://api.rawg.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }
}
