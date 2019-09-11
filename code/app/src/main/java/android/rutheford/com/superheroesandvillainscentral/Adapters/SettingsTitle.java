package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsTitle extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
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
        settingsTitleView.setMainSettingsTextView();
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
        private void setMainSettingsTextView(){
            mainSettingsTextView.setText("Settings");
        }
        private void setTypeFaceMainTitle(){

        }
    }
}
