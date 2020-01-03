package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nick R.
 */

public class HomeView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private List<Id> idHome;
    private Context mContext;
    private SharedPreferences sp1;

    public HomeView(List<Id> idHome, Context mContext)
    { // constructor
        this.idHome = idHome;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen,viewGroup,false);
        return new HomeViewScreen(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    { // calling main method from the home view.
        HomeViewScreen homeViewScreen = (HomeViewScreen) holder;
        homeViewScreen.Main(i);
    }


    @Override
    public int getItemCount()
    {
        return idHome.size();
    }
    class HomeViewScreen extends RecyclerView.ViewHolder{
        ConstraintLayout mainScreenCons;
        CircleImageView mainCircleImageView;
        TextView mainTextContent;

        HomeViewScreen(@NonNull View itemView)
        {
            super(itemView);
            mainScreenCons = itemView.findViewById(R.id.mainScreenCons);
            mainCircleImageView = itemView.findViewById(R.id.mainCircleImageView);
            mainTextContent = itemView.findViewById(R.id.mainTitle);
        }
        protected void Main(int i){
            setUpSharedPrefs();
            setTypeFace();
            loadIcon(i);
            loadMainTextContent(i);
            changeToStatsPage(i);
            checkForDarkMode();
        }
        private void setUpSharedPrefs(){ // set up shared prefs
            sp1 = mContext.getSharedPreferences("key", 0);
        }
        private void checkForDarkMode(){ // check for dark mode, if dark mode is enabled set text color
            if(sp1.getInt("darkMode",0) == 1){
                mainTextContent.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            }
        }
        private void loadIcon(int position){ // load ImageView From Data Set
            Picasso.get().load(idHome.get(position).getImage().getMd()).into(mainCircleImageView);
        }
        private void setTypeFace(){ // set up type face from type face helper method
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainTextContent.setTypeface(mainTextTypeFace);
        }
        private void changeToStatsPage(final int i ){ // if the user clicks on any part of the cons layout, read in the index and take user to stats page.
            mainScreenCons.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setArrayListForStatsPage(i,idHome);
                }
            });
        }
        @SuppressLint("SetTextI18n")
        private void loadMainTextContent(int position){ // set text and color based of there alignment stats.
            if(idHome.get(position).getBiography().getAlignment().equals("good")){
                mainTextContent.setTextColor(Color.parseColor("#006400"));
                mainTextContent.setText(idHome.get(position).getName() + ", Good");
            }else if(idHome.get(position).getBiography().getAlignment().equals("bad")){
                mainTextContent.setTextColor(Color.parseColor("#B22222"));
                mainTextContent.setText(idHome.get(position).getName() + ", Bad");
            }else
            {
                mainTextContent.setText(idHome.get(position).getName() + ", Neutral");
            }
        }
        private void setArrayListForStatsPage(int i,List<Id> list){ // change to stats page.
            ((MainActivity)mContext).changeToStatsView(list,i,false);
            ((MainActivity)mContext).setBackStackVisible();
        }

    }
}
