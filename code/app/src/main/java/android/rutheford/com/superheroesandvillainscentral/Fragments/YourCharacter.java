package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ImageView.StatsImage;
import android.rutheford.com.superheroesandvillainscentral.Adapters.StatsView;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class YourCharacter extends Fragment
{
    private StatsImage statsImage;
    private Context mContext;
    private View v;
    private StatsView viewYourCharacter;
    private RecyclerView viewYourCharacterRecycler;
    private RecyclerView mainCharacterRecycler;
    public YourCharacter()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.yourcharacter_recyclerview,container,false);
        mainCharacterRecycler = v.findViewById(R.id.yourcharacter_main_recyclerview);
        viewYourCharacterRecycler = v.findViewById(R.id.yourcharacter_secondary_recyclerview);
        viewYourCharacterRecycler.setFocusable(false);
        viewYourCharacterRecycler.setNestedScrollingEnabled(false);
        mainCharacterRecycler.setFocusable(false);
        mainCharacterRecycler.setNestedScrollingEnabled(false);
        if(HomeData.opponentId != null){
            HomeData.opponentId = null;
        }
        mainCharacterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        viewYourCharacterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        viewYourCharacter = new StatsView(getContext());
        statsImage = new StatsImage(getContext());
        mainCharacterRecycler.setAdapter(statsImage);
        viewYourCharacterRecycler.setAdapter(viewYourCharacter);
        return v;
    }
    public static YourCharacter newInstance(){
        return new YourCharacter();
    }
}
