package com.example.jobfinder.Client;

import android.util.Log;
import android.widget.Toast;

import com.example.jobfinder.Interface.JobApi;
import com.example.jobfinder.Model.Job;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class JobClient {
    static final String BASE_URL = "https://jobs.github.com/";
    public JobApi getService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        JobApi service = retrofit.create(JobApi.class);
        return service;
    }
}
