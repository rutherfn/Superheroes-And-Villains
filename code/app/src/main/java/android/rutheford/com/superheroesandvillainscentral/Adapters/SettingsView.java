package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SettingsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private List<SettingModel> sizeOfSettings = new ArrayList<>();


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
       // System.out.println(sizeOfSettings.size() + " here is the first size");
            settingViewAdapter.loadData(i);
            settingViewAdapter.checkForSwitchChecked();
            settingViewAdapter.setChangeDarkModeSwitchNonVisible(i);
            settingViewAdapter.takeMeToASettingSectionConstriant(i);
            settingViewAdapter.switchCaseForDarkMode();
//        System.out.println(sizeOfSettings.size() + " here is the size");
//        settingViewAdapter.loadData(i);
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
        Switch changeDarkModeSwitch;

        public SettingViewAdapter(@NonNull View itemView)
        {
            super(itemView);
            settingCons = itemView.findViewById(R.id.settingsCons);
            imageSettingMain = itemView.findViewById(R.id.settingsMainImageView);
            subHeaderTitle = itemView.findViewById(R.id.settingsMainHeader);
            settingsDesc = itemView.findViewById(R.id.settingsParagraph);
            changeDarkModeSwitch = itemView.findViewById(R.id.changeDarkModeSwitch);
        }
        private void sharedPredsDarkMode(int value){
            SharedPreferences sp1 = mContext.getSharedPreferences("key", 0);
            SharedPreferences.Editor editor1 = sp1.edit();
            editor1.putInt("darkMode", value);
            editor1.apply();
           ((MainActivity)mContext).reloadForDarkMode();
        }
        private void checkForSwitchChecked(){
            SharedPreferences sp = mContext.getSharedPreferences("key",0);
            if(sp.getInt("darkMode",0) == 1){
                changeDarkModeSwitch.setChecked(true);
            }else{
                changeDarkModeSwitch.setChecked(false);
            }
        }
        private void switchCaseForDarkMode(){
            changeDarkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if(isChecked){
                        sharedPredsDarkMode(1);
                        System.out.println("Checked");
                    }else{
                        sharedPredsDarkMode(0);
                        System.out.println("Not checked");
                    }
                }
            });
        }
        private void changeSettings(int pos){
            Intent intent;
            if(pos == 0){
                 intent = new Intent(mContext,OnBoardingActivity.class);
                ((MainActivity)mContext).changeActivitys(intent);
            }
            else if(pos == 1){
                // nothing using the switch to change the dark mode
            }
            else if(pos == 2){
                intent = new Intent(mContext, WebView.class);
                ((MainActivity)mContext).changeActivitys(intent);
            }else if(pos == 3){
                ((MainActivity)mContext).reloadFragmentViewYourCharacter();
            }else if(pos == 4){

            }else if(pos == 5){
                ((MainActivity)mContext).changeViewPager(7);
//                intent = new Intent(mContext, PrivacyPolicy.class);
//                ((MainActivity)mContext).changeActivitys(intent);
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
        private void setChangeDarkModeSwitchNonVisible(int pos){

            if(pos >= 2 || pos == 0){
                changeDarkModeSwitch.setVisibility(View.GONE);
            }
        }
        private void loadData(int position){
            Picasso.get().load(sizeOfSettings.get(position).getImageSetting()).into(imageSettingMain);
            subHeaderTitle.setText(sizeOfSettings.get(position).getTitle());
            settingsDesc.setText(sizeOfSettings.get(position).getDesc());
        }
    }
}
