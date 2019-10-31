package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity
{
    private TextView mainText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        Main();
    }
    protected void Main(){
        setUpIDS();
        setUpTypeFace();
        setText();
        setUpSHaredPrefs();
        keepTrackOfTotalCounts();
        checkHandler();
    }
    private void setUpIDS(){
        mainText = findViewById(R.id.mainTextView);
    }
    private void setUpTypeFace(){
        Typeface typeFaceAmbleBold = Typeface.createFromAsset(getApplicationContext().getAssets(),"AlegreyaSC-Regular.otf");
        mainText.setTypeface(typeFaceAmbleBold);
    }
    private void setText(){
        mainText.setText("\nSUPER \nHEROES \nAND \nVILLAIN'S " );
    }
    @SuppressLint("CommitPrefEdits")
    private void setUpSHaredPrefs(){
        sharedPreferences= getApplicationContext().getSharedPreferences("key",0);
        editor = sharedPreferences.edit();
    }
    private void keepTrackOfTotalCounts(){
        int totalCount = sharedPreferences.getInt("totalCount", 0);
        totalCount++;
        editor.putInt("totalCount", totalCount);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heightOfDevice = displayMetrics.heightPixels;
        if(heightOfDevice >= 1920){
            editor.putInt("on-board-width",1);
        }else{
            editor.putInt("on-board-width",2);
        }
        editor.putInt("reloadForDarkMode",0);
        editor.putInt("reloadForNewCharacters",0);
        editor.putInt("reloadForPurchaseCharacters",0);
    }
    private void checkHandler(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            Intent intent;
            @Override
            public void run()
            {
             if(sharedPreferences.getInt("totalCount",0) == 0){
                 editor.putInt("darkMode",0);
                 intent = new Intent(getApplicationContext(),OnBoardingActivity.class);
                 startActivity(intent);
             }else{
                 intent = new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(intent);
             }
             editor.apply();
             finish();
            }
        },3400);
    }
}
