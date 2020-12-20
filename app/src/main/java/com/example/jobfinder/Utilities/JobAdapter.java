package com.example.jobfinder.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jobfinder.Model.Job;
import com.example.jobfinder.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends ArrayAdapter<Job> {
    public JobAdapter(@NonNull Context context, @NonNull ArrayList<Job> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Job job = getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.selected_job_view,parent,false);
        }
        TextView jobTitle = convertView.findViewById(R.id.jb_position);
        TextView jobCompany = convertView.findViewById(R.id.jb_company);
        TextView jobLocation = convertView.findViewById(R.id.jb_location);

        jobTitle.setText(job.getTitle());
        jobCompany.setText(job.getCompany());
        jobLocation.setText(job.getLocation());

        return convertView;

    }
}
