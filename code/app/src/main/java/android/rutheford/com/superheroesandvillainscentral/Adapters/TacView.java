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
    private int userXPEarned;

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
            currentRoundValue++;
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
        private void decidedWhatComputerShouldDo(){ // algorithm, that sees what the comp will do based on stats.
            // Depending on choice, comp will pick a random number between low and high, and from there will generate a outcome.
            if(oppHealth <= 30){ // if comp choice less then or equal to 30 restore
                compChoice = 1;
            }
            if(userHealth <= 100){ // less then or equal to 100 do special or attack.
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
            // 0 = special
            // 1 == restore
            // 2 == attack
            // 3 == defend
        }
        private void computerMove(){ // checks the computer move, and does func based on move
            // 0= special, 1- restore, 2= attack, 3 = defend, for what the user is gotta do.
            if(compChoice == 0){ // special func
                putInForSpecialForCompActivate();
                specialCommandForComputer();
                userHealth = userHealth - specialAttack;
                secondaryTitle = listId.get(0).getName() + " uses their special!";
                secondaryDesc = listId.get(0).getName() + " has used their special on " + HomeData.searchNameList.get(0).getResults().get(0).getName() + "." + HomeData.searchNameList.get(0).getResults().get(0).getName() + " has lost " + specialAttack + " points for health.";

            }else if(compChoice == 1){ // restore for comp
                restoreCommandForComputer();
                oppHealth = oppHealth + randomNumberForRestoreAmount;
                if(oppHealth > 200){
                    oppHealth = 200;
                }else if(oppHealth < 0){
                    oppHealth = 0;
                }
                secondaryTitle = listId.get(0).getName() + " has taken the time to restore Health!";
                secondaryDesc = listId.get(0).getName() + " has restored " + randomNumberForRestoreAmount + " making a new total health of " + oppHealth;
            }else if(compChoice == 2){ // attack for comp
                attackCommandForComputer();
                userHealth = userHealth - randomNumberForAttackLost;
                secondaryTitle = listId.get(0).getName() + " attacks!";
                secondaryDesc = listId.get(0).getName() + " has attacked " + HomeData.searchNameList.get(0).getResults().get(0).getName() + "." + HomeData.searchNameList.get(0).getResults().get(0).getName() + " has lost " + specialAttack + " points for health.";
            }else if(compChoice == 3){
                // checks to see if score is larger, if it is then comp wont take any health damage.
                if(totalStatsForComputer() > totalStatsForUser() || totalStatsForComputer() == totalStatsForUser()){
                    if(sp.getInt("userChoice",0) == 0){
                        oppHealth = oppHealth + specialAttack;
                        secondaryDesc = listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " so " + listId.get(0).getName() + " regains health" ;
                        // regain health
                    }else if(sp.getInt("userChoice",0) == 2){
                        oppHealth = oppHealth + randomNumberForAttackLost;
                        secondaryDesc = listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " so " + listId.get(0).getName() + " regains health" ;
                    }else{
                        secondaryDesc = listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " but there was no attack, so nothing happens. " ;
                    }
                }else{ // but if user has bigger score, then the comp will take -10 health
                    if(sp.getInt("userChoice",0) == 0){
                        oppHealth = oppHealth + specialAttack - 10;
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " so " + listId.get(0).getName() + " only lose 10 points for health." ;
                    }else if(sp.getInt("userChoice",0) == 2){
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " is stronger then " + listId.get(0).getName() + " so " + listId.get(0).getName() + " only lose 10 points for health." ;
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
                        secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack, so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " only takes 10 Health points damage";
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",HomeData.searchNameList.get(0).getResults().get(0).getName() + " has defended but nothing happens since " + listId.get(0).getName() + " has not attacked!");
                    }
                }
            }else{
                alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has decided to defend",listId.get(0).getName() + " is stronger then " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " , so " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " regains 10 health points");
                secondaryDesc = HomeData.searchNameList.get(0).getResults().get(0).getName() + " has blocked the attack, but " + listId.get(0).getName() + " did not attack! So nothing happens";
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
                        alertForCommandCantBeUsed("Cannot Restore","Sorry but " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " health is already at 200! Please try another command!");
                        // no reason to restore health, since your health is already at its max
                    }else{
                        restoreCommandForUser();
                        userHealth = userHealth + randomNumberForRestoreAmount;
                        if(userHealth < 0){// if you restore but your health is less then 0, make it 0 so no negative numbers.
                            oppHealth = 0;
                        }
                        decidedWhatComputerShouldDo();
                        computerMove();
                        setDataContent();
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has taken the time to restore Health!",HomeData.searchNameList.get(0).getResults().get(0).getName() + " has restored health by " + randomNumberForRestoreAmount + " points.");
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
                        specialCommandForUser(); // equation for deduct score from opp health.
                        oppHealth = oppHealth - specialAttack;
                        decidedWhatComputerShouldDo();
                        computerMove();
                        setDataContent();
                        alertDialogForUpdateContent(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has used their special on " + listId.get(0).getName() + ".",  listId.get(0).getName() + " has lost " + specialAttack + " points for health.");
                    }else{ // user has already used special, and can only use it once per battle.
                        alertForCommandCantBeUsed("Already Used Special","Sorry but " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " has already used there special! Please try another command. ");
                    }
                }
            });
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
        private void checkIfComputerLoses(){ // check if computer loses.
            if(oppHealth == 0){
                // opp loses
                resetEverything();
                xpEarned();
                editor.putInt("wins", sp.getInt("wins",0) + 1);
                editor.apply();
                alertDialogUpdate(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has won!","Congrats " + HomeData.searchNameList.get(0).getResults().get(0).getName() + " has won this battle!" +
                        "\t\n\t\nCurrent Wins: " + sp.getInt("wins",0) + "\t\n\t\nCurrent Loses: " + sp.getInt("loses",0) + "\t\n\t\nXP Earned: " + userXPEarned + "" +
                        "\t\n\t\nCurrent XP: " + sp.getInt("xp",0) + "\t\n\r\nCurrent Ties: " + sp.getInt("ties",0));
            }
        }
        private void checkIfUserLoses(){ // check if user loses
            if(userHealth == 0){
                // user loses
                resetEverything();
                xpEarned();
                editor.putInt("loses", + sp.getInt("loses",0) + 1);
                editor.apply();
                alertDialogUpdate(HomeData.searchNameList.get(0).getResults().get(0).getName() + " has taken defeat!",listId.get(0).getName() + " has won this battle!" +
                        "\t\n\t\nCurrent Wins: " + sp.getInt("wins",0) + "\t\n\t\nCurrent Loses: " + sp.getInt("loses",0) + "\t\n\t\nXP Earned: " + userXPEarned + "" +
                        "\t\n\t\nCurrent XP: " + sp.getInt("xp",0) + "\t\n\r\nCurrent Ties: " + sp.getInt("ties",0));
            }
        }
        private void checkIfTie(){ // check if its a tie, and if it is display alert.
            if(userHealth == 0 && oppHealth == 0){
                resetEverything();
                xpEarned();
                editor.putInt("ties", + sp.getInt("ties",0) + 1);
                editor.apply();
                alertDialogUpdate("We Have A Tie!",listId.get(0).getName() + " & " + HomeData.searchNameList.get(0).getResults().get(0).getName() +  " has both been defeated!" +
                        "\t\n\t\nCurrent Wins: " + sp.getInt("wins",0) + "\t\n\t\nCurrent Loses: " + sp.getInt("loses",0) + "\t\n\t\nXP Earned: " + userXPEarned + "" +
                        "\t\n\t\nCurrent XP: " + sp.getInt("xp",0) + "\t\n\r\nCurrent Ties: " + sp.getInt("ties",0));
            }
        }
        private void xpEarned(){ // based on user health, give user amount of xp.
            if(userHealth  == 0){
                userXPEarned = 50;
            }
            else if(userHealth <= 70){
                userXPEarned = 80;
            }else if(userHealth <= 120){
                userXPEarned = 100;
            }else if(userHealth <= 200){
                userXPEarned = 150;
            }
            editor.putInt("xp",sp.getInt("xp",0) + userXPEarned);
            editor.apply();
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
        private void checkStatsForHowMuchHealthToRestore(int statsOfCharacter){ // check to see how much to restore, based on character ability.
            if(statsOfCharacter <= 300){
                commandRandom(10,20);
            }else if(statsOfCharacter <= 600){
                commandRandom(30,40);
            }else if(statsOfCharacter >= 601){
                commandRandom(50,60);
            }
        }
        private void checkStatsForHowMuchAttackForSpecial(int statsOfCharacter){ // check to see how much to attack, based on character ability.
            if(statsOfCharacter <= 300){
                commandRandom(50,70);
            }else if(statsOfCharacter <= 600){
                commandRandom(70,80);
            }else if(statsOfCharacter >= 601){
                commandRandom(90,100);
            }
        }
        private void attackCommandForUser(){ // attack command for user
            checkStatsForCharacterAndSetData(totalStatsForUser());
        }
        private void attackCommandForComputer(){ // attack command for comp
            checkStatsForCharacterAndSetData(totalStatsForComputer());
        }
        private void restoreCommandForUser(){ // restore for user
            checkStatsForHowMuchHealthToRestore(totalStatsForUser());
        }
        private void restoreCommandForComputer(){ // restore for comp
            checkStatsForHowMuchHealthToRestore(totalStatsForComputer());
        }
        private void specialCommandForUser(){ // special for user
            checkStatsForCharacterAndSetData(totalStatsForUser());
        }
        private void specialCommandForComputer(){ // special for comp
            checkStatsForHowMuchAttackForSpecial(totalStatsForComputer());
        }

        private int totalStatsForUser(){ // return total stats for user
            return HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getIntelligence() + HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getStrength() +
                    HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getSpeed() + HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getDurability() +
                    HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getPower() + HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getCombat();
        }
        private int totalStatsForComputer(){ // return total stats for comp
            return listId.get(0).getPowerStats().getIntelligence() + listId.get(0).getPowerStats().getSpeed() + listId.get(0).getPowerStats().getPower() + listId.get(0).getPowerStats().getStrength() +
                    listId.get(0).getPowerStats().getDurability() + listId.get(0).getPowerStats().getCombat();
        }
        private void commandRandom(int low, int high){ // command for user lost, following between two random numbers.
            Random r = new Random();
            randomNumberForAttackLost = r.nextInt(high-low) + low;
            specialAttack = r.nextInt(high-low) + low;
            randomNumberForRestoreAmount = r.nextInt(high-low) + low;
        }
        private void commandForRandomChoiceComputer(int low, int high){ // gets a number for the user choice, and if anything is equal 4 its considered special.
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
                            if(userHealth == 0 && oppHealth == 0){
                                checkIfTie();
                            }else
                            {
                                checkIfComputerLoses();
                                checkIfUserLoses();
                            }
                        }

                    });
                    AlertDialog dialogs = alert.create();
                    dialogs.show();

                }

            });
            AlertDialog dialogs = alert.create();
            dialogs.show();
        }
        private void alertDialogUpdate(String title, String body){ // alert dialog for close out
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
        private void alertForCommandCantBeUsed(String title, String body){ // alert dialog for user that cant use buttons, due to rules.
            final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle(title);
            alert.setMessage(body);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
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
    }
}

