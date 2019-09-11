package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Id> idHome;
    private Context mContext;

    public HomeView(List<Id> idHome, Context mContext)
    {
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
    {
        HomeViewScreen homeViewScreen = (HomeViewScreen) holder;
        homeViewScreen.setTypeFace();
        homeViewScreen.loadPlaceHolderIcon(i);
        homeViewScreen.loadMainTextContent(i);
        homeViewScreen.changeToStatsPage(i);
        homeViewScreen.alertUser();
        homeViewScreen.checkForDarkMode();
    }


    @Override
    public int getItemCount()
    {
        return idHome.size();
    }
    class HomeViewScreen extends RecyclerView.ViewHolder{
        CircleImageView mainCircleImageView;
        TextView mainTextContent;
        ImageView aboutImageView;

        public HomeViewScreen(@NonNull View itemView)
        {
            super(itemView);
            mainCircleImageView = itemView.findViewById(R.id.mainCircleImageView);
            mainTextContent = itemView.findViewById(R.id.mainTitle);
            aboutImageView = itemView.findViewById(R.id.imageView3);
        }
        private void checkForDarkMode(){
            SharedPreferences sp1 = mContext.getSharedPreferences("key", 0);
            if(sp1.getInt("darkMode",0) == 1){
                mainTextContent.setBackgroundColor(mContext.getResources().getColor(R.color.colorWwhite));
            }
        }
        private void loadPlaceHolderIcon(int position){
            Picasso.get().load(idHome.get(position).getImage().getMd()).into(mainCircleImageView);
        }
        private void setTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainTextContent.setTypeface(mainTextTypeFace);
        }
        private void alertUser(){
            aboutImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                    alertDialog.setTitle("Time To Fight");
                    alertDialog.setMessage("Welcome to Superheroes And Villains Central! Random Superheros and Villians have been called to challenge your character in a fight! Click on the Superhero or Villain name or picture to battle, view their stats, or even make the character your own! ");
                    alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }
            });
        }
        private void changeToStatsPage(final int i ){
            mainCircleImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setArrayListForStatsPage(i);
                }
            });
            mainTextContent.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setArrayListForStatsPage(i);
                }
            });
        }
        @SuppressLint("SetTextI18n")
        private void loadMainTextContent(int position){
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
        private void setArrayListForStatsPage(int i){
            HomeData.opponentId = new ArrayList<>();
            HomeData.opponentId.add(idHome.get(i));
            ((MainActivity)mContext).changeToStatsView();
            ((MainActivity)mContext).setBackStackVisible();
        }

    }
}
