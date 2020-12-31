package com.example.jobfinder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobfinder.Client.JobClient;
import com.example.jobfinder.Fragments.JobsFragment;
import com.example.jobfinder.Fragments.MapFragment;
import com.example.jobfinder.Fragments.SelectedJobListFragment;
import com.example.jobfinder.Interface.JobApi;
import com.example.jobfinder.Model.Job;
import com.example.jobfinder.R;
import com.example.jobfinder.Utilities.LoadingDialogue;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText searchBar;
    private ImageView searchButton;
    private String keyword = "";
    private LoadingDialogue loadingDialog;
    public ArrayList<Job> jobs;
    private JobClient client;
    private JobApi jobService;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private JobsFragment jobsFragment;
    private MapFragment mapFragment;
    private SelectedJobListFragment selectedJobListFragment;
    private Fragment defaultFragment;
    private TextView title;
    public HashSet<Job> jobsList;
    public Location userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        loadingDialog = new LoadingDialogue(HostActivity.this);
        client = new JobClient();
        jobService = client.getService();
        toolbar = findViewById(R.id.toolbar);
        fragmentManager = getSupportFragmentManager();

        jobsFragment = new JobsFragment();
        setSupportActionBar(toolbar);
        init();
        searchJobs("");
//
    }

    public void init() {
        searchBar = findViewById(R.id.search_keyword);
        searchButton = findViewById(R.id.search_button);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        title = findViewById(R.id.title);
        setClickListners();
    }

    private void setClickListners() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchJobs(searchBar.getText().toString());
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.location:
                        defaultFragment = new MapFragment();
                        title.setText("Location");
                        break;
                    case R.id.jobs:
                        defaultFragment = jobsFragment;
                        title.setText("Jobs");
                        break;
                    case R.id.list:
                        defaultFragment = new SelectedJobListFragment(jobsList);
                        title.setText("List");
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.frame, defaultFragment, null).commit();
                return true;
            }
        });

    }
    public void searchJobs(String key){
        jobsList=new HashSet<>();
        jobs=new ArrayList<>();
        Call<List<Job>> call = jobService.getJobs(key,"usa");
        loadingDialog.SetDialog("Please Wait");
        loadingDialog.show();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if(response.isSuccessful()){
                    for(int i=0;i<response.body().size();i++){
                        jobs.add(response.body().get(i));
                    }
                    Log.v("Fetched",Integer.toString(jobs.size()));
                    setDatainJobFragment();
                    loadingDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
            }
        });
    }
    private void setDatainJobFragment() {
        jobsFragment=new JobsFragment(jobs);
        fragmentManager.beginTransaction().replace(R.id.frame,jobsFragment,null).commit();
    }
    public void updateJobList(int position){
        jobsList.add(jobs.get(position));


    }
}