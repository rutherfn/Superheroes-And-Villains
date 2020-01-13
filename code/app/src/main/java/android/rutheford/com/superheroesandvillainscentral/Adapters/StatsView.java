package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;

/**
 * Created by Nick R.
 */

public class StatsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private Context mContext;
    private List<Id> listId;
    private SharedPreferences sp1;
    private SharedPreferences.Editor editor;

    public StatsView(Context mContext,List<Id> listId)
    { // cons takes in array list and constructor
        this.mContext = mContext;
        this.listId = listId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stats_man,viewGroup,false);
        return new ViewStats(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        ViewStats viewStats = (ViewStats) holder;
        viewStats.Main(i);
    }
    @Override
    public int getItemCount()
    {
        return 1;
    }
    class ViewStats extends RecyclerView.ViewHolder{
        TextView superHeroVillianName;
        TextView locationTitle;
        TextView locationText;
        TextView alignmentTitle;
        TextView alignmentText;
        TextView alterEgosTitle;
        TextView alterEgosText;
        TextView firstApperanceTitle;
        TextView firstApperanceText;
        TextView statsTitle;
        TextView intelligenceTitle;
        TextView intelligenceText;
        TextView strengthTitle;
        TextView strengthText;
        TextView speedTitle;
        TextView speedText;
        TextView durabilityTitle;
        TextView durabilityText;
        TextView powerTitle;
        TextView powerText;
        TextView combatTitle;
        TextView combatText;
        Button battleCharacterButton,assignNewCharacterButton,moreInfoCharacterButton;


        public ViewStats(@NonNull View itemView)
        {
            super(itemView);
            superHeroVillianName = itemView.findViewById(R.id.superHeroVillianName);
             locationText= itemView.findViewById(R.id.locationTextViewTitle);
            locationTitle = itemView.findViewById(R.id.locationText);
            alignmentText = itemView.findViewById(R.id.alignmentTitle);
            alignmentTitle = itemView.findViewById(R.id.alignmentText);
            alterEgosTitle = itemView.findViewById(R.id.alterEgosTitle);
            alterEgosText = itemView.findViewById(R.id.alterEgosText);
            firstApperanceText = itemView.findViewById(R.id.firstAppearanceText);
            firstApperanceTitle = itemView.findViewById(R.id.firstAppearanceTitle);
            statsTitle = itemView.findViewById(R.id.statsTitle);
            intelligenceTitle = itemView.findViewById(R.id.intelligenceTitle);
            intelligenceText = itemView.findViewById(R.id.intelligenceText);
            strengthTitle = itemView.findViewById(R.id.strengthTitle);
            strengthText = itemView.findViewById(R.id.strengthText);
            speedTitle = itemView.findViewById(R.id.speedTitle);
            speedText = itemView.findViewById(R.id.speedText);
            durabilityTitle = itemView.findViewById(R.id.durabilityTitle);
            durabilityText = itemView.findViewById(R.id.durabilityText);
            powerTitle = itemView.findViewById(R.id.powerTitle);
            powerText = itemView.findViewById(R.id.powerText);
            combatTitle = itemView.findViewById(R.id.combatTitle);
            combatText = itemView.findViewById(R.id.combatText);
            battleCharacterButton = itemView.findViewById(R.id.battleCharacterButton);
            assignNewCharacterButton = itemView.findViewById(R.id.assignNewCharacterButton);
            moreInfoCharacterButton = itemView.findViewById(R.id.moreInfoCharacterButton);
        }
        private void Main(int i){
            setUpSharedPrefs();
            setSuperHeroVillianData();
            setUpTypeFace();
            setUpTextColorBasedOnGoodOrBadGuy();
            switchToBattleCharacter(i);
            moreInfoOnCharacter();
            checkDarkMode();
            totalStatsOfVsCharacter();
            purchaseCharacterButton();
        }
        private void totalStatsOfVsCharacter(){ // setting up total stats for user.
            if(listId != null && listId.size() == 1){
                // declarations
                int totalStatsForUser = listId.get(0).getPowerStats().getIntelligence() +
                        listId.get(0).getPowerStats().getStrength() + listId.get(0).getPowerStats().getSpeed() +
                        listId.get(0).getPowerStats().getSpeed() + listId.get(0).getPowerStats().getDurability() +
                        listId.get(0).getPowerStats().getPower() + listId.get(0).getPowerStats().getCombat();
                editor.putInt("totalStatForVsCharacter", totalStatsForUser);
                editor.apply();
            }
        }
        private void setUpSharedPrefs(){
            sp1 = mContext.getSharedPreferences("key", 0);
            editor = sp1.edit();
        }
        private void checkDarkMode(){ // if your in dark mode, set the fields first.
            if(sp1.getInt("darkMode",0) == 1){
                superHeroVillianName.setTextColor(Color.parseColor("#FFFFFF"));
                locationText.setTextColor(Color.parseColor("#FFFFFF"));
                locationTitle.setTextColor(Color.parseColor("#FFFFFF"));
                alignmentText.setTextColor(Color.parseColor("#FFFFFF"));
                alignmentTitle.setTextColor(Color.parseColor("#FFFFFF"));
                alterEgosText.setTextColor(Color.parseColor("#FFFFFF"));
                firstApperanceText.setTextColor(Color.parseColor("#FFFFFF"));
                firstApperanceTitle.setTextColor(Color.parseColor("#FFFFFF"));
                statsTitle.setTextColor(Color.parseColor("#FFFFFF"));
                intelligenceText.setTextColor(Color.parseColor("#FFFFFF"));
                strengthText.setTextColor(Color.parseColor("#FFFFFF"));
                strengthTitle.setTextColor(Color.parseColor("#FFFFFF"));
                speedTitle.setTextColor(Color.parseColor("#FFFFFF"));
                speedText.setTextColor(Color.parseColor("#FFFFFF"));
                durabilityTitle.setTextColor(Color.parseColor("#FFFFFF"));
                durabilityText.setTextColor(Color.parseColor("#FFFFFF"));
                powerTitle.setTextColor(Color.parseColor("#FFFFFF"));
                powerText.setTextColor(Color.parseColor("#FFFFFF"));
                combatTitle.setTextColor(Color.parseColor("#FFFFFF"));
                combatText.setTextColor(Color.parseColor("#FFFFFF"));
                intelligenceTitle.setTextColor(Color.parseColor("#FFFFFF"));
                alterEgosTitle.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        private void setUpTypeFace(){ // set up type face for all fields.
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            Typeface mainBody = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
            superHeroVillianName.setTypeface(mainTextTypeFace);
            locationText.setTypeface(mainBody);
            locationTitle.setTypeface(mainTextTypeFace);
            alignmentTitle.setTypeface(mainTextTypeFace);
            alignmentText.setTypeface(mainBody);
            alterEgosTitle.setTypeface(mainTextTypeFace);
            alterEgosText.setTypeface(mainBody);
            firstApperanceTitle.setTypeface(mainTextTypeFace);
            firstApperanceText.setTypeface(mainBody);
            statsTitle.setTypeface(mainTextTypeFace);
            intelligenceTitle.setTypeface(mainTextTypeFace);
            intelligenceText.setTypeface(mainBody);
            strengthTitle.setTypeface(mainTextTypeFace);
            strengthText.setTypeface(mainBody);
            speedTitle.setTypeface(mainTextTypeFace);
            speedText.setTypeface(mainBody);
            durabilityTitle.setTypeface(mainTextTypeFace);
            durabilityText.setTypeface(mainBody);
            powerTitle.setTypeface(mainTextTypeFace);
            powerText.setTypeface(mainBody);
            combatTitle.setTypeface(mainTextTypeFace);
            combatText.setTypeface(mainBody);
            battleCharacterButton.setTypeface(mainTextTypeFace);
            assignNewCharacterButton.setTypeface(mainTextTypeFace);
            moreInfoCharacterButton.setTypeface(mainTextTypeFace);
        }
        private void setTextColor(String colorOne, String colorTwo,String colorThree){
            superHeroVillianName.setTextColor(Color.parseColor(colorOne));
            locationText.setTextColor(Color.parseColor(colorTwo));
            locationTitle.setTextColor(Color.parseColor(colorOne));
            alignmentText.setTextColor(Color.parseColor(colorTwo));
            alignmentTitle.setTextColor(Color.parseColor(colorOne));
            alterEgosTitle.setTextColor(Color.parseColor(colorTwo));
            alterEgosText.setTextColor(Color.parseColor(colorOne));
            firstApperanceTitle.setTextColor(Color.parseColor(colorTwo));
            firstApperanceText.setTextColor(Color.parseColor(colorOne));
            statsTitle.setTextColor(Color.parseColor(colorOne));
            intelligenceTitle.setTextColor(Color.parseColor(colorTwo));
            intelligenceText.setTextColor(Color.parseColor(colorOne));
            strengthTitle.setTextColor(Color.parseColor(colorTwo));
            strengthText.setTextColor(Color.parseColor(colorOne));
            speedTitle.setTextColor(Color.parseColor(colorTwo));
            speedText.setTextColor(Color.parseColor(colorOne));
            durabilityTitle.setTextColor(Color.parseColor(colorTwo));
            durabilityText.setTextColor(Color.parseColor(colorOne));
            powerTitle.setTextColor(Color.parseColor(colorTwo));
            powerText.setTextColor(Color.parseColor(colorOne));
            combatTitle.setTextColor(Color.parseColor(colorTwo));
            combatText.setTextColor(Color.parseColor(colorOne));
            battleCharacterButton.setBackgroundColor(Color.parseColor(colorOne));
            battleCharacterButton.setTextColor(Color.parseColor(colorThree));
            assignNewCharacterButton.setBackgroundColor(Color.parseColor(colorOne));
            assignNewCharacterButton.setTextColor(Color.parseColor(colorThree));
            moreInfoCharacterButton.setBackgroundColor(Color.parseColor(colorOne));
            moreInfoCharacterButton.setTextColor(Color.parseColor(colorThree));
        }
        private void setUpTextColorBasedOnGoodOrBadGuy(){ // based on if character is good, bad, or none of the above. Set colors with paramters.
            if(listId != null && listId.size() == 1)
            {
                if (listId.get(0).getBiography().getAlignment().equals("good"))
                {
                    setTextColor("#0000ff", "#000000", "#FFFFFF");
                }else if(listId.get(0).getBiography().getAlignment().equals("bad")){
                    setTextColor("#B22222", "#000000", "#FFFFFF");
                }else{
                    setTextColor("#0000ff", "#000000", "#FFFFFF");
                }
            }
        }
        @SuppressLint("SetTextI18n")
        private void setSuperHeroVillianData(){ // sets all data for stats view.
            if(listId != null && listId.size() == 1){
                superHeroVillianName.setText(listId.get(0).getName());
                if(listId.get(0).getBiography().getPlaceOfBirth().equals("-")){
                    locationTitle.setText("Unknown");
                }else{
                locationTitle.setText(listId.get(0).getBiography().getPlaceOfBirth());
                }
                if(listId.get(0).getBiography().getAlignment().equals("good")){
                    alignmentTitle.setText("Good");
                }else if(listId.get(0).getBiography().getAlignment().equals("bad")){
                    alignmentTitle.setText("Bad");
                }else{
                    alignmentTitle.setText("Neutral");
                }
                firstApperanceText.setText(listId.get(0).getBiography().getPublisher());
                if(listId.get(0).getBiography().getAlterEgos().equals("No alter egos found.")){
                    alterEgosText.setText("None");
                }else
                {
                    alterEgosText.setText(listId.get(0).getBiography().getAlterEgos());
                }
                intelligenceText.setText(String.valueOf(listId.get(0).getPowerStats().getIntelligence()));
                strengthText.setText(String.valueOf(listId.get(0).getPowerStats().getStrength()));
                speedText.setText(String.valueOf(listId.get(0).getPowerStats().getSpeed()));
                durabilityText.setText(String.valueOf(listId.get(0).getPowerStats().getDurability()));
                powerText.setText(String.valueOf(listId.get(0).getPowerStats().getPower()));
                combatText.setText(String.valueOf(listId.get(0).getPowerStats().getCombat()));
                battleCharacterButton.setText("Battle Character");
                if(HomeData.listPurchaseCharacters.size() > 0){
                    for(int i = 0; i < HomeData.listPurchaseCharacters.size(); i++){
                        if(HomeData.listPurchaseCharacters.get(i).getName().equals(listId.get(0).getName())){
                           assignNewCharacterButton.setVisibility(View.GONE);
                        }else{
                            assignNewCharacterButton.setText("Purchase Character");
                        }
                    }
                }else{
                    assignNewCharacterButton.setText("Purchase Character");
                }
                if(assignNewCharacterButton.getVisibility() == View.GONE){
                    assignNewCharacterButton.setVisibility(View.VISIBLE);
                    assignNewCharacterButton.setText("Assign Yourself Character");
                }
                moreInfoCharacterButton.setText("More Info");
            }else if(HomeData.searchNameList != null){
                superHeroVillianName.setText(HomeData.searchNameList.get(0).getResults().get(0).getName());
                if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getPlaceOfBirth().equals("-")){
                    locationTitle.setText("Unknown");
                }else{
                    locationTitle.setText(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getPlaceOfBirth());
                }
                if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getAlignment().equals("good")){
                    alignmentTitle.setText("Good");
                }else if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getAlignment().equals("bad")){
                    alignmentTitle.setText("Bad");
                }else{
                    alignmentTitle.setText("Neutral");
                }
                if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getPublisher().equals("")){
                    firstApperanceText.setText("None");
                }else
                {
                    firstApperanceText.setText(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getPublisher());
                }
                if(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getAlterEgos().equals("No alter egos found.")){
                    alterEgosText.setText("None");
                }else{
                    alterEgosText.setText(HomeData.searchNameList.get(0).getResults().get(0).getBiography().getAlterEgos());
                }
                intelligenceText.setText(String.valueOf(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getIntelligence()));
                strengthText.setText(String.valueOf(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getStrength()));
                speedText.setText(String.valueOf(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getSpeed()));
                durabilityText.setText(String.valueOf(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getDurability()));
                powerText.setText(String.valueOf(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getPower()));
                combatText.setText(String.valueOf(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getCombat()));
                battleCharacterButton.setVisibility(View.GONE);
                assignNewCharacterButton.setVisibility(View.GONE);
                moreInfoCharacterButton.setText("More Info");
            }
        }
        private void moreInfoOnCharacter(){ // more info on character listener
            moreInfoCharacterButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle(superHeroVillianName.getText().toString());
                    if(((MainActivity)mContext).returnCurrentViewPagerItem() == 3)
                    {
                        String[] statsForCharacter = {"Publisher: " + listId.get(0).getBiography().getPublisher(), "Gender: " + listId.get(0).getAppearance().getGender(), "Race: " + listId.get(0).getAppearance().getRace(), "Work: " + listId.get(0).getWork().getOccupation(), "Group-Affiliation: " + listId.get(0).getConnections().getGroupAffiliation()};
                        builder.setItems(statsForCharacter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                }
                            }
                        });
                        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else{
                        String[] statsForCharacter = {"Publisher: " + HomeData.searchCharacterData.get(0).getBiography().getPublisher(), "Gender: " + HomeData.searchCharacterData.get(0).getAppearance().getGender(), "Race: " + HomeData.searchCharacterData.get(0).getAppearance().getRace(), "Work: " + HomeData.searchCharacterData.get(0).getWork().getOccupation(), "Group-Affiliation: " + HomeData.searchCharacterData.get(0).getConnections().getGroupAffiliation()};
                        builder.setItems(statsForCharacter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                }
                            }
                        });
                        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
            });
        }
        private void purchaseCharacterButton(){ // checks to see if you can purchase characters, from your xp point system.
            assignNewCharacterButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (assignNewCharacterButton.getText().toString().equals("Assign Yourself Character"))
                    {
                        final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                        alert.setTitle("Change Characters");
                        alert.setMessage("Are you sure you want to switch to " + listId.get(0).getName());
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.cancel();
                            }
                        });
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.cancel();
                                ((MainActivity)mContext).callApiForNewCharacterAssign(listId.get(0).getId());
                                editor.putInt("uniqueId",listId.get(0).getId());
                                editor.apply();
                                Toast.makeText(mContext,listId.get(0).getName() + " is now your new main character!",Toast.LENGTH_SHORT).show();
                                ((MainActivity)mContext).reloadSettings();
                            }
                        });
                        AlertDialog dialogs = alert.create();
                        dialogs.show();
                    } else
                    {
                        if (sp1.getInt("xp", 0) > sp1.getInt("totalStatForVsCharacter", 0) ||
                                sp1.getInt("xp", 0) == sp1.getInt("totalStatForVsCharacter", 0))
                        {
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                            //                    alertDialog.setTitle("Get Ready For Battle");
                            alertDialog.setMessage("Are you sure you want to purchase " + listId.get(0).getName());
                            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();

                                }
                            });
                            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Paper.book().write(listId.get(0).getName(), listId);
                                    editor.putInt("xp", sp1.getInt("xp", 0) - sp1.getInt("totalStatForVsCharacter", 0));
                                    editor.apply();
                                    final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                    alert.setTitle("Character Purchased");
                                    alert.setMessage(listId.get(0).getName() + " has been purchased, heading back to the home screen! Please visit the settings screen to view purchase characters, press OK to continue!");
                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            ((MainActivity) mContext).restartMainActivity();
                                        }
                                    });
                                    AlertDialog alertThis = alert.create();
                                    alertThis.show();
                                }
                            });
                            AlertDialog dialog = alertDialog.create();
                            dialog.show();

                        } else
                        {
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                            //                    alertDialog.setTitle("Get Ready For Battle");
                            alertDialog.setMessage("You do not have enough XP to purchase " + listId.get(0).getName() + "." + "\n\nCurrent XP: " + sp1.getInt("xp", 0) + "\n\n" + listId.get(0).getName() + " Current XP: " + sp1.getInt("totalStatForVsCharacter", 0) + "\n\nEarn more XP by battling other characters, and come back when you have enough XP!");
                            alertDialog.setNegativeButton("OKAY", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();

                                }
                            });
                            AlertDialog dialog = alertDialog.create();
                            dialog.show();
                        }
                    }
                }
            });
        }
        private void switchToBattleCharacter(final int pos){ // here alert that checks if your able to battle the character, and goes into battle based on settings and qualifications.
            battleCharacterButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (((MainActivity) mContext).returnCurrentViewPagerItem() == 3)
                    {
                        if (HomeData.searchNameList != null)
                        {
                            if (HomeData.searchNameList.get(0).getResults().get(0).getName().equals(listId.get(0).getName()))
                            {
                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                                alertDialog.setTitle("Cant Battle Yourself");
                                alertDialog.setMessage(HomeData.searchNameList.get(0).getResults().get(0).getName() + " vs " + listId.get(0).getName() + " is not a option, switch character or find a new character to battle!");
                                alertDialog.setNegativeButton("CONTINUE", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            } else
                            {
                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                                alertDialog.setTitle("Get Ready For Battle");
                                alertDialog.setMessage("Are you sure your ready for battle?");
                                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dialog.cancel();
                                    }
                                });
                                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dialog.cancel();
                                        final AlertDialog alertDialog = new
                                                SpotsDialog.Builder().setContext(mContext).build();
                                        alertDialog.setTitle("Loading Battle");
                                        alertDialog.setMessage("Please wait.....");
                                        alertDialog.show();
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable()
                                        {

                                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                            @Override
                                            public void run()
                                            {
                                                alertDialog.dismiss();
                                                if(sp1.getInt("simOrTac",0) == 0)
                                                {
                                                    ((MainActivity) mContext).switchToVs(listId, pos);
                                                }else if(sp1.getInt("simOrTac",0) == 1){
                                                    ((MainActivity)mContext).switchToTac(listId,pos);
                                                }
                                            }
                                        }, 3000);
                                    }
                                });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                            }
                        } else
                        {
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                            alertDialog.setTitle("Please Choose A Character");
                            alertDialog.setMessage("Seems like we you don't have a character set, please choose a character in order to battle!");
                            alertDialog.setNegativeButton("CONTINUE", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog dialog = alertDialog.create();
                            dialog.show();
                        }
                    }
                }
            });
        }
    }
}