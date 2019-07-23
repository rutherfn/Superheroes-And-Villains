package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.OnBoardAdapter;
import android.rutheford.com.superheroesandvillainscentral.Models.OnBoard;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity
{
    private ConstraintLayout onbrdLayout;
    private ViewPager screenViewPager;
    private TabLayout tabLayout;
    private Button buttonView;
    private OnBoardAdapter onBoardAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_main);
        Main();
    }
    protected void Main(){
        setUpIds();
        checkScreenPager();
        setUpListAndAdapter();
        goToMain();
    }
    private void setUpIds(){
        onbrdLayout = findViewById(R.id.onbrdLayout);
        screenViewPager = findViewById(R.id.screen_viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        buttonView = findViewById(R.id.button);
    }
    private void goToMain(){
        buttonView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(screenViewPager.getCurrentItem() == 0){
                    screenViewPager.setCurrentItem(1);
                }else if(screenViewPager.getCurrentItem() == 1){
                    screenViewPager.setCurrentItem(2);
                }
                else if(screenViewPager.getCurrentItem() == 2){
                    startNewActivity();
                }
            }
        });
    }
    private void checkScreenPager(){
        screenViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {
                if(screenViewPager.getCurrentItem() == 0){
                    buttonView.setText("Continue");
                }else if(screenViewPager.getCurrentItem() == 1){
                    buttonView.setText("Continue");
                }else{
                    buttonView.setText("Get Started");
                }
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
    private void startNewActivity(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        finish();
        startActivity(intent);
    }
    private void setUpListAndAdapter(){
        // fill list screen
        List<OnBoard> mList = new ArrayList<>();
            mList.add(new OnBoard("Welcome To Superheroes And Villians Central","Welcome To Super Heroes And Villians,\n" +
                    "please feel free to explore the app by searching for Super Heroes And Villians.",R.drawable.onboardimageone));
            mList.add(new OnBoard("View Character Stats","Once you select a character, you will be able to then view the character stats. Once you view the stats, that's where the real fun begins!",R.drawable.onboardimagetwo));
            mList.add(new OnBoard("Prepare For Battle", "From there make sure to prepare for battle, as you\n" +
                    "will do battle with your favorite superhero! Last man superhero or villain standing, will win!", R.drawable.onboardimagethree));
        onBoardAdapter = new OnBoardAdapter(this, mList);
        tabLayout.setupWithViewPager(screenViewPager,true);
        screenViewPager.setAdapter(onBoardAdapter);
    }
}
