package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SettingsTitle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SettingsView;
import android.rutheford.com.superheroesandvillainscentral.Models.Settings.SettingModel;
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

public class Settings extends Fragment
{
    private RecyclerView mainRecyclerView,secondaryRecyclerView;
    private View mainSettingsView;
    private SettingsTitle settingsTitle;
    private SettingsView settingsView;
    private List<SettingModel> sizeOfSettings = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        addToList();
        mainSettingsView = inflater.inflate(R.layout.settings_recycler_view,container,false);
        mainRecyclerView = mainSettingsView.findViewById(R.id.settings_recyclerview);
        secondaryRecyclerView = mainSettingsView.findViewById(R.id.settings_secondary_recyclerview);
        mainRecyclerView.setFocusable(false);
        mainRecyclerView.setNestedScrollingEnabled(false);
        secondaryRecyclerView.setFocusable(false);
        secondaryRecyclerView.setNestedScrollingEnabled(false);
        settingsTitle = new SettingsTitle();
        settingsView = new SettingsView(getContext(),sizeOfSettings);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(settingsTitle);
        secondaryRecyclerView.setAdapter(settingsView);
        return mainSettingsView;
    }
    public static Settings newInstance(){
        return new Settings();
    }
    private void addToList(){
        if(sizeOfSettings.size() > 1)
        {
        }else{
            sizeOfSettings.add(new SettingModel("Replay OnBoarding", "Replay on-boarding for questions and concerns; to get answered thorough the app! ", R.drawable.onboard));
            sizeOfSettings.add(new SettingModel("Dark Mode", "Change the app the dark mode, based on your preferences!", R.drawable.darkeye));
            sizeOfSettings.add(new SettingModel("View API Website", "View Website API, in order to view all Superheros and Villains!", R.drawable.website));
            sizeOfSettings.add(new SettingModel("View Character", "View your Character Stats or change current Character!", R.drawable.cape));
            sizeOfSettings.add(new SettingModel("View Record", "See how many times you have fallen, and how many times you have been victorious. ", R.drawable.trophy));
            sizeOfSettings.add(new SettingModel("Terms Of Conditions", "Take a quick pick of terms of conditions for app!", R.drawable.tc));
        }
    }
}
