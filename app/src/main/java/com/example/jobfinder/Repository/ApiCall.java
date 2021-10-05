package com.example.jobfinder.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.Model.FlightModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {
    private static JobsService flightService;
    private static MutableLiveData<List<Flight>> flights;
    public static MutableLiveData<List<Flight>> fetchFlights() {
        flightService= ApiClient.getClient().create(JobsService.class);
        flights= new MutableLiveData<>();
        Call<FlightModel> call = flightService.listFlights();
        call.enqueue(new Callback<FlightModel>() {
            @Override
            public void onResponse(Call<FlightModel> call, Response<FlightModel> response) {
                Log.v("Response", Integer.toString(response.code()) );
                if(response.code()==200)
                flights.postValue(response.body().getFlights());
            }
            @Override
            public void onFailure(Call<FlightModel> call, Throwable t) {
                Log.v("Failed","Failed");
            }
        });
        return flights;
    }
}
