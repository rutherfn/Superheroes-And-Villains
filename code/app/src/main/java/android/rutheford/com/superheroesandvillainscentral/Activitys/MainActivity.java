package android.rutheford.com.superheroesandvillainscentral.Activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
{
    private Toolbar mainActivityToolBar;
    private ImageView settingsImageView;
    private TextView mTextViewToolBar;
    private String toolBarTitle = "SUPER HEROES AND VILLAINS CENTRAL";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main();
    }
    protected  void Main(){
        setUpIDS();
        setUpToolBar();
    }
    private void setUpIDS(){
        mainActivityToolBar = findViewById(R.id.toolbarMainActivity);
        mTextViewToolBar = findViewById(R.id.toolBarTitle);
        settingsImageView = findViewById(R.id.settingsImageView);
        setSupportActionBar(mainActivityToolBar);
    }
    private void setUpToolBar(){
        Typeface logoTypeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"AlegreyaSC-Regular.otf");
        mTextViewToolBar.setTypeface(logoTypeFace);
        mTextViewToolBar.setTextColor(Color.parseColor("#0000ff"));
        mTextViewToolBar.setText(toolBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
