package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ImageView.StatsImage;
import android.rutheford.com.superheroesandvillainscentral.Adapters.StatsView;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick R.
 */

public class YourCharacter extends Fragment
{
    // declarations
    private View view;
    private RecyclerView mainCharacterRecycler, viewYourCharacterRecycler;
    private StatsView viewYourCharacter;
    private StatsImage statsImage;
    private List<Id> list = new ArrayList<>();

    public YourCharacter()
    { // default const
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.yourcharacter_recyclerview, container, false);
        Main();
        return view;
    }
    protected  void Main(){
        setUpIds();
        setUpRecyclerViews();
        setUpAdapters();
        setAdaptersToRecyclerView();
    }
    private void setUpIds(){ // setting up ids for recycler views.
        mainCharacterRecycler = view.findViewById(R.id.yourcharacter_main_recyclerview);
        viewYourCharacterRecycler = view.findViewById(R.id.yourcharacter_secondary_recyclerview);
    }
    private void setUpAdapters(){
        viewYourCharacter = new StatsView(getContext(),list);
        statsImage = new StatsImage(list);
    }
    private void setUpRecyclerViews(){
        viewYourCharacterRecycler.setFocusable(false);
        viewYourCharacterRecycler.setNestedScrollingEnabled(false);
        mainCharacterRecycler.setFocusable(false);
        mainCharacterRecycler.setNestedScrollingEnabled(false);
        mainCharacterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        viewYourCharacterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setAdaptersToRecyclerView(){
        mainCharacterRecycler.setAdapter(statsImage);
        viewYourCharacterRecycler.setAdapter(viewYourCharacter);
    }
    public static YourCharacter newInstance(){ // returns instance of character.
        return new YourCharacter();
    }
}
