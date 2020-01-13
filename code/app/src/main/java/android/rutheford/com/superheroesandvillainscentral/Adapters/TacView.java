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
    private int compChoice;
    private Context mContext;
    private List<Id> listId;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int oppHealth = 200;
    private int userHealth = 200;
    private int currentRoundValue = 0;
    private int randomNumberForAttackLost;
    private int specialAttack;
    private int randomNumberForRestoreAmount;
    private String secondaryTitle;
    private String secondaryDesc;
    private int computerChoice;

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
            editor.putBoolean("userSpecialUsed",false);
            editor.putBoolean("oppSpecialUsed",false);
            editor.apply();
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
            if(userHealth < 0){
                userHealth = 0;
            }
            if(oppHealth < 0){
                oppHealth = 0;
            }
            if(userHealth > 200){
                userHealth = 200;
            }
            if(oppHealth > 200){
                oppHealth = 200;
            }
            if(HomeData.searchNameList != null){
                mainUserTextViewcurrentHealth.setText(HomeData.searchNameList.get(0).getResults().get(0).getName() + " \t\nCurrent Health: " + userHealth);
            }
            if(listId != null){
                mainUserTextViewCurrentHealth2.setText(listId.get(0).getName() +  " \t\nCurrent Health: " + oppHealth );
            }
            currentRound.setText("Current Round: " + currentRoundValue);
        }
        private void decidedWhatComputerShouldDo(){
            if(oppHealth <= 30){
                compChoice = 1;
            }
            if(userHealth <= 100){
                if(!sp.getBoolean("oppSpecialUsed",false)){
                    compChoice = 0;
                }else
                {
                    compChoice = 2;
                }
            }
            else if(sp.getInt("userChoice",0) == 0){
                if(oppHealth == 200){
                    if(!sp.getBoolean("oppSpecialUsed",false)){
                        commandForRandomChoiceComputer(2,4);
                    }else{
                        commandForRandomChoiceComputer(2,3);
                    }
                }else{
                    commandForRandomChoiceComputer(1,3);
                }
            }else if(sp.getInt("userChoice",0) == 1){
                if(oppHealth == 200){
                    if(!sp.getBoolean("oppSpecialUsed",false)){
                        commandForRandomChoiceComputer(2,4);
                    }else{
                        commandForRandomChoiceComputer(2,3);
                    }
                }else
                {
                    if (!sp.getBoolean("oppSpecialUsed", false))
                    {
                        commandForRandomChoiceComputer(1, 4);
                    } else
                    {
                        commandForRandomChoiceComputer(1, 3);
                    }
                }
            }else if(sp.getInt("userChoice",0) == 2){
                if(sp.getBoolean("oppSpecialUsed", false)){
                    commandForRandomChoiceComputer(1,3);
                }else{
                    commandForRandomChoiceComputer(0,4);
                }
            }else if(sp.getInt("userChoice",0) == 3){
                if(sp.getBoolean("oppSpecialUsed", false)){
                    commandForRandomChoiceComputer(1,2);
                }else{
                    commandForRandomChoiceComputer(0,3);
                }
            }
            System.out.println("Here is the value " + compChoice);
            // 0 = special
            // 1 == restore
            // 2 == attack
            // 3 == defend
        }
        private void computerMove(){ // list id == comp, Homedata = user
            if(compChoice == 0){
                putInForSpecialForCompActivate();
                specialCommandForComputer();
                userHealth = userHealth - specialAttack;
                secondaryTitle = listId.get(0).getName() + " uses their special!";
                secondaryDesc = listId.get(0).getName() + " has used their special on " + HomeData.searchNameList.get(0).getResults().get(0).getName() + "." + HomeData.searchNameList.get(0).getResults().get(0).getName() + " has lost " + specialAttack + " points for health.";

            }else if(compChoice == 1){
                restoreCommandForComputer();
                oppHealth = oppHealth + randomNumberForRestoreAmount;
                secondaryTitle = listId.get(0).getName() + " has taken the time to restore Health!";
                secondaryDesc = listId.get(0).getName() + " has restored " + randomNumberForRestoreAmount + " making a new total health of " + oppHealth;
            }else if(compChoice == 2){
                attackCommandForComputer();
                userHealth = userHealth - randomNumberForAttackLost;
                secondaryTitle = listId.get(0).getName() + " attacks!";
                secondaryDesc = listId.get(0).getName() + " has attacked " + HomeData.searchNameList.get(0).getResults().get(0).getName() + "." + HomeData.searchNameList.get(0).getResults().get(0).getName() + " has lost " + specialAttack + " points for health.";
            }else if(compChoice == 3){
                if(totalStatsForComputer() > totalStatsForUser() || totalStatsForComputer() == totalStatsForUser()){
                    if(sp.getInt("userChoice",0) == 0){
                        oppHealth = oppHealth + specialAttack;
                        secondaryDesc = listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " so computer regains health" ;
                        // regain health
                    }else if(sp.getInt("userChoice",0) == 2){
                        oppHealth = oppHealth + randomNumberForAttackLost;
                        secondaryDesc = listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " so computer regains health" ;
                    }else{
                        secondaryDesc = listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " but there was no attack, so nothing happens. " ;
                    }
                }else{
                    if(sp.getInt("userChoice",0) == 0){
                        oppHealth = oppHealth + specialAttack - 10;
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " so computer only lose 10 points for health." ;
                    }else if(sp.getInt("userChoice",0) == 2){
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " so computer only lose 10 points for health." ;
                        oppHealth = oppHealth + randomNumberForAttackLost - 10;
                    }else{
                        secondaryDesc = listId.get(0).getName() + " has defended, but there was no attack! So nothing happened.";
                    }
                }
                secondaryTitle = listId.get(0).getName() + " has defended it!";
            }
        }
        private void attackUserButton(){ // attack for user, functionality.
            attackUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    putInUserChoice(2);
                    attackCommandForUser();
                    oppHealth = oppHealth - randomNumberForAttackLost;
                    decidedWhatComputerShouldDo();
                    computerMove();
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
                    putInUserChoice(3);
                    decidedWhatComputerShouldDo();
                    computerMove();
                    defendFuncForUser();
                }
            });
        }
        private void defendFuncForUser(){
            System.out.println(compChoice + " here is the computer choice");
            if(compChoice == 0 || compChoice == 2){
                if(totalStatsForUser() > totalStatsForComputer() ||totalStatsForUser() == totalStatsForComputer()){
                    if(compChoice == 0){
                        userHealth = userHealth + specialAttack;
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " , so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " regains health");
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack";
                    }else if(compChoice == 2){
                        userHealth = userHealth + randomNumberForAttackLost;
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " , so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " regains health");
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack";
                    }else{
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " , but theirs no attack so nothing happens");
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack";
                    }
                }else{
                    if(compChoice == 0){
                        userHealth = userHealth + specialAttack - 10;
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " , so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " regains 10 health points");
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack";
                    }else if(compChoice == 2){
                        userHealth = userHealth + randomNumberForAttackLost - 10;
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " , so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " regains 10 health points");
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack";
                    }else{
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack, so user only takes 10 Health points damage";
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",HomeData.searchNameList.get(0).getResults().get(0).getName() + " has defended but nothing happens since " + listId.get(0).getName() + " has not attacked!");
                    }
                }
            }else{
                alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " , so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " regains 10 health points");
                secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack, but the computer did not attack! So nothing happens";
            }
        }
        private void restoreUserButton(){ // restore health functionality.
            restoreUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    putInUserChoice(1);
                    if(userHealth == 200){
                        // no reason to restore health, since your health is already at its max
                    }else{
                        restoreCommandForUser();
                        userHealth = userHealth + randomNumberForRestoreAmount;
                        decidedWhatComputerShouldDo();
                        computerMove();
                        setDataContent();
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has taken the time to restore Health!","defend");
                        // alert can go here when finished.
                    }
                }
            });
        }
        private void specialUserButton(){ // special for user functionality.
            specialUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    putInUserChoice(0);
                    if(!sp.getBoolean("userSpecialUsed", false))
                    {
                        putInSpecialForUserActivate();
                        specialCommandForUser();
                        oppHealth = oppHealth - specialAttack;
                        decidedWhatComputerShouldDo();
                        computerMove();
                        setDataContent();
                        alertDialogForUpdateContent("special","special");
                    }
                }
            });
        }
        private void checkIfComputerLoses(){ // check if computer loses.
            if(oppHealth == 0){
                // opp loses
                resetEverything();
                alertDialogForWinner("Computer Lost","You Won");
                editor.putInt("wins", sp.getInt("wins",0) + 1);
            }
        }
        private void putInUserChoice(int choice){
            editor.putInt("userChoice",choice);
            editor.apply();
        }
        private void putInSpecialForUserActivate(){
            editor.putBoolean("userSpecialUsed",true);
            editor.apply();
        }
        private void putInForSpecialForCompActivate(){
            editor.putBoolean("oppSpecialUsed",true);
            editor.apply();
        }
        private void giveUserXPForWin(){
            if(oppHealth <= 75){
                editor.putInt("xp",sp.getInt("xp",0) + 80); // when user loses they get 30 xp
            }else if(oppHealth <= 120){
                editor.putInt("xp",sp.getInt("xp",0) + 100); // when user loses they get 30 xp
            }else if(oppHealth <= 200){
                editor.putInt("xp",sp.getInt("xp",0) + 150); // when user loses they get 30 xp
            }
        }
        private void checkIfUserLoses(){ // check if user loses
            if(userHealth == 0){
                // user loses
                resetEverything();
                alertDialogForWinner("User Lost","Computer Won");
                editor.putInt("loses", + sp.getInt("loses",0) + 1);
            }
        }
        private void resetEverything(){ // reset everything back to normal.
            editor.putInt("userChoice",0);
            editor.putBoolean("userSpecialUsed",false);
            editor.putBoolean("oppSpecialUsed",false);
            editor.apply();
        }
        private void checkStatsForCharacterAndSetData(int statsOfCharacter){ // check stats of character for user attack.
            if(statsOfCharacter <= 300){
                commandRandom(10,30);
            }else if(statsOfCharacter <= 600){
                commandRandom(30,70);
            }else if(statsOfCharacter >= 601){
                commandRandom(70,100);
            }
        }
        private void checkStatsForHowMuchHealthToRestore(int statsOfCharacter){
            if(statsOfCharacter <= 300){
                commandRandom(10,20);
            }else if(statsOfCharacter <= 600){
                commandRandom(30,40);
            }else if(statsOfCharacter >= 601){
                commandRandom(50,60);
            }
        }
        private void checkStatsForHowMuchAttackForSpecial(int statsOfCharacter){
            if(statsOfCharacter <= 300){
                commandRandom(50,70);
            }else if(statsOfCharacter <= 600){
                commandRandom(70,80);
            }else if(statsOfCharacter >= 601){
                commandRandom(90,100);
            }
        }
        private void attackCommandForUser(){
            checkStatsForCharacterAndSetData(totalStatsForUser());
        }
        private void attackCommandForComputer(){
            checkStatsForCharacterAndSetData(totalStatsForComputer());
        }
        private void restoreCommandForUser(){
            checkStatsForHowMuchHealthToRestore(totalStatsForUser());
        }
        private void restoreCommandForComputer(){
            checkStatsForHowMuchHealthToRestore(totalStatsForComputer());
        }
        private void specialCommandForUser(){
            checkStatsForCharacterAndSetData(totalStatsForUser());
        }
        private void specialCommandForComputer(){
            checkStatsForHowMuchAttackForSpecial(totalStatsForComputer());
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
        private void commandRandom(int low, int high){ // command for user lost, following between two random numbers.
            Random r = new Random();
            randomNumberForAttackLost = r.nextInt(high-low) + low;
            specialAttack = r.nextInt(high-low) + low;
            randomNumberForRestoreAmount = r.nextInt(high-low) + low;
        }
        private void commandForRandomChoiceComputer(int low, int high){
            Random r = new Random();
            compChoice = r.nextInt(high-low) + low;
            if(compChoice == 4){
                compChoice = 0;
            }
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
                    alert.setNegativeButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                            checkIfComputerLoses();
                            checkIfUserLoses();
                        }

                    });
                    AlertDialog dialogs = alert.create();
                    dialogs.show();

                }

            });
            AlertDialog dialogs = alert.create();
            dialogs.show();
        }
        private void alertDialogForWinner(String title, String body){
            final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle(title);
            alert.setMessage(body);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                    ((MainActivity)mContext).setViewPagerHome();
                    ((MainActivity)mContext).setBottomColor();
                }

            });
            AlertDialog dialogs = alert.create();
            dialogs.show();
        }
    }
}

