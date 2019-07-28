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
        sizeOfSettings.add(new SettingModel("dad","one directions","https://cnet2.cbsistatic.com/img/l2Hh3-frh-RCCmE2FCZIY8PeUdQ=/1092x0/2019/03/26/d50717a6-5f2d-418e-9fa4-caaf7c6faea5/spider-man-far-from-home-og-size-image.jpg"));
        sizeOfSettings.add(new SettingModel("dad","one directions","https://cnet2.cbsistatic.com/img/l2Hh3-frh-RCCmE2FCZIY8PeUdQ=/1092x0/2019/03/26/d50717a6-5f2d-418e-9fa4-caaf7c6faea5/spider-man-far-from-home-og-size-image.jpg"));
    }
}
