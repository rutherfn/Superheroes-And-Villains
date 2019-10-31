package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Activitys.OnBoardingActivity;
import android.rutheford.com.superheroesandvillainscentral.Activitys.WebView;
import android.rutheford.com.superheroesandvillainscentral.Models.Settings.SettingModel;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SettingsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private List<SettingModel> sizeOfSettings = new ArrayList<>();
    private SharedPreferences sp1;
    private SharedPreferences.Editor editor1;


    public SettingsView(Context mContext, List<SettingModel> sizeOfSettings)
    {
        this.mContext = mContext;
        this.sizeOfSettings = sizeOfSettings;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.settings_main,viewGroup,false);
        return new SettingViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        SettingViewAdapter settingViewAdapter = (SettingViewAdapter) holder;
        settingViewAdapter.Main(i);
    }

    @Override
    public int getItemCount()
    {
        return sizeOfSettings.size();
    }
    class SettingViewAdapter extends RecyclerView.ViewHolder{
        ImageView imageSettingMain;
        TextView subHeaderTitle;
        ConstraintLayout settingCons;
        TextView settingsDesc;

        public SettingViewAdapter(@NonNull View itemView)
        {
            super(itemView);
            settingCons = itemView.findViewById(R.id.settingsCons);
            imageSettingMain = itemView.findViewById(R.id.settingsMainImageView);
            subHeaderTitle = itemView.findViewById(R.id.settingsMainHeader);
            settingsDesc = itemView.findViewById(R.id.settingsParagraph);
        }
        protected void Main(int i){
            setUpSharedPrefs();
            checkIfSharedPrefsDarkIsSet();
            changeTextColorBasedOnDarkMode();
            loadData(i);
            takeMeToASettingSectionConstriant(i);
            changeImageFilterColor(i);
            setUpTypeFace();
        }
        private void checkIfSharedPrefsDarkIsSet(){
            if(sp1.getInt("darkMode",0) == 0 ){
                editor1.putInt("darkMode",0);
                editor1.apply();
            }
        }
        private void setUpTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            Typeface mainBody = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
            subHeaderTitle.setTypeface(mainTextTypeFace);
            settingsDesc.setTypeface(mainBody);
        }
        private void changeImageFilterColor(int pos){
            if(pos == 1 || pos == 2){
                if(sp1.getInt("darkMode",0) == 1){
                    imageSettingMain.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
        }
        @SuppressLint("CommitPrefEdits")
        private void setUpSharedPrefs(){
            sp1 = mContext.getSharedPreferences("key", 0);
            editor1 = sp1.edit();
        }
        private void changeTextColorBasedOnDarkMode(){
            if(sp1.getInt("darkMode",0) == 1){
                subHeaderTitle.setTextColor(Color.parseColor("#FFFFFF"));
                settingsDesc.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        private void sharedPrefsDarkMode(int value){
            editor1.putInt("darkMode", value);
            editor1.apply();
           ((MainActivity)mContext).reloadForDarkMode();
        }
        private void changeSettings(int pos){
            Intent intent;
            if(pos == 0){
                 intent = new Intent(mContext,OnBoardingActivity.class);
                ((MainActivity)mContext).changeActivitys(intent);
            }
            else if(pos == 1){
                if(sp1.getInt("darkMode",0) == 1){
                    sharedPrefsDarkMode(0);
                }else{
                    sharedPrefsDarkMode(1);
                }
            }
            else if(pos == 2){
                intent = new Intent(mContext, WebView.class);
                ((MainActivity)mContext).changeActivitys(intent);
            }else if(pos == 3){
                ((MainActivity)mContext).reloadFragmentViewYourCharacter();
            }else if(pos == 4){
                setUpAlertForWinsAndLoses();
            }else if(pos == 5){
                ((MainActivity)mContext).changeViewPager(7);
//                intent = new Intent(mContext, PrivacyPolicy.class);
//                ((MainActivity)mContext).changeActivitys(intent);
            }else if(pos == 6){
                ((MainActivity)mContext).restartMainActivity();
            }else if(pos == 7){
                ((MainActivity)mContext).reloadFragmentPurchaseCharacters();
            }else if(pos == 8){
                setUpTacOrSimSharedPrefs();
            }else if(pos == 9){
                setUpDirectionsSharedPrefs();
            }
        }
        private void takeMeToASettingSectionConstriant(final int pos){
            settingCons.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    changeSettings(pos);
                }
            });
        }
        private void showDialog(String desc,String title){
            final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setTitle(title);
            alert.setMessage(desc);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener()
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
        private void setUpDirectionsSharedPrefs(){
            if(sp1.getInt("directionsTactical",0) == 1){
                System.out.println("it equals 1");
                editor1.putInt("directionsTactical",0);
                editor1.apply();
                showDialog("Directions For Tactical Disable. Press OK to continue!","Directions Swapped");

            }else {
                editor1.putInt("directionsTactical",1);
                editor1.apply();
                showDialog("Directions For Tactical Enable. Press OK to continue!","Directions Swapped");
            }
        }

        private void setUpTacOrSimSharedPrefs(){
            if(sp1.getInt("simOrTac",0) == 1){
                editor1.putInt("simOrTac",0);
                editor1.apply();
                showDialog("Simulation Battles Are Enabled, while Tactical Battles are Disabled. Press OK to continue!","Simulation Or Tactical?");

            }else {
                editor1.putInt("simOrTac",1);
                editor1.apply();
                showDialog("Tactical Battles Are Enabled, while Simulation Battles are Disabled. Press OK to continue!","Simulation Or Tactical?");
            }
        }
        private void setUpAlertForWinsAndLoses(){
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Wins Losses / XP");
            String[] winsLossesXP = {"Wins:  " + sp1.getInt("wins",0), "Loses: " + sp1.getInt("loses",0), "Draws: " + sp1.getInt("ties",0), "XP:  " + sp1.getInt("xp",0)};
            builder.setItems(winsLossesXP, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

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
        private void loadData(int position){
            Picasso.get().load(sizeOfSettings.get(position).getImageSetting()).into(imageSettingMain);
            subHeaderTitle.setText(sizeOfSettings.get(position).getTitle());
            settingsDesc.setText(sizeOfSettings.get(position).getDesc());
        }
    }
}
