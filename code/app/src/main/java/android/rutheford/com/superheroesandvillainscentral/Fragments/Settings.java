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

/**
 * Created by Nick R.
 */

public class Settings extends Fragment
{
    // declarations
    private List<SettingModel> sizeOfSettings = new ArrayList<>();
    private View mainSettingsView;
    private RecyclerView mainRecyclerView, secondaryRecyclerView;
    private SettingsTitle settingsTitle;
    private SettingsView settingsView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        addToList();
        mainSettingsView = inflater.inflate(R.layout.settings_recycler_view, container, false);
        Main();
        return mainSettingsView;
    }
    public static Settings newInstance(){ // return a instance of settings
        return new Settings();
    }
    protected void Main(){
        addToList();
        setUpRecyclers();
        setNestedAndFocusableForRecyclers();
        setUpAdaptersToLayouts();
        setUpAdapters();
    }
    private void setUpRecyclers(){ // set recycler views init.
        mainRecyclerView = mainSettingsView.findViewById(R.id.settings_recyclerview);
        secondaryRecyclerView = mainSettingsView.findViewById(R.id.settings_secondary_recyclerview);
    }
    private void setNestedAndFocusableForRecyclers(){
        mainRecyclerView.setFocusable(false);
        mainRecyclerView.setNestedScrollingEnabled(false);
        secondaryRecyclerView.setFocusable(false);
        secondaryRecyclerView.setNestedScrollingEnabled(false);
    }
    private void setUpAdaptersToLayouts(){ // adapters to layouts
        settingsTitle = new SettingsTitle(getContext());
        settingsView = new SettingsView(getContext(), sizeOfSettings);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setUpAdapters(){ // set adapters
        mainRecyclerView.setAdapter(settingsTitle);
        secondaryRecyclerView.setAdapter(settingsView);
    }
    private void addToList(){ // if the array list size is less then 1, go ahead and array list size to model.
        if(sizeOfSettings.size() < 1)
        {
            sizeOfSettings.add(new SettingModel("Replay OnBoarding", "Replay on-boarding for questions and concerns; to get answered thorough the app! ", R.drawable.onboard));
            sizeOfSettings.add(new SettingModel("Dark Mode", "Change the app the dark mode, based on your preferences!", R.drawable.darkeye));
            sizeOfSettings.add(new SettingModel("View API Website", "View Website API, in order to view all Superheros and Villains!", R.drawable.website));
            sizeOfSettings.add(new SettingModel("View Character", "View your Character Stats or change current Character!", R.drawable.cape));
            sizeOfSettings.add(new SettingModel("View Record / XP", "See how many times you have fallen, and how many times you have been victorious. Also view XP, for proper spending!", R.drawable.trophy));
            sizeOfSettings.add(new SettingModel("Terms Of Conditions", "Take a quick pick of terms of conditions for app!", R.drawable.tc));
            sizeOfSettings.add(new SettingModel("Generate New Characters","Battle new characters from the home screen, and see if you have what it takes!",R.drawable.boysuperhero));
            sizeOfSettings.add(new SettingModel("View Purchase Characters","View current characters purchase throughout the app, and have the ability to switch between characters! ",R.drawable.characters));
            sizeOfSettings.add(new SettingModel("Change Game Mode","Choose Between Simulation Mode Or Battle Mode",R.drawable.fighting));
            sizeOfSettings.add(new SettingModel("Reset Game","Reset Stats To Start From The Ground Up!",R.drawable.restart));
        }
    }
}
