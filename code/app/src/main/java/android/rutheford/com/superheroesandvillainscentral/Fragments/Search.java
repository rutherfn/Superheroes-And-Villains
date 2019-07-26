package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SearchResultsView;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SearchView;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.SearchName;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends Fragment
{
     RecyclerView mainRecyclerView, secondaryRecyclerView;
        SearchView searchView;
        SearchResultsView searchResultsView;
        View mainView;
        private SearchName searchName = new SearchName();
        private List<SearchName> searchNameList = new ArrayList<>();

    public Search()
    {
    }

    public static Fragment newInstance()
    {
        return new Search();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        searchNameList.clear();
        mainView = inflater.inflate(R.layout.search_recycler_view,container,false);
        if(HomeData.searchBoolean)
        {
            loadASearchByName(HomeData.searchFromUser);
        }
        mainRecyclerView = mainView.findViewById(R.id.search_recycler_view);
        secondaryRecyclerView = mainView.findViewById(R.id.secondary_recycler_view);
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
        secondaryRecyclerView.setNestedScrollingEnabled(false);
        secondaryRecyclerView.setFocusable(false);
        searchView = new SearchView(getContext());
        searchResultsView = new SearchResultsView(getContext(),searchNameList);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(searchView);
        secondaryRecyclerView.setAdapter(searchResultsView);
        return mainView;
    }
    private void loadASearchByName(String name){
        GetDataService apiSearchByNameCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
        final Call<SearchName> searchByName = apiSearchByNameCall.getSearchByName("/api/10211183686108194/search/" + name);
        searchByName.enqueue(new Callback<SearchName>()
        {
            @Override
            public void onResponse(Call<SearchName> call, Response<SearchName> response)
            {

                searchName = response.body();
                if(searchName.getResponse().equals("error")){
                    searchNameList.clear();
                        }else{
                searchNameList.add(searchName);
                if(searchNameList.size() == 1)
                {
                    mainRecyclerView.setNestedScrollingEnabled(false);
                    mainRecyclerView.setFocusable(false);
                    secondaryRecyclerView.setNestedScrollingEnabled(false);
                    secondaryRecyclerView.setFocusable(false);
                    searchView = new SearchView(getContext());
                    searchResultsView = new SearchResultsView(getContext(), searchNameList);
                    mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    secondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mainRecyclerView.setAdapter(searchView);
                    secondaryRecyclerView.setAdapter(searchResultsView);
                }
            }
            }

            @Override
            public void onFailure(Call<SearchName> call, Throwable t)
            {

            }
        });

    }
}
