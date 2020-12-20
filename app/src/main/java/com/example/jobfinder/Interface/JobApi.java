package com.example.jobfinder.Interface;

import com.example.jobfinder.Model.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JobApi {
    @GET("positions.json")
    Call<List<Job>> getJobs(@Query("description") String keyword,@Query("location") String location);
}
