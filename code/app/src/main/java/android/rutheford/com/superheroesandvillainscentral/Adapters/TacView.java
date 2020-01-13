package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

/**
 * Created by Nick R.
 */

public class TacView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private Context mContext;
    private List<Id> listId;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int oppHealth = 200;
    private int userHealth = 200;
    private int currentRoundValue = 0;
    private int randomNumberForAttackLost;
    private int specialAttackForUserLost;
    private String secondaryTitle;
    private String secondaryDesc;

    public TacView(Context mContext, List<Id> listId)
    { // constructor for context and array list.
        this.mContext = mContext;
        this.listId = listId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tatical_screen_main, viewGroup, false);
        return new TacViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i)
    {
        TacViewHolder tacViewHolder = (TacViewHolder) viewHolder;
        tacViewHolder.Main();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }

    class TacViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mainUserSuperHeroOrVillain, mainUserSuperHeroOrVillain2;
        private TextView mainUserTextViewcurrentHealth, mainUserTextViewCurrentHealth2, currentRound;
        private Button attackUserButton, defendUserButton, restoreUserButton, specialUserButton;

        public TacViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mainUserSuperHeroOrVillain = itemView.findViewById(R.id.mainUserSuperHeroOrVillain);
            mainUserSuperHeroOrVillain2 = itemView.findViewById(R.id.mainUserSuperHeroOrVillain2);
            mainUserTextViewcurrentHealth = itemView.findViewById(R.id.mainUserTextViewcurrentHealth);
            mainUserTextViewCurrentHealth2 = itemView.findViewById(R.id.mainUserTextViewcurrentHealth2);
            currentRound = itemView.findViewById(R.id.currentRound);
            attackUserButton = itemView.findViewById(R.id.attackUserButton);
            defendUserButton = itemView.findViewById(R.id.defendUserButton);
            restoreUserButton = itemView.findViewById(R.id.restoreUserButton);
            specialUserButton = itemView.findViewById(R.id.specialUserButton);
        }
        protected void Main()
        {
            setUpSharedPrefs();
            setUpTypeFace();
            checkDarkMode();
            setImageContent();
            setDataContent();
            attackUserButton();
            defendUserButton();
            restoreUserButton();
            specialUserButton();
        }
        private void setUpSharedPrefs(){ // set up shared prefs
            sp = mContext.getSharedPreferences("key", 0);
            editor = sp.edit();
        }
        private void setUpTypeFace()
        { // set up type face for fields.
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            Typeface body = Typeface.createFromAsset(mContext.getAssets(),"OpenSans-Regular.ttf");
            mainUserTextViewcurrentHealth.setTypeface(mainTextTypeFace);
            mainUserTextViewCurrentHealth2.setTypeface(mainTextTypeFace);
            currentRound.setTypeface(mainTextTypeFace);
            attackUserButton.setTypeface(body);
            defendUserButton.setTypeface(body);
            restoreUserButton.setTypeface(body);
            specialUserButton.setTypeface(body);
        }
        private void checkDarkMode(){ // check in for dark mode for fields.
            if(sp.getInt("darkMode",0) == 1){
                mainUserTextViewcurrentHealth.setTextColor(Color.parseColor("#FFFFFF"));
                mainUserTextViewCurrentHealth2.setTextColor(Color.parseColor("#FFFFFF"));
                currentRound.setTextColor(Color.parseColor("#FFFFFF"));
                attackUserButton.setTextColor(Color.parseColor("#FFFFFF"));
                defendUserButton.setTextColor(Color.parseColor("#FFFFFF"));
                restoreUserButton.setTextColor(Color.parseColor("#FFFFFF"));
                specialUserButton.setTextColor(Color.parseColor("#FFFFFF"));
                attackUserButton.setTextColor(Color.parseColor("#000000"));
                defendUserButton.setTextColor(Color.parseColor("#000000"));
                restoreUserButton.setTextColor(Color.parseColor("#000000"));
                specialUserButton.setTextColor(Color.parseColor("#000000"));
                setContentColor(mainUserTextViewcurrentHealth,"#006400");
            }else{
                setCharacterColor();
            }
        }
        private void setContentColor(TextView currentHealth, String value){ // setting up content color for dark mode.
            if(sp.getInt("darkMode",0) != 1)
            {
                currentHealth.setTextColor(Color.parseColor(value));
                currentRound.setTextColor(Color.parseColor(value));
            }
        }
        private void setBackgroundColor(String value){
            attackUserButton.setBackgroundColor(Color.parseColor(value));
            defendUserButton.setBackgroundColor(Color.parseColor(value));
            restoreUserButton.setBackgroundColor(Color.parseColor(value));
            specialUserButton.setBackgroundColor(Color.parseColor(value));
        }
        private void setCharacterColor(){ // setting up character color.
            attackUserButton.setTextColor(Color.parseColor("#FFFFFF"));
            defendUserButton.setTextColor(Color.parseColor("#FFFFFF"));
            restoreUserButton.setTextColor(Color.parseColor("#FFFFFF"));
            specialUserButton.setTextColor(Color.parseColor("#FFFFFF"));
            if(HomeData.searchNameList != null){
                if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getAlignment().equals("good")){
                    setContentColor(mainUserTextViewcurrentHealth,"#0000ff");
                    setBackgroundColor("#0000ff");
                }else if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getAlignment().equals("bad")){
                    setContentColor(mainUserTextViewcurrentHealth,"#B22222");
                    setBackgroundColor("#0000ff");
                }else{
                    setContentColor(mainUserTextViewcurrentHealth,"#0000ff");
                    setBackgroundColor("#0000ff");
                }
            }
            if(listId != null){
                if(listId.get(0).getBiography().getAlignment().equals("good")){
                    setContentColor(mainUserTextViewCurrentHealth2,"#0000ff");
                }else if(listId.get(0).getBiography().getAlignment().equals("bad")){
                    setContentColor(mainUserTextViewCurrentHealth2,"#B22222");
                }else{
                    setContentColor(mainUserTextViewCurrentHealth2,"#0000ff");
                }
            }
        }
        private void setImageContent()
        { // setting up image for each character, with a animation.
            if (HomeData.searchNameList != null)
            {
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
            }
            if (listId != null)
            {
                Picasso.get().load(listId.get(0).getImage().getMd()).into(mainUserSuperHeroOrVillain2, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        MaterialImageLoading.animate(mainUserSuperHeroOrVillain2).setDuration(4000).start();
                    }

                    @Override
                    public void onError(Exception e)
                    {

                    }
                });
            }
        }
        @SuppressLint("SetTextI18n")
        private void setDataContent(){ // setting data content for each score updated.
            if(HomeData.searchNameList != null){
                mainUserTextViewcurrentHealth.setText(HomeData.searchNameList.get(0).getResults().get(0).getName() + " \t\nCurrent Health: " + userHealth);
            }
            if(listId != null){
                mainUserTextViewCurrentHealth2.setText(listId.get(0).getName() +  " \t\nCurrent Health: " + oppHealth );
            }
            currentRound.setText("Current Round: " + currentRoundValue);
        }
        private void attackUserButton(){ // attack for user, functionality.
            attackUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    attackCommandForUser();
                    oppHealth = oppHealth - randomNumberForAttackLost;
                    setDataContent();
                    alertDialogForUpdateContent("You Attacked " + listId.get(0).getName(),HomeData.searchNameList.get(0).getResults().get(0).getName() + " has attacked " + listId.get(0).getName() + "." + listId.get(0).getName() + " has lost " + randomNumberForAttackLost + " points for health.");

                }
            });
        }
        private void defendUserButton(){ // defend for user, functionality.
            defendUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        private void restoreUserButton(){ // restore health functionality.
            restoreUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        private void specialUserButton(){ // special for user functionality.
            specialUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    specialCommandForUser();
                    oppHealth = oppHealth - specialAttackForUserLost;
                    setDataContent();
                }
            });
        }
        private void checkIfComputerLoses(){ // check if computer loses.
            if(oppHealth <= 0){
                // opp loses
            }
        }
        private void checkIfUserLoses(){ // check if user loses
            if(userHealth <= 0){
                // user loses
            }
        }
        private void resetEverything(){ // reset everything back to normal.

        }
        private void checkStatsForCharacterAndSetData(int statsOfCharacter){ // check stats of character for user attack.
            if(statsOfCharacter <= 300){
                commandForLost(10,30);
            }else if(statsOfCharacter <= 600){
                commandForLost(50,600);
            }else if(statsOfCharacter >= 601){
                commandForLost(60,100);
            }
        }
        private void attackCommandForUser(){
            checkStatsForCharacterAndSetData(totalStatsForUser());
        }
        private void attackCommandForComputer(){
            checkStatsForCharacterAndSetData(totalStatsForComputer());
        }
        private int totalStatsForUser(){
            return HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getIntelligence() + HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getStrength() +
                    HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getSpeed() + HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getDurability() +
                    HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getPower() + HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getCombat();
        }
        private int totalStatsForComputer(){
            return listId.get(0).getPowerStats().getIntelligence() + listId.get(0).getPowerStats().getSpeed() + listId.get(0).getPowerStats().getPower() + listId.get(0).getPowerStats().getStrength() +
                    listId.get(0).getPowerStats().getDurability() + listId.get(0).getPowerStats().getCombat();
        }
        private void specialCommandForUser(){
            commandForLost(50,110);
        }
        private void commandForLost(int low, int high){ // command for user lost, following between two random numbers.
            Random r = new Random();
            randomNumberForAttackLost = r.nextInt(high-low) + low;
            specialAttackForUserLost = r.nextInt(high-low) + low;
        }
        private void alertDialogForUpdateContent(String title, String body){ // alert dialog for updates on each characters.
            final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle(title);
            alert.setMessage(body);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                    final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setTitle(secondaryTitle);
                    alert.setMessage(secondaryDesc);
                    alert.setNegativeButton("Save Us", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();

                        }

                    });
                    AlertDialog dialogs = alert.create();
                    dialogs.show();

                }

            });
            AlertDialog dialogs = alert.create();
            dialogs.show();
        }
    }
}

