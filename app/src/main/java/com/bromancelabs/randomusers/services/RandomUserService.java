package com.bromancelabs.randomusers.services;

import com.bromancelabs.randomusers.models.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {

    @GET("/")
    Call<Results> getRandomUsers(@Query("results") String results);
}
