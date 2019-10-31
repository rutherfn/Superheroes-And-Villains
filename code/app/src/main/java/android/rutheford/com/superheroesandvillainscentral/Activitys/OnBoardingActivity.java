package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.OnBoardAdapter;
import android.rutheford.com.superheroesandvillainscentral.Models.OnBoard;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NickR.
 */

public class OnBoardingActivity extends AppCompatActivity
{
    // declarations
    private SharedPreferences sp;
    private ViewPager screenViewPager;
    private TabLayout tabLayout;
    private Button buttonView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_main);
        Main();
    }
    protected void Main(){ // main protected method, that setups all of my other methods.
        setUpSharedPrefs();
        setUpIds();
        checkScreenPager();
        setUpListAndAdapter();
        buttonContinueView();
      //  displayAlertForPaul();
    }
    private void setUpSharedPrefs(){
        sp = getApplicationContext().getSharedPreferences("key", 0); //  set shared prefs
    }
    private void setUpIds(){ // sets up all of the ids for my class.
        screenViewPager = findViewById(R.id.screen_viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        buttonView = findViewById(R.id.button);
        Typeface typefaceLogo = Typeface.createFromAsset(getApplicationContext().getAssets(),"AlegreyaSC-Regular.otf");
        buttonView.setTypeface(typefaceLogo);
    }
    private void buttonContinueView(){ // when the button gets clicked, look at current view pager item, and set set item to the next one in the view page.
        buttonView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleSwitchForViewPagerItem();
            }
        });
    }
    private void handleSwitchForViewPagerItem(){ // switch statement to change view pager current item.
        switch (screenViewPager.getCurrentItem()){
            case 0:
                screenViewPager.setCurrentItem(1);
                break;
            case 1:
                screenViewPager.setCurrentItem(2);
                break;
            case 2:
                screenViewPager.setCurrentItem(3);
                break;
            case 3:
                screenViewPager.setCurrentItem(4);
                break;
            case 4:
                startNewActivity();
                break;
            default:
                screenViewPager.setCurrentItem(0);
                break;
        }
    }
    private void setButtonTextBasedOnViewPager(){ // if the view pager is not equal to the last item, set button to continue else; set button text to get started.
        if(screenViewPager.getCurrentItem() == 0 || screenViewPager.getCurrentItem() == 1 || screenViewPager.getCurrentItem() == 2
        || screenViewPager.getCurrentItem() == 3){
            String continueText = "Continue";
            buttonView.setText(continueText);
        }else{
            String getStartedText = "Get Started";
            buttonView.setText(getStartedText);
        }
    }
    private void checkScreenPager(){ // add on page listener for scrolling.
        screenViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {
                setButtonTextBasedOnViewPager();
            }

            @Override
            public void onPageSelected(int i)
            {

            }

            @Override
            public void onPageScrollStateChanged(int i)
            {

            }
        });
    }
    private void startNewActivity(){ // start new activity, once finished
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            finish();
            startActivity(intent);
        }
    private void displayAlertForPaul(){
        if(sp.getInt("totalCount",0) == 1){
            alertForPaulBlartFirstTimeUser();
        }
    }
    private void alertForPaulBlartFirstTimeUser(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
        alertDialog.setTitle("Welcome To SUPER HEROES AND VILLAIN'S!");
        alertDialog.setMessage("Hello First Time User! Your character assigned to you is Paul Blart, the weakest character of all the hero's and villain's. Fight characters, in order to unlock new characters! Head Over To Your Character in settings, to review details! ");
        alertDialog.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
    private void setUpListAndAdapter(){
        // fill list screen, with the adapter class and size.
        List<OnBoard> mList = new ArrayList<>(); // adding data to list.
            mList.add(new OnBoard("WELCOME TO","SUPER HEROES AND VILLAIN'S","Here you will be able to battle characters, unlock new characters, and view information of Superheroes and Villains.You will start by receiving a character." ,R.drawable.onboardimageone));
            mList.add(new OnBoard("View","Character Stats","Once you have received a character, you are then ready to battle! Make sure to view your character stats in the settings menu, in order to know what your character is truly capable of! ",R.drawable.onboardimagetwo));
            mList.add(new OnBoard("Simulation Mode","Will Your Character Win?","Once you are ready, go into battle with other characters with a Simulation experience! Simply head into battle, where your character stats will determine the winner!",R.drawable.simvsrealbattle));
            mList.add(new OnBoard("Earn XP","For Rewards","Battle other characters in order to earn XP. You will then be able to use that XP, to purchase new characters for you to use on the app! Do you have what it takes to unlock all of them?",R.drawable.coinbag));
            mList.add(new OnBoard("View","Settings", "Please visit the setting's screen to replay on boarding experience, change to dark mode, and more! Without further a do, Prepare for battle!", R.drawable.onboardimagethree));
        OnBoardAdapter onBoardAdapter = new OnBoardAdapter(this, mList);
        tabLayout.setupWithViewPager(screenViewPager,true);
        screenViewPager.setAdapter(onBoardAdapter); // setting view pager up with list item.
    }
}
