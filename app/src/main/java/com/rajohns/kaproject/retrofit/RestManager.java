package com.rajohns.kaproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestManager {

    private static final RestClient instance = new Retrofit.Builder()
        .baseUrl("http://www.khanacademy.org/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RestClient.class);

    private RestManager() {}

    public static RestClient getInstance() {
        return instance;
    }

}
