package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.HomeView;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends Fragment
{
    private RecyclerView mainRecyclerView;
    private HomeView homeView;
    View mainLayout;

    public Home()
    {

    }

    public static Fragment newInstance()
    {
        return new Home();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mainLayout = inflater.inflate(R.layout.main_recylcer_view,container,false);
        mainRecyclerView = mainLayout.findViewById(R.id.main_recyclerview);
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
        homeView = new HomeView(getContext());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(homeView);
        return mainLayout;
    }
}
