package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ImageView.StatsImage;
import android.rutheford.com.superheroesandvillainscentral.Adapters.StatsView;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by Nick R.
 */

public class Stats extends Fragment
{
    // declarations
    private StatsImage statsImage;
    private StatsView statsAdapter;
    private RecyclerView mainRecyclerViewStats;
    private RecyclerView secondaryRecyclerViewStats;
    private View statsView;
    private List<Id> listId;
    public Stats()
    { // default fragment
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        initListBasedOnArgument();
        statsView = inflater.inflate(R.layout.stats_recycler_view,container,false);
        Main();
        return statsView;
    }
    private void initListBasedOnArgument(){ // set array list based on arguments if its null or not
        if (getArguments() != null)
        {
            listId = getArguments().getParcelableArrayList("newList");
        }
    }
    protected void Main(){
        initRecyclerView();
        setFocusAndNested();
        setUpAdaptAndLayout();
        setUpAdapters();
    }
    private void initRecyclerView(){
        mainRecyclerViewStats = statsView.findViewById(R.id.stats_main_recyclerview);
        secondaryRecyclerViewStats = statsView.findViewById(R.id.stats_secondary_recyclerview);
    }
    private void setFocusAndNested(){
        mainRecyclerViewStats.setFocusable(false);
        mainRecyclerViewStats.setNestedScrollingEnabled(false);
        secondaryRecyclerViewStats.setFocusable(false);
        secondaryRecyclerViewStats.setNestedScrollingEnabled(false);
    }
    private void setUpAdaptAndLayout(){ // init adapter and set layout.
        statsImage = new StatsImage(listId);
        statsAdapter = new StatsView(getContext(),listId);
        mainRecyclerViewStats.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerViewStats.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setUpAdapters(){ // set up adapters
        mainRecyclerViewStats.setAdapter(statsImage);
        secondaryRecyclerViewStats.setAdapter(statsAdapter);
    }
     public static Stats newInstance()
    { // returns instance of stats
        return new Stats();
    }
}
