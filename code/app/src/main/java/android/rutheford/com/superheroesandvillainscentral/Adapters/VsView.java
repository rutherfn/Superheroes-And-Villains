package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
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

import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nick R.
 */

public class VsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private int score = 0;
    private Context mContext;
    private int totalScore;
    private List<Id> listId;
    private List<Integer> userScores = new ArrayList<>();
    private SharedPreferences sp1;
    private SharedPreferences.Editor editor;

    public VsView(Context mContext,List<Id> listId)
    { // constructor for context and list.
        this.mContext = mContext;
        this.listId = listId;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vs_screen_main,viewGroup,false);
        return new VsItemView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        VsItemView vsItemView = (VsItemView) holder;
        vsItemView.Main();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class VsItemView extends RecyclerView.ViewHolder{
        ImageView mainUserSuperHeroOrVillain, opposingMainImageView,vsImageView;
        TextView mainUserText, opposingUserText;

        public VsItemView(@NonNull View itemView)
        {
            super(itemView);
            mainUserSuperHeroOrVillain = itemView.findViewById(R.id.mainUserSuperHeroOrVillain);
            opposingMainImageView = itemView.findViewById(R.id.opposingMainImageView);
            mainUserText = itemView.findViewById(R.id.mainUserTextView);
            opposingUserText = itemView.findViewById(R.id.mainOpposingTextView);
            vsImageView = itemView.findViewById(R.id.vsImageView);
        }
        private void Main(){
            setUpSharedPrefs();
            setUpTypeFace();
            setUpColor();
            loadMainUserSuperHeroOrVillain();
            loadOpposingSuperHeroOrVillain();
            generateWhoWon();
            checkForDarkMode();
        }
        private void checkForDarkMode(){
            if(sp1.getInt("darkMode",0) == 1){
                mainUserText.setTextColor(Color.parseColor("#FFFFFF"));
                opposingUserText.setTextColor(Color.parseColor("#FFFFFF"));
                vsImageView.setVisibility(View.INVISIBLE);
            }
        }
        private void setUpSharedPrefs(){
            sp1 = mContext.getSharedPreferences("key", 0);
            editor = sp1.edit();
        }
        private void generateWhoWon(){ // looks to see who won battle. s
            if(listId != null && HomeData.searchNameList != null)
            {

                generateScore( listId.get(0).getPowerStats().getIntelligence(), listId.get(0).getPowerStats().getStrength(),
                        listId.get(0).getPowerStats().getSpeed(), listId.get(0).getPowerStats().getDurability(), listId.get(0).getPowerStats().getPower(), listId.get(0).getPowerStats().getCombat());

                generateScore(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getIntelligence(), HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getStrength(),
                        HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getSpeed(), HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getDurability(), HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getPower(),
                        HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getCombat());
                displayWhoWon();
            }
        }
        private void displayWhoWon(){ // displays to the user who won.
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    chooseWinner();
                }
            },2000);
        }
        private void loadMainUserSuperHeroOrVillain(){
            if(HomeData.searchNameList != null){
                Picasso.get().load(HomeData.searchNameList.get(0).getResults().get(0).getImage().getMd()).into(mainUserSuperHeroOrVillain, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        MaterialImageLoading.animate(mainUserSuperHeroOrVillain).setDuration(2000).start();
                    }

                    @Override
                    public void onError(Exception e)
                    {

                    }
                });
                mainUserText.setText(HomeData.searchNameList.get(0).getResults().get(0).getName());
            }
        }
        private void setUpColor(){
            mainUserText.setTextColor(Color.parseColor("#0000ff"));
            opposingUserText.setTextColor(Color.parseColor("#0000ff"));
        }
        private void setUpTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainUserText.setTypeface(mainTextTypeFace);
            opposingUserText.setTypeface(mainTextTypeFace);
        }
        private void loadOpposingSuperHeroOrVillain(){
            if(listId != null){
                Picasso.get().load(listId.get(0).getImage().getMd()).into(opposingMainImageView, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        MaterialImageLoading.animate(opposingMainImageView).setDuration(4000).start();
                    }

                    @Override
                    public void onError(Exception e)
                    {

                    }
                });
                opposingUserText.setText(listId.get(0).getName());
            }
        }
        private void generateScore(int intelligence, int strength, int speed, int durability, int power, int combat){ // generate score for user.
            Random r = new Random();
            int low = 1;
            int high = 150;
            int result = r.nextInt(high-low) + low;
                totalScore = intelligence + strength + speed + durability + power + combat + result;
                userScores.add(totalScore);
        }
        private void chooseWinner(){ // alert to choose winner for sim.
            final SharedPreferences sp1 = mContext.getSharedPreferences("key", 0);
            SharedPreferences.Editor editor1 = sp1.edit();
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Battle Results");
            if (userScores.get(1) > userScores.get(0))
            {
                score = 150;
                editor1.putInt("wins", sp1.getInt("wins",0) + 1);
                editor1.putInt("xp",sp1.getInt("xp",0) + 150); // when user wins they get 60 xp
                editor1.apply();
                alertDialog.setMessage("You Won This Battle! Do you want to see how? Press YES to view full results!");
            } else if (userScores.get(0) > userScores.get(1))
            {
                score = 80;
                editor1.putInt("loses", + sp1.getInt("loses",0) + 1);
                editor1.putInt("xp",sp1.getInt("xp",0) + 80); // when user loses they get 30 xp
                editor1.apply();
                alertDialog.setMessage("Computer won this battle! Do you want to see how? Press YES to view full results!");
            } else
            {
                score = 100;
                editor1.putInt("ties", + sp1.getInt("ties",0) + 1);
                editor1.putInt("xp",sp1.getInt("xp",0) + 100); // when user ties they get 40 xp
                editor1.apply();
                alertDialog.setMessage("We have a tie! Do you want to see how? Press YES to view full results!");
            }
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                    ((MainActivity)mContext).setViewPagerHome();
                    ((MainActivity)mContext).setBottomColor();
                }
            });
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                    final AlertDialog.Builder alertDialog= new
                            AlertDialog.Builder(mContext);
                    alertDialog.setTitle("Battle Stats");
                    alertDialog.setMessage("Your Character:  " +  mainUserText.getText().toString() + "\nFinal Score:  "  +  userScores.get(1) + "\n\nYour Enemy:  " + opposingUserText.getText().toString() +
                            "\nFinal Score:  "+ userScores.get(0) +  "\n\nYour XP Earned:  " + score + "\n\nTotal XP Earned:  " + sp1.getInt("xp",0));
                    alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                            ((MainActivity)mContext).setViewPagerHome();
                        }
                    });
                    alertDialog.show();
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.show();
            }

    }
}
