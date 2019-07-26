package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.VsView;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Vs extends Fragment
{
    View vsView;
    VsView vsViewAdapter;
    RecyclerView mainVsRecyclerView;
    public Vs()
    {
    }
    public static Vs newInstance()
    {
        return new Vs();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        vsView = inflater.inflate(R.layout.vs_recycler_view,container,false);
        mainVsRecyclerView = vsView.findViewById(R.id.vs_recyclerview);
        mainVsRecyclerView.setFocusable(false);
        mainVsRecyclerView.setNestedScrollingEnabled(false);
        vsViewAdapter = new VsView(getContext());
        mainVsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainVsRecyclerView.setAdapter(vsViewAdapter);
        return vsView;
    }
}
