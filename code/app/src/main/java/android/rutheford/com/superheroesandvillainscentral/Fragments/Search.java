package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SearchResultsView;
import android.rutheford.com.superheroesandvillainscentral.Adapters.SearchView;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Appearance;
import android.rutheford.com.superheroesandvillainscentral.Models.Biography;
import android.rutheford.com.superheroesandvillainscentral.Models.Connections;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Models.Image;
import android.rutheford.com.superheroesandvillainscentral.Models.PowerStats;
import android.rutheford.com.superheroesandvillainscentral.Models.SearchName;
import android.rutheford.com.superheroesandvillainscentral.Models.Work;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick R.
 */

public class Search extends Fragment
{
    // declarations
    RecyclerView mainRecyclerView, secondaryRecyclerView;
    SearchView searchView;
    SearchResultsView searchResultsView;
    View mainView;
    private List<SearchName> searchNameList = new ArrayList<>();
    private String searchString;

    public Search()
    {  // empty cons
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
        Main();
        return mainView;
    }
    protected void Main(){
        checkArgumentsForSearch();
        setUpIdsForRecycler();
        setUpNestedScrollAndFocusable();
        setUpRecyclersAndLayout();
        setUpAdapters();
    }
    private void checkArgumentsForSearch(){ // if artguments is not equal to null set the search string up, and loop through json
        if(getArguments() != null){
            searchString = getArguments().getString("searchString");
        }
        loopThorughJson();
        if(searchString != null)
        {
            loadASearchByName(searchString);
        }
    }
    private void setUpNestedScrollAndFocusable(){ // set up nested scrolling and focusable.
        mainRecyclerView.setNestedScrollingEnabled(false);
        mainRecyclerView.setFocusable(false);
        secondaryRecyclerView.setNestedScrollingEnabled(false);
        secondaryRecyclerView.setFocusable(false);
    }
    private void setUpIdsForRecycler(){ // set up ids for recycler
        mainRecyclerView = mainView.findViewById(R.id.search_recycler_view);
        secondaryRecyclerView = mainView.findViewById(R.id.secondary_recycler_view);
    }
    private void setUpRecyclersAndLayout(){ // set up recyclers and layouts.
        searchView = new SearchView(getContext());
        searchResultsView = new SearchResultsView(getContext(),HomeData.searchCharacterData);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setUpAdapters(){ // set up adapters
        mainRecyclerView.setAdapter(searchView);
        secondaryRecyclerView.setAdapter(searchResultsView);
    }
    public String loadJSONFromAsset() { // load in actual json.
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("all.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    private void loopThorughJson()
    { // loop through json for given fields.
        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());
            for(int i = 0; i < obj.length(); i++){
                JSONObject object = obj.getJSONObject(i);
                Id id = new Id();
                id.setId(object.getInt("id"));
                id.setName(object.getString("name"));
                id.setSlug(object.getString("slug"));
                JSONObject powerStatsObject = object.getJSONObject("powerstats");
                PowerStats powerStats= new PowerStats();
                powerStats.setIntelligence(powerStatsObject.getInt("intelligence"));
                powerStats.setStrength(powerStatsObject.getInt("strength"));
                powerStats.setSpeed(powerStatsObject.getInt("speed"));
                powerStats.setDurability(powerStatsObject.getInt("durability"));
                powerStats.setPower(powerStatsObject.getInt("power"));
                powerStats.setCombat(powerStatsObject.getInt("combat"));
                id.setPowerStats(powerStats);
                JSONObject biographyObject = object.getJSONObject("biography");
                Biography biography = new Biography();
                if(biographyObject.has("fullName"))
                {
                    biography.setFullName(biographyObject.getString("fullName"));
                }else{
                    biography.setFullName("");
                }
                if(biographyObject.has("alterEgos"))
                {
                    biography.setAlterEgos(biographyObject.getString("alterEgos"));
                }else{
                    biography.setAlterEgos("None");
                }

                if(biographyObject.has("placeOfBirth")){
                    biography.setPlaceOfBirth(biographyObject.getString("placeOfBirth"));
                }else{
                    biography.setPlaceOfBirth("Unknown");
                }
                if(biographyObject.has("firstAppearance")){
                    biography.setFirstApperance(biographyObject.getString("firstAppearance"));
                }else{
                    biography.setFirstApperance("");
                }
                if(biographyObject.has("publisher")){
                    biography.setPublisher(biographyObject.getString("publisher"));
                }else {
                    biography.setPublisher("");
                }
                biography.setAlignment(biographyObject.getString("alignment"));
                id.setBiography(biography);
                JSONObject appObject = object.getJSONObject("appearance");
                Appearance appearance = new Appearance();
                if(appObject.has("gender")){
                    appearance.setGender(appObject.getString("gender"));
                }else{
                    appearance.setGender("Unknown");
                }
                if(appObject.has("race")){
                    appearance.setRace(appObject.getString("race"));
                }else{
                    appearance.setRace("Unknown");
                }
                if(appObject.has("eyeColor")){
                    appearance.setEyeColor(appObject.getString("eyeColor"));
                }else{
                    appearance.setEyeColor("Unknown");
                }
                if(appObject.has("hairColor")){
                    appearance.setHairColor(appObject.getString("hairColor"));
                }else{
                    appearance.setHairColor("Unknown");
                }
                id.setAppearance(appearance);
                JSONObject workObject = object.getJSONObject("appearance");
                Work work = new Work();
                if(workObject.has("occupation")){
                    work.setOccupation(appObject.getString("occupation"));
                }else{
                    work.setOccupation("Unknown");
                }
                if(workObject.has("base")){
                    work.setBase(appObject.getString("base"));
                }else{
                    work.setBase("Unknown");
                }
                id.setWork(work);

                JSONObject conObject = object.getJSONObject("connections");
                Connections connections = new Connections();

                if(conObject.has("groupAffiliation")){
                    connections.setGroupAffiliation(conObject.getString("groupAffiliation"));
                }else{
                    connections.setGroupAffiliation("Unknown");
                }
                connections.setRelatives("Unknown");
                JSONObject imageObject = object.getJSONObject("images");
                Image image = new Image();

                if(imageObject.has("md")){
                    image.setMd(imageObject.getString("md"));
                }else{
                    image.setMd(imageObject.getString("md"));
                }
                id.setImage(image);
                id.setConnections(connections);
                HomeData.searchCharacterData.add(id);
            }
        } catch (JSONException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
    private void loadASearchByName(String name){ // load values by name if there is a match
        for(int i =0; i < HomeData.searchCharacterData.size(); i++){
            if(name.toUpperCase().equals(HomeData.searchCharacterData.get(i).getName().toUpperCase())){
                Id id = new Id();
                id.setId(HomeData.searchCharacterData.get(i).getId());
                id.setName(HomeData.searchCharacterData.get(i).getName());
                id.setSlug(HomeData.searchCharacterData.get(i).getSlug());
                PowerStats powerStats = new PowerStats();
                powerStats.setIntelligence(HomeData.searchCharacterData.get(i).getPowerStats().getIntelligence());
                powerStats.setStrength(HomeData.searchCharacterData.get(i).getPowerStats().getStrength());
                powerStats.setSpeed(HomeData.searchCharacterData.get(i).getPowerStats().getSpeed());
                powerStats.setDurability(HomeData.searchCharacterData.get(i).getPowerStats().getDurability());
                powerStats.setPower(HomeData.searchCharacterData.get(i).getPowerStats().getPower());
                powerStats.setCombat(HomeData.searchCharacterData.get(i).getPowerStats().getCombat());
                id.setPowerStats(powerStats);
                Appearance appearance = new Appearance();
                appearance.setGender(HomeData.searchCharacterData.get(i).getAppearance().getGender());
                appearance.setRace(HomeData.searchCharacterData.get(i).getAppearance().getRace());
                appearance.setHairColor(HomeData.searchCharacterData.get(i).getAppearance().getHairColor());
                appearance.setEyeColor(HomeData.searchCharacterData.get(i).getAppearance().getEyeColor());
                id.setAppearance(appearance);
                Biography biography = new Biography();
                biography.setFullName(HomeData.searchCharacterData.get(i).getBiography().getFullName());
                biography.setAlterEgos(HomeData.searchCharacterData.get(i).getBiography().getAlterEgos());
                biography.setPublisher(HomeData.searchCharacterData.get(i).getBiography().getPublisher());
                biography.setAlignment(HomeData.searchCharacterData.get(i).getBiography().getAlignment());
                biography.setPlaceOfBirth(HomeData.searchCharacterData.get(i).getBiography().getPlaceOfBirth());
                id.setBiography(biography);
                Work work = new Work();
                work.setOccupation(HomeData.searchCharacterData.get(i).getWork().getOccupation());
                id.setWork(work);
                Connections connections = new Connections();
                connections.setGroupAffiliation(HomeData.searchCharacterData.get(i).getConnections().getGroupAffiliation());
                id.setConnections(connections);
                Image image = new Image();
                image.setMd(HomeData.searchCharacterData.get(i).getImage().getMd());
                List<Id> newList = new ArrayList<>();
                id.setImage(image);
                newList.add(id);
                HomeData.searchCharacterData = newList;
                searchString = null;
            }
        }
    }
}