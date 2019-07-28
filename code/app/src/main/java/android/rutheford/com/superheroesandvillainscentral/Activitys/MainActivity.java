package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.ViewPagerAdapter;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private ViewPager mainViewPager;
    private ViewPagerAdapter mainAdapter;
    private Toolbar mainActivityToolBar;
    private ImageView settingsImageView;
    private TextView mTextViewToolBar;
    private BottomNavigationView mainBottomNavigationView;
    private String toolBarTitle = "SUPER HEROES AND VILLAINS CENTRAL";
    private Id idSearch = new Id();
    public  Id idObject = new Id();

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

    public void reloadFragmentAndSetViewPagerForSearch(){
        Fragment fragment = mainAdapter.getFragment(mainViewPager.getCurrentItem());
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(1,false);
    }
    private void setUpIds(){
        mainActivityToolBar = findViewById(R.id.toolbarMainActivity);
        mTextViewToolBar = findViewById(R.id.toolBarTitle);
        settingsImageView = findViewById(R.id.settingsImageView);
        mainBottomNavigationView = findViewById(R.id.bottom_navigation);
        mainViewPager = findViewById(R.id.viewpager_id);
        setSupportActionBar(mainActivityToolBar);
        mainAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainAdapter);
        mainViewPager.setOffscreenPageLimit(5);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void changeToStatsView(){
        Fragment fragment = mainAdapter.getFragment(3);
        assert fragment.getFragmentManager() != null;
        fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        mainViewPager.setCurrentItem(3,false);
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

    private void bottomNavigationViewListener(){
        mainBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.homeAction:
                        System.out.println("Search New");
                        mainViewPager.setCurrentItem(0, false);

                        return true;

                    case R.id.searchAction:
                        System.out.println("Search Again");
                        mainViewPager.setCurrentItem(1,false);
                        return true;

                    case R.id.battleAction:
                        mainViewPager.setCurrentItem(2,false);
                        return true;
                }
                return false;
            }
        });
    }

}
