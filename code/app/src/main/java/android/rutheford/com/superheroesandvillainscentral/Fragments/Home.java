package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.HomeView;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Models.Results;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nick R.
 */

public class Home extends Fragment
{ // declarations
    private int numberTracker;
    private boolean saveString;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private boolean showHere;
    private RecyclerView mainRecyclerView;
    private HomeView homeView;
    View mainLayout;
    private Results idObjectTwo = new Results();
    private Id idObject = new Id();
    ArrayList<Results> resultsList = new ArrayList<>();
    private List<Id> idList = new ArrayList<>();

    public Home()
    {
        // default constructor.
    }

    public static Fragment newInstance()
    { // return a instance of Home.
        return new Home();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mainLayout = inflater.inflate(R.layout.main_recylcer_view,container,false);
        Main();
        return mainLayout;
    }
    protected void Main(){
        setUpSharedPrefs();
        idList.clear();
        callUserCharacter();
        callApi();;
    }
    @SuppressLint({"CommitPrefEdits", "NewApi"})
    private void setUpSharedPrefs(){
        sp = (Objects.requireNonNull(getContext())).getSharedPreferences("key",0);
        editor = sp.edit();
    }
    private void callUserCharacter(){ // call user character, that is saved into shared prefs.
        if(sp.getInt("uniqueId",0) != 0)
        {
            numberTracker = sp.getInt("uniqueId",0);
        }else{
            numberTracker = 510;
        }
            GetDataService userCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
            final Call<Results> userSearchById = userCall.getByResults("akabab/superhero-api/0.2.0/api//id/" + numberTracker + ".json");
            userSearchById.enqueue(new Callback<Results>()
            {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response)
                {
                    if(response.isSuccessful()){
                        idObjectTwo = response.body();
                        System.out.println(idObjectTwo.getImage().getSm());
                        resultsList.add(idObjectTwo);
                        SearchName searchName = new SearchName();
                        searchName.setResults(resultsList);
                        HomeData.searchNameList = new ArrayList<>();
                        HomeData.searchNameList.add(searchName);
                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t)
                {

                }
            });

    }
    private void callApi(){ // call api of list of characters
        Random r = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size()<20){
            uniqueNumbers.add(r.nextInt(731));
        }
        List<Integer> listOfNumbers = new ArrayList<>(uniqueNumbers);
        for (int i =0; i < listOfNumbers.size(); i++ ){
            System.out.println(" here is i " + i);
            GetDataService apiCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
            final Call<Id> searchById = apiCall.getById("akabab/superhero-api/0.2.0/api//id/" + listOfNumbers.get(i) + ".json");
            if(i == 19){
                showHere = true;
            }
            if(i == 0){
                saveString = true;
            }else{
                saveString = false;
            }
            searchById.enqueue(new Callback<Id>()
            {
                @Override
                public void onResponse(Call<Id> call, Response<Id> response)
                {
                    if(response.body() != null){
                        if(saveString){
                            editor.putString("nameValue",response.body().getName());
                            editor.apply();;
                        }
                        idObject= response.body();
                        idList.add(idObject);
                    }
                    if(showHere)
                    {
                        mainRecyclerView = mainLayout.findViewById(R.id.main_recyclerview);
                        mainRecyclerView.setNestedScrollingEnabled(false);
                        mainRecyclerView.setFocusable(false);
                        homeView = new HomeView(idList, getContext());
                        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mainRecyclerView.setAdapter(homeView);
                        mainRecyclerView.setVisibility(View.VISIBLE);

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

