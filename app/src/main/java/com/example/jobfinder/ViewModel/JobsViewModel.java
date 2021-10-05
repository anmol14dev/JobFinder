package com.example.jobfinder.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.Repository.ApiCall;
import com.example.jobfinder.Model.Job;

import java.util.List;


public class JobsViewModel extends ViewModel {
    private MutableLiveData<List<Job>> jobs;

    public LiveData<List<Job>> getFlights(){
        if(jobs==null){
            jobs= new MutableLiveData<>();
            fetchFlights();
        }
        return jobs;
    }

    private void fetchFlights() {
        jobs=ApiCall.fetchFlights();
    }

}
