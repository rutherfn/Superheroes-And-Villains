package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity
{
    private TextView mainText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int totalCount;
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
        mainText.setText("\nSUPER \nHEROES \nAND \nVILLIANS \nCENTRAL" );
    }
    @SuppressLint("CommitPrefEdits")
    private void setUpSHaredPrefs(){
        sharedPreferences= getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    private void keepTrackOfTotalCounts(){
        totalCount = sharedPreferences.getInt("totalCount",0);
        totalCount++;
        editor.putInt("totalCount",totalCount);
        editor.apply();
    }
    private void checkHandler(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            Intent intent;
            @Override
            public void run()
            {
             if(sharedPreferences.getInt("totalCount",0) == 1){
                 intent = new Intent(getApplicationContext(),OnBoardingActivity.class);
              //   finish();
                 startActivity(intent);
             }else{
                 intent = new Intent(getApplicationContext(),MainActivity.class);
               //  finish();
                 startActivity(intent);
             }
            }
        },3400);
    }
}
