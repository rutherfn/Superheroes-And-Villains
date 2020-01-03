package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.TacView;
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

public class TacticalVs extends Fragment
{
    private View view;
    private RecyclerView mainRecyclerView;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TacView tacView;
    private List<Id> idList;

    public TacticalVs(){

    }
    public static Fragment newInstance(){
        return new TacticalVs();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.recyclerview_tactical,container,false);
        Main();
        return view;
    }
    protected void Main(){
        setUpSharedPrefs();
        setUpRecyclers();
        setUpArrayList();
        setUpAdaptersToRecyclers();
    }
    private void setUpSharedPrefs(){
        sp = (getContext()).getSharedPreferences("key",0);
        editor = sp.edit();
    }
    private void setUpRecyclers(){
        mainRecyclerView = view.findViewById(R.id.tac_recyclerview);
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setUpArrayList(){
        if(getArguments() != null){
            idList = getArguments().getParcelableArrayList("vsListTac");
        }
    }
    private void setUpAdaptersToRecyclers(){
        tacView = new TacView(getContext(),idList);
        mainRecyclerView.setAdapter(tacView);
    }
}
