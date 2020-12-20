package com.example.jobfinder.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jobfinder.Model.Job;
import com.example.jobfinder.R;
import com.example.jobfinder.Utilities.JobAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class SelectedJobListFragment extends Fragment {
    HashSet<Job> jobslist;
    ArrayList<Job> selectedJobs;

    public SelectedJobListFragment() {
        // Required empty public constructor
    }
    public SelectedJobListFragment(HashSet<Job> jobslist)
    {
        this.jobslist=new HashSet<>(jobslist);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedJobs=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Iterator<Job> iter = jobslist.iterator();
        while(iter.hasNext()){
            selectedJobs.add(iter.next());
        }
        View view=inflater.inflate(R.layout.fragment_selected_job_list, container, false);
        ListView listView = view.findViewById(R.id.selected_list_view);
        JobAdapter jobAdapter = new JobAdapter(getContext(),selectedJobs);
        listView.setAdapter(jobAdapter);
        return view;
    }
}