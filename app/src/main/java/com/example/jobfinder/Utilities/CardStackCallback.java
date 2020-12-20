package com.example.jobfinder.Utilities;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

import com.example.jobfinder.Model.Job;

public class CardStackCallback extends DiffUtil.Callback {

    private ArrayList<Job> oldList , newList;

    public CardStackCallback(ArrayList<Job> oldList, ArrayList<Job> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTitle() == newList.get(newItemPosition).getTitle();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }
}