package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.HomeView;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
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

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nick R.
 */

public class PurchaseCharacters extends Fragment
{
    // declarations

    private List<Id> idList = new ArrayList<>();
    private Id id = new Id();
    private HomeView homeView;
    private RecyclerView mainRecyclerView;
    private View view;
    public static int i = 0;
    public PurchaseCharacters(){

    }
    public static Fragment newInstance(){
        return new PurchaseCharacters();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.main_recylcer_view,container,false);
        Main();
        return view;
    }

    protected void Main(){
        callUserCharacterPaul();
        fillUpArrayList();
    }
    private void fillUpArrayList(){ // fill up and view array list for purchase  characters.
        List<String> allKeys = Paper.book().getAllKeys();
        for(int i = 0; i < allKeys.size(); i++){
            final List<Id> idListTwo = Paper.book().read(allKeys.get(i),new ArrayList<Id>());
            for(int l = 0; l < idListTwo.size(); l++){
                GetDataService apiCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
                final Call<Id> searchById = apiCall.getById("akabab/superhero-api/0.2.0/api//id/" + idListTwo.get(l).getId() + ".json");
                searchById.enqueue(new Callback<Id>()
                {
                    @Override
                    public void onResponse(Call<Id> call, Response<Id> response)
                    {
                        if(response.isSuccessful()){
                            id = response.body();
                            idList.add(id);
                            HomeData.listPurchaseCharacters = idList;
                            setUpRecyclerView();
                            setUpRecyclerViewToAdapter();
                        }
                    }

                    @Override
                    public void onFailure(Call<Id> call, Throwable t)
                    {

                    }
                });
            }
        }

        }
    private void callUserCharacterPaul(){ // call user character paul blart.
        GetDataService userCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
        final Call<Id> searchById = userCall.getById("akabab/superhero-api/0.2.0/api//id/" + 510 + ".json");
        searchById.enqueue(new Callback<Id>()
        {
            @Override
            public void onResponse(Call<Id> call, Response<Id> response)
            {
                if(response.isSuccessful()){
                    id = response.body();
                    idList.add(id);
                    setUpRecyclerView();
                    setUpRecyclerViewToAdapter();
                }
            }

            @Override
            public void onFailure(Call<Id> call, Throwable t)
            {

            }
        });
    }
    private void setUpRecyclerView(){ // set up recycler view
        mainRecyclerView = view.findViewById(R.id.main_recyclerview);
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
    }
    private void setUpRecyclerViewToAdapter(){
        homeView = new HomeView(idList,getContext());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(homeView);
        mainRecyclerView.setVisibility(View.VISIBLE);
    }
}
