package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ImageView.StatsImage;
import android.rutheford.com.superheroesandvillainscentral.Adapters.StatsView;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Stats extends Fragment
{
    StatsImage statsImage;
    StatsView statsAdapter;
    RecyclerView mainRecyclerViewStats;
    RecyclerView secondaryRecyclerViewStats;
    View statsView;
    public Stats()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        statsView = inflater.inflate(R.layout.stats_recycler_view,container,false);
        mainRecyclerViewStats = statsView.findViewById(R.id.stats_main_recyclerview);
        secondaryRecyclerViewStats = statsView.findViewById(R.id.stats_secondary_recyclerview);
        mainRecyclerViewStats.setFocusable(false);
        mainRecyclerViewStats.setNestedScrollingEnabled(false);
        secondaryRecyclerViewStats.setFocusable(false);
        secondaryRecyclerViewStats.setNestedScrollingEnabled(false);
        statsImage = new StatsImage(getContext());
        statsAdapter = new StatsView(getContext());
        mainRecyclerViewStats.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerViewStats.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerViewStats.setAdapter(statsImage);
        secondaryRecyclerViewStats.setAdapter(statsAdapter);

        return statsView;
    }
     public static Stats newInstance()
    {
        return new Stats();
    }
}
