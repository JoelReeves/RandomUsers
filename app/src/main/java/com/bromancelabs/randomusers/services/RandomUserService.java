package com.bromancelabs.randomusers.services;

import com.bromancelabs.randomusers.models.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomUserService {

    @GET("/?results=100")
    Call<Results> getRandomUsers();
}
