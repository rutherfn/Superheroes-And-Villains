package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nick R.
 */
public class SettingsTitle extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SettingsTitle(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.title_textview,viewGroup,false);
        return new SettingsTitleView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        SettingsTitleView settingsTitleView = (SettingsTitleView) holder;
        settingsTitleView.Main();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class SettingsTitleView extends RecyclerView.ViewHolder{
        TextView mainSettingsTextView;

        public SettingsTitleView(@NonNull View itemView)
        {
            super(itemView);
            mainSettingsTextView = itemView.findViewById(R.id.titleTextView);
        }
        private void Main(){
            setUpSharedPrefs();
            setMainSettingsTextView();
            setTypeFaceMainTitle();
            setTextViewToDarkMode();
        }
        private void setTextViewToDarkMode(){ // listen if dark mode is enabled or not.
            if(sp.getInt("darkMode",0) == 1){
                mainSettingsTextView.setTextColor(Color.parseColor("#FFFFFF"));
            }else{
                mainSettingsTextView.setTextColor(Color.parseColor("#000000"));
            }
        }
        private void setUpSharedPrefs(){
            sp = mContext.getSharedPreferences("key", 0);
            editor = sp.edit();
        }
        private void setMainSettingsTextView(){ // simple setting text view.
            mainSettingsTextView.setText("Settings");
        }
        private void setTypeFaceMainTitle(){ // setting type face
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainSettingsTextView.setTypeface(mainTextTypeFace);
        }
    }
}
