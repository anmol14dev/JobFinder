package com.example.jobfinder.Utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobfinder.Model.Job;
import com.example.jobfinder.R;

import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private ArrayList<Job> items;

    public CardStackAdapter(@NonNull ArrayList<Job> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.job_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(items.get(position));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle;
        TextView jobCompany;
        TextView jobLocation;
        TextView jobDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.jb_position);
            jobCompany = itemView.findViewById(R.id.jb_company);
            jobLocation = itemView.findViewById(R.id.jb_location);
            jobDescription=itemView.findViewById(R.id.jb_description);
        }

        public void setData(Job job) {
            jobTitle.setText(job.getTitle());
            jobCompany.setText(job.getCompany());
            jobLocation.setText(job.getLocation());
            jobDescription.setText(job.getDescription());

        }
    }

    public ArrayList<Job> getItems() {
        return items;
    }

    public void setItems(ArrayList<Job> items) {
        this.items = items;
    }
}
