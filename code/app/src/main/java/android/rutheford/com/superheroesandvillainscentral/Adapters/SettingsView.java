package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.Models.Settings.SettingModel;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
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
        TextView settingsDesc;

        public SettingViewAdapter(@NonNull View itemView)
        {
            super(itemView);
            imageSettingMain = itemView.findViewById(R.id.settingsMainImageView);
            subHeaderTitle = itemView.findViewById(R.id.settingsMainHeader);
            settingsDesc = itemView.findViewById(R.id.settingsParagraph);

        }
        private void loadData(int position){
            Picasso.get().load(sizeOfSettings.get(position).getImageSetting()).into(imageSettingMain);
            subHeaderTitle.setText(sizeOfSettings.get(position).getTitle());
            settingsDesc.setText(sizeOfSettings.get(position).getDesc());
        }
    }
}
