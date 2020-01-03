package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
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

public class TacView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private List<Id> listId;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int oppHealth = 200;
    private int userHealth = 200;
    private int currentRoundValue = 0;

    public TacView(Context mContext, List<Id> listId)
    {
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
        {
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
        private void checkDarkMode(){
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
        private void setContentColor(TextView currentHealth, String value){
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
        private void setCharacterColor(){
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
                    // nothing for now
                }
            }
            if(listId != null){
                if(listId.get(0).getBiography().getAlignment().equals("good")){
                    setContentColor(mainUserTextViewCurrentHealth2,"#0000ff");
                }else if(listId.get(0).getBiography().getAlignment().equals("bad")){
                    setContentColor(mainUserTextViewCurrentHealth2,"#B22222");
                }else{
                    setContentColor(mainUserTextViewCurrentHealth2,"#0000ff");
                    // nothing for now
                }
            }
        }

        private void setImageContent()
        {
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
        private void setDataContent(){
            if(HomeData.searchNameList != null){
                mainUserTextViewcurrentHealth.setText(HomeData.searchNameList.get(0).getResults().get(0).getName() + " \t\nCurrent Health: " + userHealth);
            }
            if(listId != null){
                mainUserTextViewCurrentHealth2.setText(listId.get(0).getName() +  " \t\nCurrent Health: " + oppHealth );
            }
            currentRound.setText("Current Round: " + currentRoundValue);
        }
        private void attackUserButton(){
            attackUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        private void defendUserButton(){
            defendUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        private void restoreUserButton(){
            restoreUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        private void specialUserButton(){
            specialUserButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
        private void checkIfComputerLoses(){
            if(oppHealth <= 0){
                // opp loses
            }
        }
        private void checkIfUserWins(){
            if(userHealth <= 0){
                // user loses
            }
        }
        private void resetEverything(){

        }
    }
}

