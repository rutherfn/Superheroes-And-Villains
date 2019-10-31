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

public class PrivatePolicy extends Fragment
{
    View view;
    RecyclerView recyclerView;
    PrivatePolicyView privatePolicyView;

    public PrivatePolicy()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view  = inflater.inflate(R.layout.terms_cond_recycler,container,false);
        recyclerView = view.findViewById(R.id.terms_recyclerview);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        privatePolicyView = new PrivatePolicyView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(privatePolicyView);
        return view;
    }

    public static Fragment newInstance(){
        return new PrivatePolicy();
    }
}
