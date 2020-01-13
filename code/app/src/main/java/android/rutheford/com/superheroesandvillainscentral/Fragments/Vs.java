package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.VsView;
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

public class Vs extends Fragment
{
    private List<Id> idList;
    private View vsView;
    private VsView vsViewAdapter;
    private RecyclerView mainVsRecyclerView;
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
        setArgumentsToList();
        vsView = inflater.inflate(R.layout.vs_recycler_view,container,false);
        Main();
        return vsView;
    }
    protected void Main(){
        initRecyclerView();
        setFocusAndNestedRecycler();
        setLayoutAndAdapter();
    }
    private void setArgumentsToList(){
        if(getArguments() != null){
            idList = getArguments().getParcelableArrayList("vsList");
        }
    }
    private void initRecyclerView(){
        mainVsRecyclerView = vsView.findViewById(R.id.vs_recyclerview);
    }
    private void setFocusAndNestedRecycler(){
        mainVsRecyclerView.setFocusable(false);
        mainVsRecyclerView.setNestedScrollingEnabled(false);
    }
    private void setLayoutAndAdapter(){
        vsViewAdapter = new VsView(getContext(),idList);
        mainVsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainVsRecyclerView.setAdapter(vsViewAdapter);
    }
}
