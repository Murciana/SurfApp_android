package com.example.surfclient.retrofit;

import com.example.surfclient.model.Spot;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SpotApi {

    @GET("/spot/get-all")
    Call<List<Spot>> getAllSpots();

    @POST("/spot/save")
    Call<Spot> save(@Body Spot spot);

}
