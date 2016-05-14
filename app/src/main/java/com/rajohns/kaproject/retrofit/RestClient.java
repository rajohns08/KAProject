package com.rajohns.kaproject.retrofit;

import com.rajohns.kaproject.models.Badge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {

    @GET("badges")
    Call<List<Badge>> getAllBadges();

}
