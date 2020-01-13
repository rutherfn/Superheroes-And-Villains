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

/**
 * Created by Nick R.
 */

public class TacticalVs extends Fragment
{
    // declarations
    private View view;
    private RecyclerView mainRecyclerView;
    private TacView tacView;
    private List<Id> idList;

    public TacticalVs(){
        // default cons
    }
    public static Fragment newInstance(){ // returns a instance of tac view
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
        setUpRecyclers();
        setUpArrayList();
        setUpAdaptersToRecyclers();
    }
    private void setUpRecyclers(){ // init items
        mainRecyclerView = view.findViewById(R.id.tac_recyclerview);
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setUpArrayList(){ // set up array list for items checks arguents.
        if(getArguments() != null){
            idList = getArguments().getParcelableArrayList("vsListTac");
        }
    }
    private void setUpAdaptersToRecyclers(){ // set adapters to recyclers
        tacView = new TacView(getContext(),idList);
        mainRecyclerView.setAdapter(tacView);
    }
}
