package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ViewPagerAdapter;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.Models.Results;
import android.rutheford.com.superheroesandvillainscentral.Models.SearchName;
import android.rutheford.com.superheroesandvillainscentral.Network.GetDataService;
import android.rutheford.com.superheroesandvillainscentral.Network.RetroFitInstance;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nick R.
 */

public class MainActivity extends AppCompatActivity
{
    // declarations
    private int[][] states;
    private int[] colors;
    private Bundle opponentBundle = new Bundle();
    private Bundle putBool = new Bundle();
    private Bundle vsBundle = new Bundle();
    private Toolbar mainActivityToolBar;
    private RelativeLayout relativeLayoutMain;
    private ViewPager mainViewPager;
    private ViewPagerAdapter mainAdapter;
    private TextView mTextViewToolBar;
    private BottomNavigationView mainBottomNavigationView;
    private SharedPreferences sp1;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main();
    }

    protected  void Main(){
        // protected Void Main method to call all of my methods through.
        setUpSharedPres();
        setUpIds();
        setUpToolBar();
        bottomNavigationViewListener();
        checkDarkMode();
        setViewPagerItemToSettings();
        Paper.init(getApplicationContext());
    }
    private void setViewPagerItemToSettings(){ // method to set dark mode for view pager item settings.
        if(sp1.getInt("reloadForDarkMode",0) == 1){
            mainViewPager.setCurrentItem(0,false);
        }if(sp1.getInt("reloadForNewCharacters",0) == 1){
            mainViewPager.setCurrentItem(0,false);
        }if(sp1.getInt("reloadForPurchaseCharacters",0) == 1){
            mainViewPager.setCurrentItem(8,false);
        }
    }
    public void restartMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent); // create a new Instances of Main Activity
        finish();
        editor.putInt("reloadForDarkMode",0);
        editor.putInt("reloadForNewCharacters",1);  // restart all Shared Prefs used within the app.
        editor.putInt("reloadForPurchaseCharacters",0);
        editor.apply();;
    }
    private void setUpSharedPres(){ // method that setups shared prefs
        sp1 = getApplicationContext().getSharedPreferences("key",0);
        editor = sp1.edit();
    }
    public int returnCurrentViewPagerItem(){
        return mainViewPager.getCurrentItem(); // return current view pager item.
    }
    private void checkDarkMode(){ // method to check if dark mode is enabled, and if it is change backgrounds from black or white.
        if(sp1.getInt("darkMode",0) == 1){
            relativeLayoutMain.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorBlack));
            mainBottomNavigationView.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorBlack));
            mTextViewToolBar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorWhite));
        }else{
            relativeLayoutMain.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorWhite));
            mainBottomNavigationView.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorWhite));
            mTextViewToolBar.setTextColor(Color.parseColor("#0000ff"));
        }
    }
    public void changeActivity(Intent intent){ // method to take in a new Activity and change it.
        startActivity(intent);
    }
    private void reloadFragment(Fragment fragment){ // helper method to reload fragment for current items
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
    }
    public void reloadFragmentAndSetViewPagerForSearch(String search){ // reload fragment for search
        Fragment fragment = mainAdapter.getFragment(mainViewPager.getCurrentItem());
        reloadFragment(fragment);
        putBool.putBoolean("searchBool",true);
        putBool.putString("searchString",search);
        fragment.setArguments(putBool);
        mainViewPager.setCurrentItem(1,false);
    }
    public void reloadFragmentViewYourCharacter(){ // reload fragment for viewchracter
        Fragment fragment = mainAdapter.getFragment(6);
        reloadFragment(fragment);
        mainViewPager.setCurrentItem(6,false);
    }
    public void setViewPagerForPurchaseCharacters(){
        mainViewPager.setCurrentItem(8,false);
    }
    private void setColorStateList(BottomNavigationView bottomNavigationView){ // set color state list for bottom nav
        ColorStateList ColorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(ColorStateList);
        bottomNavigationView.setItemIconTintList(ColorStateList);
    }
    private void setBottomColorFirst(BottomNavigationView bottomNavigationView)
    {
        colors = new int[]{
                Color.BLUE,
                Color.GRAY,
                Color.GRAY
        };

        states = new int[][]{
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked}
        };
        setColorStateList(bottomNavigationView);
    }
    private void setBottomColorSecond(BottomNavigationView bottomNavigationView)
    {
        colors = new int[]{
                Color.GRAY,
                Color.BLUE,
                Color.GRAY
        };
        states = new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked}
        };
        setColorStateList(bottomNavigationView);
    }
    private void setBottomColorThird(BottomNavigationView bottomNavigationView)
    {
        colors = new int[]{
                Color.GRAY,
                Color.GRAY,
                Color.BLUE
        };
        states = new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}
        };
        setColorStateList(bottomNavigationView);
    }
    private void setUpIds(){ // set up all ids for all  items used in Activity
        mainActivityToolBar = findViewById(R.id.toolbarMainActivity);
        mTextViewToolBar = findViewById(R.id.toolBarTitle);
        mainBottomNavigationView = findViewById(R.id.bottom_navigation);
        relativeLayoutMain = findViewById(R.id.relativeLayoutMain);
        mainViewPager = findViewById(R.id.viewpager_id);
        setSupportActionBar(mainActivityToolBar);
        mainAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainAdapter);
        mainViewPager.setOffscreenPageLimit(10);
        setBottomColorFirst(mainBottomNavigationView);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void switchToVs(List<Id> idList, int pos){
        Fragment fragment = mainAdapter.getFragment(4);
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        List<Id> newList = new ArrayList<>();
        newList.add(idList.get(pos));
        vsBundle.putParcelableArrayList("vsList", (ArrayList<? extends Parcelable>) newList);
        fragment.setArguments(vsBundle);
        mainViewPager.setCurrentItem(4,false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        mTextViewToolBar.setVisibility(View.VISIBLE);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void switchToTac(List<Id> idList, int pos){ // setting up fragment to switch to tac view.
        Fragment fragment = mainAdapter.getFragment(9);
        reloadFragment(fragment);
        List<Id> newList = new ArrayList<>();
        newList.add(idList.get(pos));
        vsBundle.putParcelableArrayList("vsListTac", (ArrayList<? extends Parcelable>) newList);
        fragment.setArguments(vsBundle);
        mainViewPager.setCurrentItem(9,false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        mTextViewToolBar.setVisibility(View.VISIBLE);
    }
    private void setUpToolBar(){
        Typeface logoTypeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"AlegreyaSC-Regular.otf");
        mTextViewToolBar.setTypeface(logoTypeFace);
        mTextViewToolBar.setTextColor(Color.parseColor("#0000ff"));
        String toolBarTitle = "SUPER HEROES AND VILLAIN'S";
        mTextViewToolBar.setText(toolBarTitle);
        setUpToolBarBackStack();
    }
    private void setUpToolBarBackStack(){
        mainActivityToolBar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                backStack();
            }
        });
    }
    public void changeToStatsView(List<Id> list,int i,Boolean searchBool){
        Fragment fragment = mainAdapter.getFragment(3);
        assert fragment.getFragmentManager() != null;
        List<Id> newList = new ArrayList<>();
        newList.add(list.get(i));
        opponentBundle.putParcelableArrayList("newList", (ArrayList<? extends Parcelable>) newList);
        opponentBundle.putBoolean("searchBool",searchBool);
        fragment.setArguments(opponentBundle);
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(3,false);
    }
    public void setViewPagerHome(){  // setting bottom nav up with view color, and item
        setBottomColorFirst(mainBottomNavigationView);
       mainViewPager.setCurrentItem(0,false);
    }
    public void setBottomColor(){
        setBottomColorFirst(mainBottomNavigationView);
    }
    public void reloadForDarkMode(){ // reloading view for dark mode, that sets the new items.
        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        editor.putInt("reloadForDarkMode",1);
        editor.putInt("reloadForNewCharacters",0);
        editor.putInt("reloadForPurchaseCharacters",0);
        editor.apply();;
    }
    public void reloadSettings(){ // reload for settings view.
        mainViewPager.setCurrentItem(5,false);
        mTextViewToolBar.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }
    private void backStack(){ // back stack functionality.
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        if(mainViewPager.getCurrentItem() == 0){
            finish();
        }else if(mainViewPager.getCurrentItem() == 1){
            finish();
        }else if(mainViewPager.getCurrentItem() == 2){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 3){
            setBottomColorFirst(mainBottomNavigationView);
            mTextViewToolBar.setVisibility(View.VISIBLE);
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 4){
            mainViewPager.setCurrentItem(0,false);
        }else if(mainViewPager.getCurrentItem() == 5){
            finish();
        }else if(mainViewPager.getCurrentItem() == 6){
            mainViewPager.setCurrentItem(5,false);
        }else if(mainViewPager.getCurrentItem() == 7){
            mainViewPager.setCurrentItem(5,false);
        }else if(mainViewPager.getCurrentItem() == 8){
            mainViewPager.setCurrentItem(5,false);
        }
    }
    @Override
    public void onBackPressed()
    { // when user hits back button, sack func as back stack.
        backStack();
    }
    public void setBackStackVisible(){ // setting items as visible or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mTextViewToolBar.setVisibility(View.GONE);
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
    private void bottomNavigationViewListener(){ // func for switching between home, search, and settings .
        mainBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.homeAction:
                        mTextViewToolBar.setVisibility(View.VISIBLE);
                        setBottomColorFirst(mainBottomNavigationView);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        mainViewPager.setCurrentItem(0, false);

                        return true;

                    case R.id.searchAction:
                        mTextViewToolBar.setVisibility(View.VISIBLE);
                        setBottomColorSecond(mainBottomNavigationView);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        mainViewPager.setCurrentItem(1,false);
                        return true;

                    case R.id.settingsAction:
                        mTextViewToolBar.setVisibility(View.VISIBLE);
                        setBottomColorThird(mainBottomNavigationView);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowHomeEnabled(false);
                        mainViewPager.setCurrentItem(5,false);
                        return true;

                }
                return false;
            }
        });
    }
    public void callApiForNewCharacterAssign(int index){ // if a user whats to assign a new character, api method gets called to assign a new character based on local json.
            GetDataService apiCall = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
            final Call<Results> searchById = apiCall.getByResults("akabab/superhero-api/0.2.0/api//id/" + index  + ".json");
            searchById.enqueue(new Callback<Results>()
            {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response)
                {
                    if(response.isSuccessful()){
                        Results results;
                        results = response.body();
                        ArrayList<Results> resultsList = new ArrayList<>();
                        resultsList.add(results);
                        SearchName searchName = new SearchName();
                        searchName.setResults(resultsList);
                        HomeData.searchNameList = new ArrayList<>();
                        HomeData.searchNameList.add(searchName);
                    }
                }
                @Override
                public void onFailure(Call<Results> call, Throwable t)
                {
                    // failed to create instance of api call.
                }
            });
    }
}
