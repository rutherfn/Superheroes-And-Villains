package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchResultsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private List<Id> searchName;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SearchResultsView(Context mContext, List<Id> searchName)
    {
        this.mContext = mContext;
        this.searchName = searchName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen,viewGroup,false);
        return new SearchResultsViewGroup(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        SearchResultsViewGroup searchResultsViewGroup = (SearchResultsViewGroup) holder;
        searchResultsViewGroup.Main();
    }

    @Override
    public int getItemCount()
    {
        if(searchName.size() > 1){
            return 0;
        }else{
         return 1;
        }
    }
    class SearchResultsViewGroup extends RecyclerView.ViewHolder{
        CircleImageView mainCircleImageView;
        TextView mainTextContent;

        public SearchResultsViewGroup(@NonNull View itemView)
        {
            super(itemView);
            mainCircleImageView = itemView.findViewById(R.id.mainCircleImageView);
            mainTextContent = itemView.findViewById(R.id.mainTitle);
        }
        private void Main(){
            setUpSharedPrefs();
            loadPlaceHolderIcon(0);
            setTypeFace();
            loadMainTextContentForTitle(0);
            selectImageViewOrTextView(0);
            checkDarkMode();
        }

        private void setUpSharedPrefs(){
            sp = mContext.getSharedPreferences("key", 0);
            editor = sp.edit();
        }
        private void checkDarkMode(){
            if(sp.getInt("darkMode",0) == 1){
                mainTextContent.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        private void loadPlaceHolderIcon(int position){
            if(searchName.get(position).getImage().getMd().equals("")){

            }else
            {
                Picasso.get().load(searchName.get(position).getImage().getMd()).into(mainCircleImageView);
            }
        }
        private void setTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainTextContent.setTypeface(mainTextTypeFace);
        }
        private void passData(int pos){
            ((MainActivity)mContext).changeToStatsView(searchName,pos,true);
        }
        private void selectImageViewOrTextView(final int pos){
            mainCircleImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    passData(pos);
                }
            });
            mainTextContent.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    passData(pos);
                }
            });
        }
        @SuppressLint("SetTextI18n")
        private void loadMainTextContentForTitle(int position){
            if(searchName.get(position).getBiography().getAlignment().equals("good")){
                mainTextContent.setTextColor(Color.parseColor("#006400"));
                mainTextContent.setText(searchName.get(position).getName() + ", Good");
            }else if(searchName.get(position).getBiography().getAlignment().equals("bad")){
                mainTextContent.setTextColor(Color.parseColor("#B22222"));
                mainTextContent.setText(searchName.get(position).getName() + ", Bad");
            }else{
                mainTextContent.setText(searchName.get(position).getName() + ", Neutral");
            }
        }

    }
}
