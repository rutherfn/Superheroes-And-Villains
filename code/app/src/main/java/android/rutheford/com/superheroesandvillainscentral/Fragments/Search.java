package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SearchResultsView;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SearchView;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Search extends Fragment
{
     RecyclerView mainRecyclerView, secondaryRecyclerView;
        SearchView searchView;
        SearchResultsView searchResultsView;
        View mainView;

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
        mainView = inflater.inflate(R.layout.search_recycler_view,container,false);
        mainRecyclerView = mainView.findViewById(R.id.search_recycler_view);
        secondaryRecyclerView = mainView.findViewById(R.id.secondary_recycler_view);
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
        secondaryRecyclerView.setNestedScrollingEnabled(false);
        secondaryRecyclerView.setFocusable(false);
        searchView = new SearchView(getContext());
        searchResultsView = new SearchResultsView(getContext());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(searchView);
        secondaryRecyclerView.setAdapter(searchResultsView);
        return mainView;
    }
}
