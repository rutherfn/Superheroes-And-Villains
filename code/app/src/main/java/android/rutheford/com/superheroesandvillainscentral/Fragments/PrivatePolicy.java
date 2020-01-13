package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.PrivatePolicyView;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nick R.
 */


public class PrivatePolicy extends Fragment
{
    // declarations
    private View view;
    private RecyclerView recyclerView;
    private PrivatePolicyView privatePolicyView;

    public PrivatePolicy()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view  = inflater.inflate(R.layout.terms_cond_recycler,container,false);
        Main();
        return view;
    }
    protected void Main(){
        setUpRecyclerView();
        setUpNestedScrollAndFocusable();
        initRecyclerAndSetAdapter();
    }
    private void setUpRecyclerView(){
        // method to set up id of recycler view
        recyclerView = view.findViewById(R.id.terms_recyclerview);
    }
    private void setUpNestedScrollAndFocusable(){
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
    }
    private void initRecyclerAndSetAdapter(){
        privatePolicyView = new PrivatePolicyView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(privatePolicyView);
    }

    public static Fragment newInstance(){  // return a instance of private policy
        return new PrivatePolicy();
    }
}
