package com.example.jobfinder.Repository;

import com.example.go_mmt.Model.FlightModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JobsService {

    @GET("getAllFlights")
    Call<FlightModel> listFlights();
}
