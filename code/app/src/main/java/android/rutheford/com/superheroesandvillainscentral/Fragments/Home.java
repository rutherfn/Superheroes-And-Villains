package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.HomeView;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Network.GetDataService;
import android.rutheford.com.superheroesandvillainscentral.Network.RetroFitInstance;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment
{
    private boolean showHere;
    private RecyclerView mainRecyclerView;
    private HomeView homeView;
    View mainLayout;
    private Id idObject = new Id();
    private List<Id> idList = new ArrayList<>();

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
        idList.clear();
        mainLayout = inflater.inflate(R.layout.main_recylcer_view,container,false);
        callApi();
        return mainLayout;
    }
    private void callApi(){
        for(int i = 0; i < 12; i++){
            GetDataService apiCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
            Random r = new Random();
            int low = 1;
            int high = 731;
            int result = r.nextInt(high-low) + low;
            final Call<Id> searchById = apiCall.getById("/api/10211183686108194/" + result);
            if(i == idList.size()){
                showHere = true;
            }
            searchById.enqueue(new Callback<Id>()
            {
                @Override
                public void onResponse(Call<Id> call, Response<Id> response)
                {
                    idObject= response.body();
                    idList.add(idObject);
                    if(showHere)
                    {
                        mainRecyclerView = mainLayout.findViewById(R.id.main_recyclerview);
                        mainRecyclerView.setNestedScrollingEnabled(false);
                        mainRecyclerView.setFocusable(false);
                        homeView = new HomeView(idList, getContext());
                        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mainRecyclerView.setAdapter(homeView);
                    }
                }

                @Override
                public void onFailure(Call<Id> call, Throwable t)
                {
                    System.out.println("Failed");
                }
            });
        }
    }
    }

