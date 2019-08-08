package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Results;
import android.rutheford.com.superheroesandvillainscentral.Models.SearchName;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class StatsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context mContext;
    private SharedPreferences sp;

    public StatsView(Context mContext)
    {
        this.mContext = mContext;
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
        viewStats.setSuperHeroVillianData();
        viewStats.setUpTypeFace();
        viewStats.setTextColor();
        viewStats.switchToBattleCharacter();
        viewStats.assignNewCharacter();
        viewStats.moreInfoOnCharacter();
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
        private void setUpTypeFace(){
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
        private void setTextColor(){
            superHeroVillianName.setTextColor(Color.parseColor("#0000ff"));
            locationText.setTextColor(Color.parseColor("#000000"));
            locationTitle.setTextColor(Color.parseColor("#0000ff"));
            alignmentText.setTextColor(Color.parseColor("#000000"));
            alignmentTitle.setTextColor(Color.parseColor("#0000ff"));
            alterEgosTitle.setTextColor(Color.parseColor("#000000"));
            alterEgosText.setTextColor(Color.parseColor("#0000ff"));
            firstApperanceTitle.setTextColor(Color.parseColor("#000000"));
            firstApperanceText.setTextColor(Color.parseColor("#0000ff"));
            statsTitle.setTextColor(Color.parseColor("#0000ff"));
            intelligenceTitle.setTextColor(Color.parseColor("#000000"));
            intelligenceText.setTextColor(Color.parseColor("#0000ff"));
            strengthTitle.setTextColor(Color.parseColor("#000000"));
            strengthText.setTextColor(Color.parseColor("#0000ff"));
            speedTitle.setTextColor(Color.parseColor("#000000"));
            speedText.setTextColor(Color.parseColor("#0000ff"));
            durabilityTitle.setTextColor(Color.parseColor("#000000"));
            durabilityText.setTextColor(Color.parseColor("#0000ff"));
            powerTitle.setTextColor(Color.parseColor("#000000"));
            powerText.setTextColor(Color.parseColor("#0000ff"));
            combatTitle.setTextColor(Color.parseColor("#000000"));
            combatText.setTextColor(Color.parseColor("#0000ff"));
            battleCharacterButton.setBackgroundColor(Color.parseColor("#0000ff"));
            battleCharacterButton.setTextColor(Color.parseColor("#FFFFFF"));
            assignNewCharacterButton.setBackgroundColor(Color.parseColor("#0000ff"));
            assignNewCharacterButton.setTextColor(Color.parseColor("#FFFFFF"));
            moreInfoCharacterButton.setBackgroundColor(Color.parseColor("#0000ff"));
            moreInfoCharacterButton.setTextColor(Color.parseColor("#FFFFFF"));
        }
        @SuppressLint("SetTextI18n")
        private void setSuperHeroVillianData(){
            if(HomeData.opponentId != null){
                superHeroVillianName.setText(HomeData.opponentId.get(0).getName());
                if(HomeData.opponentId.get(0).getBiography().getPlaceOfBirth().equals("-")){
                    locationTitle.setText("Unknown");
                }else{
                locationTitle.setText(HomeData.opponentId.get(0).getBiography().getPlaceOfBirth());
                }
                if(HomeData.opponentId.get(0).getBiography().getAlignment().equals("good")){
                    alignmentTitle.setText("Good");
                }else if(HomeData.opponentId.get(0).getBiography().getAlignment().equals("bad")){
                    alignmentTitle.setText("Bad");
                }else{
                    alignmentTitle.setText("Neutral");
                }
                firstApperanceText.setText(HomeData.opponentId.get(0).getBiography().getPublisher());
                if(HomeData.opponentId.get(0).getBiography().getAlterEgos().equals("No alter egos found.")){
                    alterEgosText.setText("None");
                }else
                {
                    alterEgosText.setText(HomeData.opponentId.get(0).getBiography().getAlterEgos());
                }
                intelligenceText.setText(String.valueOf(HomeData.opponentId.get(0).getPowerStats().getIntelligence()));
                strengthText.setText(String.valueOf(HomeData.opponentId.get(0).getPowerStats().getStrength()));
                speedText.setText(String.valueOf(HomeData.opponentId.get(0).getPowerStats().getSpeed()));
                durabilityText.setText(String.valueOf(HomeData.opponentId.get(0).getPowerStats().getDurability()));
                powerText.setText(String.valueOf(HomeData.opponentId.get(0).getPowerStats().getPower()));
                combatText.setText(String.valueOf(HomeData.opponentId.get(0).getPowerStats().getCombat()));
                battleCharacterButton.setText("Battle " + HomeData.opponentId.get(0).getName());
                assignNewCharacterButton.setText("Assign Yourself " + HomeData.opponentId.get(0).getName());
                moreInfoCharacterButton.setText("More Info");
            }
        }
        private void assignNewCharacter(){
            assignNewCharacterButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                    alertDialog.setTitle("Assigning New Character ");
                    alertDialog.setMessage("Are you sure you want to assign this character as your new character?");
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ArrayList<Results> resultsList = new ArrayList<>();
                            Results results = new Results();
                            SearchName searchName = new SearchName();
                            HomeData.searchNameList = new ArrayList<>();
                            results.setId(HomeData.opponentId.get(0).getId());
                            results.setName(HomeData.opponentId.get(0).getName());
                            results.setBiography(HomeData.opponentId.get(0).getBiography());
                            results.setAppearance(HomeData.opponentId.get(0).getAppearance());
                            results.setConnections(HomeData.opponentId.get(0).getConnections());
                            results.setImage(HomeData.opponentId.get(0).getImage());
                            results.setWork(HomeData.opponentId.get(0).getWork());
                            results.setPowerStats(HomeData.opponentId.get(0).getPowerStats());
                            resultsList.add(results);
                            sp = PreferenceManager.getDefaultSharedPreferences(mContext);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("uniqueId", results.getId());
                            System.out.println(HomeData.opponentId.get(0).getId());
                            editor.apply();
                            searchName.setResults(resultsList);
                            HomeData.searchNameList.add(searchName);
                            final AlertDialog.Builder alertDialogAdd = new AlertDialog.Builder(mContext);
                            alertDialogAdd.setTitle("Character Switched");
                            alertDialogAdd.setMessage(HomeData.opponentId.get(0).getName() + " is your new character!");
                            alertDialogAdd.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog1 = alertDialogAdd.create();
                            alertDialog1.show();
                        }
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();

                }
            });
        }
        private void moreInfoOnCharacter(){
            moreInfoCharacterButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                    builder.setTitle("More Info");
//                    String[] characterInfo = {"Name: " + HomeData.opponentId.get(0).getName(), "cow", "camel", "sheep", "goat"};
//                    builder.setItems(characterInfo, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
                }
            });
        }
        private void switchToBattleCharacter(){
            battleCharacterButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(HomeData.searchNameList != null){
                        if(HomeData.searchNameList.get(0).getResults().get(0).getName().equals(HomeData.opponentId.get(0).getName())){
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                            alertDialog.setTitle("Cant Battle Yourself");
                            alertDialog.setMessage(HomeData.searchNameList.get(0).getResults().get(0).getName() + " vs " + HomeData.opponentId.get(0).getName() + " is not a option, switch character or find a new character to battle!");
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
                        }else
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
                                    final AlertDialog alertDialog= new
                                            SpotsDialog.Builder().setContext(mContext).build();
                                    alertDialog.setTitle("Loading Battle");
                                    alertDialog.setMessage("Please wait.....");
                                    alertDialog.show();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable()
                                    {

                                        @Override
                                        public void run()
                                        {
                                            alertDialog.dismiss();
                                            ((MainActivity) mContext).switchToVs();
                                        }
                                    },3000);
                                }
                            });
                            AlertDialog dialog = alertDialog.create();
                            dialog.show();
                        }
                    }else{
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
            });
        }
    }
}
