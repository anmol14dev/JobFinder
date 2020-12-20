package com.example.jobfinder.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobfinder.Activity.HostActivity;
import com.example.jobfinder.Model.Job;
import com.example.jobfinder.R;
import com.example.jobfinder.Utilities.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;

public class JobsFragment extends Fragment {
    private static final String TAG = "JobFragment";
    ArrayList<Job> jobs;
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    CardStackView cardStackView;
    View view;
    public JobsFragment() {
        // Required empty public constructor
    }
    public JobsFragment(ArrayList<Job> jobs){
        this.jobs=new ArrayList<>(jobs);
        Log.v("InJobFragment",Integer.toString(this.jobs.size()));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jobs, container, false);
        cardStackView = view.findViewById(R.id.card_stack_view);
        setDatainView();
        return view;
    }
    private void setDatainView() {
            manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
                @Override
                public void onCardDragging(Direction direction, float ratio) {
                    Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
                }
                @Override
                public void onCardSwiped(Direction direction) {
                    Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                    if (direction == Direction.Right){
                        Toast.makeText(getContext(), "Direction Right", Toast.LENGTH_SHORT).show();
                        ((HostActivity)getActivity()).updateJobList(manager.getTopPosition()-1);
                    }
                    if (direction == Direction.Top){
                        Toast.makeText(getContext(), "Direction Top", Toast.LENGTH_SHORT).show();
                    }
                    if (direction == Direction.Left){
                        Toast.makeText(getContext(), "Direction Left", Toast.LENGTH_SHORT).show();
                    }
                    if (direction == Direction.Bottom){
                        Toast.makeText(getContext(), "Direction Bottom", Toast.LENGTH_SHORT).show();
                    }
                    // Paginating
                    if (manager.getTopPosition() == adapter.getItemCount()){
                        Toast.makeText(getContext(), "End", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCardRewound() {
                    Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
                }
                @Override
                public void onCardCanceled() {
                    Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
                }
                @Override
                public void onCardAppeared(View view, int position) {
                    TextView tv = view.findViewById(R.id.jb_position);
                    Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
                }
                @Override
                public void onCardDisappeared(View view, int position) {
                    TextView tv = view.findViewById(R.id.jb_position);
                    Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
                }
            });
            manager.setStackFrom(StackFrom.None);
            manager.setVisibleCount(3);
            manager.setTranslationInterval(8.0f);
            manager.setScaleInterval(0.95f);
            manager.setSwipeThreshold(0.3f);
            manager.setMaxDegree(20.0f);
            manager.setDirections(Direction.FREEDOM);
            manager.setCanScrollHorizontal(true);
            manager.setCanScrollVertical(false);
            manager.setSwipeableMethod(SwipeableMethod.Manual);
            manager.setOverlayInterpolator(new LinearInterpolator());
            adapter = new CardStackAdapter(jobs);
            cardStackView.setLayoutManager(manager);
            cardStackView.setAdapter(adapter);
            cardStackView.setItemAnimator(new DefaultItemAnimator());
        }
    }