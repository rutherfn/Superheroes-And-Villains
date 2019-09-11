package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ViewPagerAdapter;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Network.GetDataService;
import android.rutheford.com.superheroesandvillainscentral.Network.RetroFitInstance;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private RelativeLayout relativeLayoutMain;
    private ViewPager mainViewPager;
    private ViewPagerAdapter mainAdapter;
    private Toolbar mainActivityToolBar;
    private ImageView settingsImageView;
    Id IdOpp = new Id();
    private TextView mTextViewToolBar;
    private BottomNavigationView mainBottomNavigationView;
    private String toolBarTitle = "SUPER HEROES AND VILLAINS CENTRAL";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main();
    }

    protected  void Main(){
        setUpIds();
        setUpToolBar();
        bottomNavigationViewListener();
        settingsOnTouchListener();
    }
    public int returnCurrentViewPagerItem(){
        return mainViewPager.getCurrentItem();
    }
    public void setSearchPager(){
        mainViewPager.setCurrentItem(1,false);
    }
    public void changeActivitys(Intent intent){
        startActivity(intent);
    }
    public void reloadFragmentAndSetViewPagerForSearch(){
        Fragment fragment = mainAdapter.getFragment(mainViewPager.getCurrentItem());
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(1,false);
    }
    public void reloadFragmentViewYourCharacter(){
        Fragment fragment = mainAdapter.getFragment(6);
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(6,false);
    }
    private void SetBottomColorFirst(BottomNavigationView bottomNavigationView)
    {
        int[] colors = new int[]{
                Color.BLUE,
                Color.GRAY,
                Color.GRAY
        };

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked}
        };

        ColorStateList ColorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(ColorStateList);
        bottomNavigationView.setItemIconTintList(ColorStateList);
    }
    private void SetBottomColorSecond(BottomNavigationView bottomNavigationView)
    {
        int[] colors = new int[]{
                Color.GRAY,
                Color.BLUE,
                Color.GRAY
        };

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked}
        };


        ColorStateList ColorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(ColorStateList);
        bottomNavigationView.setItemIconTintList(ColorStateList);
    }
    private void SetBottomColorThird(BottomNavigationView bottomNavigationView)
    {
        int[] colors = new int[]{
                Color.GRAY,
                Color.GRAY,
                Color.BLUE
        };

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}
        };


        ColorStateList ColorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(ColorStateList);
        bottomNavigationView.setItemIconTintList(ColorStateList);
    }
    private void setUpIds(){
        mainActivityToolBar = findViewById(R.id.toolbarMainActivity);
        mTextViewToolBar = findViewById(R.id.toolBarTitle);
        settingsImageView = findViewById(R.id.settingsImageView);
        mainBottomNavigationView = findViewById(R.id.bottom_navigation);
        relativeLayoutMain = findViewById(R.id.relativeLayoutMain);
        mainViewPager = findViewById(R.id.viewpager_id);
        setSupportActionBar(mainActivityToolBar);
        mainAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainAdapter);
        mainViewPager.setOffscreenPageLimit(10);
        SetBottomColorFirst(mainBottomNavigationView);
        HomeData.userNumber = 0;
    }
    public void switchToVs(){
        Fragment fragment = mainAdapter.getFragment(4);
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(4,false);
    }
    private void setUpToolBar(){
        Typeface logoTypeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"AlegreyaSC-Regular.otf");
        mTextViewToolBar.setTypeface(logoTypeFace);
        mTextViewToolBar.setTextColor(Color.parseColor("#0000ff"));
        mTextViewToolBar.setText(toolBarTitle);
    }
    public void changeToStatsView(){
        Fragment fragment = mainAdapter.getFragment(3);
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(3,false);
    }
    public void setViewPagerHome(){
        SetBottomColorFirst(mainBottomNavigationView);
       mainViewPager.setCurrentItem(0,false);
    }
    public void setBottomColor(){
        SetBottomColorFirst(mainBottomNavigationView);
    }
    private void settingsOnTouchListener(){
        settingsImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainViewPager.setCurrentItem(5,false);
            }
        });
    }
    public void reloadForDarkMode(){
        Fragment fragment = mainAdapter.getFragment(mainViewPager.getCurrentItem());
        //  fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        SharedPreferences sp1 = getApplicationContext().getSharedPreferences("key", 0);
        if(sp1.getInt("darkMode",0) == 1){
            relativeLayoutMain.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorBlack));
            mainBottomNavigationView.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorBlack));
        }else{
            relativeLayoutMain.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorWwhite));
            mainBottomNavigationView.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorWwhite));
        }
    }

    @Override
    public void onBackPressed()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        if(mainViewPager.getCurrentItem() == 0){
            finish();
        }else if(mainViewPager.getCurrentItem() == 1){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 2){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 3){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 4){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 5){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 6){
            mainViewPager.setCurrentItem(5,false);
        }else if(mainViewPager.getCurrentItem() == 7){
            mainViewPager.setCurrentItem(5,false);
        }
    }

    public void setBackStackVisible(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        mainViewPager.setCurrentItem(0,false);
        return super.onOptionsItemSelected(item);
    }

    public void changeViewPager(int pos){
        mainViewPager.setCurrentItem(pos,false);
    }

    private void bottomNavigationViewListener(){
        mainBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.homeAction:
                        SetBottomColorFirst(mainBottomNavigationView);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        mainViewPager.setCurrentItem(0, false);

                        return true;

                    case R.id.searchAction:
                        SetBottomColorSecond(mainBottomNavigationView);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        mainViewPager.setCurrentItem(1,false);
                        return true;

                    case R.id.battleAction:
                        SetBottomColorThird(mainBottomNavigationView);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        mainViewPager.setCurrentItem(2,false);
                        return true;

                }
                return false;
            }
        });
    }
    public void callApiForNewRandomBattle(){
        Random r = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size()<20){
            uniqueNumbers.add(r.nextInt(731));
        }
        List<Integer> listOfNumbers = new ArrayList<>(uniqueNumbers);
            GetDataService apiCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
            final Call<Id> searchById = apiCall.getById("akabab/superhero-api/0.2.0/api//id/" + listOfNumbers.get(0)  + ".json");
            searchById.enqueue(new Callback<Id>()
            {
                @Override
                public void onResponse(Call<Id> call, Response<Id> response)
                {
                    if(response.body() != null){
                       IdOpp =  response.body();
                       HomeData.opponentId = new ArrayList<>();
                       HomeData.opponentId.add(IdOpp);
                        System.out.println(HomeData.opponentId.get(0).getName() + " ride it");
                    }
                }

                @Override
                public void onFailure(Call<Id> call, Throwable t)
                {
                    //  callApi();
                    System.out.println("Failed");
                }
            });

    }
    public void callApiForNewCharacter(){
        Random r = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size()<20){
            uniqueNumbers.add(r.nextInt(731));
        }
        List<Integer> listOfNumbers = new ArrayList<>(uniqueNumbers);
        GetDataService apiCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
        final Call<Id> searchById = apiCall.getById("akabab/superhero-api/0.2.0/api//id/" + listOfNumbers.get(0)  + ".json");
        searchById.enqueue(new Callback<Id>()
        {
            @Override
            public void onResponse(Call<Id> call, Response<Id> response)
            {
                if(response.body() != null){
                    IdOpp =  response.body();
                    HomeData.searchCharacterData = new ArrayList<>();
                    HomeData.searchCharacterData.add(IdOpp);
                }
            }

            @Override
            public void onFailure(Call<Id> call, Throwable t)
            {
                //  callApi();
                System.out.println("Failed");
            }
        });
        reloadFragmentViewYourCharacter();

    }

}
